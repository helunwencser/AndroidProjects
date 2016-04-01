package edu.cmu.lunwenh.artistshow;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunwenh on 3/31/2016.
 */
public class Songs extends Fragment {

    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.songs, container, false);

        addItemsToListView();

        final ListView listView = (ListView)fragmentView.findViewById(R.id.listViewOfAudio);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String song = (String) listView.getItemAtPosition(position);

                Button pauseButton = (Button)fragmentView.findViewById(R.id.pauseAudio);
                Button startButton = (Button)fragmentView.findViewById(R.id.startAudio);
                Button stopButton = (Button)fragmentView.findViewById(R.id.stopAudio);


                String item = (String)listView.getItemAtPosition(position);
                final MediaPlayer mediaPlayer;
                if(item.equals("audio1.mp3")) {
                    mediaPlayer = MediaPlayer.create(fragmentView.getContext(), R.raw.audio1);
                } else {
                    mediaPlayer = MediaPlayer.create(fragmentView.getContext(), R.raw.audio2);
                }
                pauseButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        mediaPlayer.pause();
                    }
                });

                startButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        mediaPlayer.start();
                    }
                });

                stopButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        mediaPlayer.stop();
                    }
                });
            }
        });

        return fragmentView;
    }

    /**
     * add items to ListView
     * */
    private void addItemsToListView() {
        ListView listView = (ListView)fragmentView.findViewById(R.id.listViewOfAudio);
        List<String> list = new ArrayList<String>();
        list.add("audio1.mp3");
        list.add("audio2.mp3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fragmentView.getContext(), R.layout.simplerow, list);
        listView.setAdapter(adapter);
    }
}
