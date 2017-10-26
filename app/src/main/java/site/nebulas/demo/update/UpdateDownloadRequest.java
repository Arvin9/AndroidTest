package site.nebulas.demo.update;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Response;
import site.nebulas.demo.base.BaseCallback;
import site.nebulas.demo.utils.HttpUtils;

/**
 * Created by Nebula on 2017/10/26.
 */

public class UpdateDownloadRequest implements Runnable {
    private String TAG = "UpdateDownloadRequest";

    private String downloadUrl;
    private String localFilePath;
    private UpdateDownloadListener updateDownloadListener;
    private boolean isDownloading = false;
    private long currentLength;
    private DownloadResponseHandler downloadResponseHandler;

    public UpdateDownloadRequest(String downloadUrl, String localFilePath, UpdateDownloadListener updateDownloadListener) {
        this.downloadUrl = downloadUrl;
        this.localFilePath = localFilePath;
        this.updateDownloadListener = updateDownloadListener;
        this.isDownloading = true;
        this.downloadResponseHandler = new DownloadResponseHandler();
    }

    private void makeRequest() {
        String url = "https://github.com/Arvin9/data/raw/master/test.apk";
        String savePath = "";
        if (!Thread.currentThread().isInterrupted()) {
            HttpUtils.downFile(url, savePath, new BaseCallback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!Thread.currentThread().isInterrupted()) {
                        downloadResponseHandler.sendResponseMessage(response.body().byteStream());

                    }
                }
            });
        }
    }

    @Override
    public void run() {
        makeRequest();
    }

    /**
     * 格式化数字
     * */
    private String getTowPointFloatStr(float value) {
        DecimalFormat fnum = new DecimalFormat("0.00");
        return fnum.format(value);
    }

    public enum FailureCode {
        UnKnownHost, Socket, SocketTimeout, ConnectTimeout,
        IO, HttpResponse, JSON, Interrupted
    }

    public class DownloadResponseHandler {
        protected static final int SUCCESS_MESSAGE = 0;
        protected static final int FAILURE_MESSAGE = 1;
        protected static final int START_MESSAGE = 2;
        protected static final int FINISH_MESSAGE = 3;
        protected static final int NETWORK_OFF = 4;
        protected static final int PROGRESS_CHANGE = 5;

        private int mCompleteSize = 0;
        private int progress = 0;
        private Handler handler;

        public DownloadResponseHandler() {
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    handlerSelfMessage(msg);
                }
            };
        }

        /**
         * 发送不同的消息
         * */
        protected void sendFinishMessage() {
            sendMessage(obtainMessage(FINISH_MESSAGE, null));
        }
        protected void sendProgressChangedMessage(int progress) {
            sendMessage(obtainMessage(PROGRESS_CHANGE, new Object[] {progress}));
        }

        protected void sendFailureMessage(FailureCode failureCode) {
            sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[] {failureCode}));
        }

        protected void sendMessage(Message msg) {
            if (handler != null) {
                handler.sendMessage(msg);
            } else {
                handlerSelfMessage(msg);
            }
        }

        protected Message obtainMessage(int responseMessage, Object response) {
            Message msg = null;
            if (handler != null) {
                msg = handler.obtainMessage(responseMessage, response);
            } else {
                msg = Message.obtain();
                msg.what = responseMessage;
                msg.obj = response;
            }
            return msg;
        }

        protected void handlerSelfMessage(Message msg) {
            Object[] response;
            switch (msg.what) {
                case FAILURE_MESSAGE: {
                    response = (Object[]) msg.obj;
                    handleFailureMessage((FailureCode) response[0]);
                    break;
                }
                case PROGRESS_CHANGE: {
                    response = (Object[]) msg.obj;
                    handleProgressChangedMessage(((Integer) response[0]).intValue());
                    break;
                }
                case FINISH_MESSAGE: {
                    onFinish();
                    break;
                }
            }
        }

        protected void handleProgressChangedMessage(int progress) {
            updateDownloadListener.onProgressChange(progress, "");
        }
        protected void handleFailureMessage(FailureCode failureCode) {
            onFailure(failureCode);
        }
        public void onFinish() {
            updateDownloadListener.onFinished(mCompleteSize, "");
        }
        public void onFailure(FailureCode failureCode) {
            updateDownloadListener.onFailure();
        }

        public void sendResponseMessage(InputStream is) {
            RandomAccessFile randomAccessFile = null;
            mCompleteSize = 0;
            try {
                byte[] buf = new byte[2048];
                int length = -1;
                int limit = 0;
                randomAccessFile = new RandomAccessFile(localFilePath, "rwd");
                while ((length = is.read(buf)) != -1) {
                    if (isDownloading) {
                        randomAccessFile.write(buf, 0, length);
                        mCompleteSize += length;
                        if (mCompleteSize < currentLength) {
                            progress = (int) Float.parseFloat(getTowPointFloatStr(mCompleteSize / currentLength));
                            if (limit / 30 == 0 || progress <= 100 ) {
                                // 限制notification更新频率
                                sendProgressChangedMessage(progress);
                            }
                            limit++;
                        }
                    }
                }
                sendFinishMessage();
            }catch (IOException e){
                sendFailureMessage(FailureCode.IO);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    sendFailureMessage(FailureCode.IO);
                }

            }
        }
    }
}
