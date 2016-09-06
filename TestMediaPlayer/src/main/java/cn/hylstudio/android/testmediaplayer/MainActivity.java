package cn.hylstudio.android.testmediaplayer;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * 28：编程项目-[上机实践]MediaPlayer-具体内容
 * created by hyl on 2016/9/6
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hylTag";
    private MediaPlayer mediaPlayer;

    private Button btn_start;
    private Button btn_pause;
    private Button btn_stop;
    private Button btn_loop;
    private TextView text_loopState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();

        text_loopState = (TextView) findViewById(R.id.text_loop_state);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_loop = (Button) findViewById(R.id.btn_loop);

        btn_pause.setEnabled(false);
        btn_stop.setEnabled(false);
        btn_loop.setEnabled(false);

        //开始播放
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Log.v(TAG, "start");
                    mediaPlayer.reset();

                    AssetManager assetManager = getAssets();
                    AssetFileDescriptor assetFileDescriptor = null;
                    try {
                        assetFileDescriptor = assetManager.openFd("AreYouOK.WMA");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    btn_pause.setEnabled(true);
                    btn_stop.setEnabled(true);
                    btn_loop.setEnabled(true);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //暂停播放
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    btn_pause.setText("播放");
                    mediaPlayer.pause();
                } else {
                    btn_pause.setText("暂停");
                    mediaPlayer.start();
                }

            }
        });

        //停止播放
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();

            }
        });

        //循环播放
        btn_loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Looping");

                boolean loop = mediaPlayer.isLooping();
                mediaPlayer.setLooping(!loop);


                if (!loop)
                    text_loopState.setText("循环播放");
                else
                    text_loopState.setText("一次播放");


            }
        });
    }


}
