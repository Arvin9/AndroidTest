package site.nebulas.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.BuildConfig;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import site.nebulas.demo.R;

/**
 * Created by Nebula on 2017/10/12.
 */

public class CameraActivity extends Activity implements View.OnClickListener{
    private int CAMERA_REQ = 1000;

    private ImageView mImageView;

    private String mFilePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        findViewById(R.id.open_camera).setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.image_show);
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath + "/" + "temp.png";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open_camera: {
                Log.i("T", mFilePath);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /*
                Uri photoUri;
                //判断是否是AndroidN以及更高的版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", new File(mFilePath));
                    intent.setDataAndType(photoUri, "application/vnd.android.package-archive");
                } else {
                    photoUri = Uri.fromFile(new File(mFilePath));
                    intent.setDataAndType(Uri.fromFile(new File(mFilePath)), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                */
                startActivityForResult(intent, CAMERA_REQ);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQ) {

                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                mImageView.setImageBitmap(bitmap);
                /*
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    mImageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                */
            }
        }
    }
}
