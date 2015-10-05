package com.star.jukebox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class JukeboxActivity extends AppCompatActivity {

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jukebox);

        ListView listView = (ListView) findViewById(R.id.song_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] songTitles = getResources().getStringArray(R.array.song_titles);
                mTitle = songTitles[position].toLowerCase().replace(" ", "");

                Intent intent = new Intent(JukeboxActivity.this, JukeboxService.class);
                intent.putExtra(JukeboxService.TITLE, mTitle);
                intent.setAction(JukeboxService.ACTION_PLAY);
                startService(intent);
            }
        });

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTitle != null) {
                    Intent intent = new Intent(JukeboxActivity.this, JukeboxService.class);
                    intent.putExtra(JukeboxService.TITLE, mTitle);
                    intent.setAction(JukeboxService.ACTION_PLAY);
                    startService(intent);
                }
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JukeboxActivity.this, JukeboxService.class);
                intent.setAction(JukeboxService.ACTION_STOP);
                startService(intent);
            }
        });

    }
}
