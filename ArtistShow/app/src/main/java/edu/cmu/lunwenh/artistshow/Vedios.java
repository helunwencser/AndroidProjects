package edu.cmu.lunwenh.artistshow;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunwenh on 3/31/2016.
 */
public class Vedios extends Fragment {

    private View fragmentView;
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.vedios, container, false);

        addItemsToListView();

        final ListView listView = (ListView)fragmentView.findViewById(R.id.listViewOfVedio);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String song = (String) listView.getItemAtPosition(position);

                Button pauseButton = (Button) fragmentView.findViewById(R.id.pauseVedio);
                Button startButton = (Button) fragmentView.findViewById(R.id.startVedio);
                Button stopButton = (Button) fragmentView.findViewById(R.id.stopVedio);
                final VideoView videoView = (VideoView)fragmentView.findViewById(R.id.videoView);

                String item = (String) listView.getItemAtPosition(position);
                //final MediaPlayer mediaPlayer;
                String path = "android.resource://" + getActivity().getPackageName() + "/raw/" + R.raw.vedio1;
                videoView.setVideoURI(Uri.parse(path));
                pauseButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        videoView.pause();
                    }
                });

                startButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        videoView.start();
                    }
                });

                stopButton.setOnClickListener(new Button.OnClickListener() {

                    public void onClick(View arg0) {
                        videoView.stopPlayback();
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
        ListView listView = (ListView)fragmentView.findViewById(R.id.listViewOfVedio);
        List<String> list = new ArrayList<String>();
        list.add("vedio1.mp4");
        list.add("vedio2.mp4");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fragmentView.getContext(), R.layout.simplerow, list);
        listView.setAdapter(adapter);
    }
}
