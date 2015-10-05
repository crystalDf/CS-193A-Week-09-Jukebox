package com.star.jukebox;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class JukeboxService extends Service {

    public static final String TITLE = "Title";

    public static final String ACTION_PLAY = "play";
    public static final String ACTION_STOP = "stop";

    private MediaPlayer mMediaPlayer;

    public JukeboxService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        final String action = intent.getAction();
        final String title = intent.getStringExtra(TITLE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                if (action.equals(ACTION_PLAY)) {

                    int songResId = getResources().getIdentifier(title, "raw", getPackageName());

                    if (mMediaPlayer != null) {
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                    }

                    mMediaPlayer = MediaPlayer.create(JukeboxService.this, songResId);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.start();
                } else if (action.equals(ACTION_STOP)) {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                    }
                }
            }
        });
        thread.start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
