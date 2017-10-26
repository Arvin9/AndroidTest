package site.nebulas.demo.update;

/**
 * Created by Nebula on 2017/10/26.
 */

public interface UpdateDownloadListener {
    /**
     * 下载请求开始回调
     */
    public void onStarted();

    /**
     * 进度更新回调
     * @param progress
     * @param downloadUrl
     * */
    public void onProgressChange(int progress, String downloadUrl);

    /**
     * 下载完成回调
     * @param completeSize
     * @param downloadUrl
     * */
    public void onFinished(int completeSize, String downloadUrl);

    /**
     * 下载失败回调
     * */
    public void onFailure();
}
