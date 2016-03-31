package edu.cmu.lunwenh.artist;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private String[] audios;
    private String[] vedios;

    int[] cargallery = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    int carIndex = -1;

    private static final int UPDATE_IMAGE = 6000;

    LinearLayout linearLayout = null;

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == UPDATE_IMAGE) {
                if (carIndex >= cargallery.length - 1) {
                    carIndex = -1;
                }
                carIndex++;
                linearLayout.setBackgroundResource(cargallery[carIndex]);
                reloadCarImage();
            }
        }
    };

    private void reloadCarImage() {
        Message message = new Message();
        message.what = UPDATE_IMAGE;
        handler.sendMessageDelayed(message, 3000);
    }

    /**
     * set the personal information of artist
     */
    private void setPersonalInfo() {
        InputStream inputStream = this.getResources().openRawResource(R.raw.info);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = properties.getProperty("name");
        TextView nameTextView = (TextView) this.findViewById(R.id.name);
        nameTextView.setText("Name: " + name);

        String link = properties.getProperty("link");
        TextView linkTextView = (TextView) this.findViewById(R.id.link);
        linkTextView.setText("Link: " + link);

        String phoneNumber = properties.getProperty("phoneNumber");
        TextView phoneNumerTextView = (TextView) this.findViewById(R.id.phoneNumber);
        phoneNumerTextView.setText("Phone Number: " + phoneNumber);

        String email = properties.getProperty("email");
        TextView emailTextView = (TextView) this.findViewById(R.id.email);
        emailTextView.setText("Email: " + email);

        String likes = properties.getProperty("likes");
        TextView likesTextView = (TextView) this.findViewById(R.id.likes);
        likesTextView.setText("Likes: " + likes);

        String disLikes = properties.getProperty("disLikes");
        TextView disLikesTextView = (TextView) this.findViewById(R.id.dislikes);
        disLikesTextView.setText("Dislike: " + disLikes);

        String dateOfBirth = properties.getProperty("dateOfBirth");
        TextView dateOfBirthTextView = (TextView) this.findViewById(R.id.dateOfBirth);
        dateOfBirthTextView.setText("Birthday: " + dateOfBirth);

        String socialNetwork = properties.getProperty("socialNetwork");
        TextView socialNetworkTextView = (TextView) this.findViewById(R.id.socialNetwork);
        socialNetworkTextView.setText("Facebook: "+ socialNetwork);

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout);
        reloadCarImage();

        setPersonalInfo();

        AssetManager assetManager = this.getAssets();
        try{
            audios = assetManager.list("audio");
            Arrays.sort(audios);
            vedios = assetManager.list("vedio");
            Arrays.sort(vedios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addItemsToListView(audios, "audio");
        addItemsToListView(vedios, "vedio");

        addListenerOnAudioListView();
        addListenerOnVedioListView();
    }

    /**
     * add items to ListView
     * @param   items
     *          the name of items
     *
     * @param   type
     *          type of list, possible values are: audio, vedio
     * */
    private void addItemsToListView(String[] items, String type) {
        ListView listView = null;
        if(type.equals("audio")) {
            listView = (ListView)this.findViewById(R.id.listViewOfAudio);
        } else if(type.equals("vedio")) {
            listView = (ListView)this.findViewById(R.id.listViewOfVedio);
        }
        if(linearLayout != null) {
            List<String> list = new ArrayList<String>();
            list.addAll(Arrays.asList(items));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simplerow, list);
            listView.setAdapter(adapter);
        }
    }

    /**
     * add item click listener on audio list view
     * */
    private void addListenerOnAudioListView() {
        final ListView listViewAudio = (ListView)this.findViewById(R.id.listViewOfAudio);
        listViewAudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.audio_control, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                Button pauseButton = (Button)popupView.findViewById(R.id.pauseAudio);
                Button startButton = (Button)popupView.findViewById(R.id.startAudio);
                Button stopButton = (Button)popupView.findViewById(R.id.stopAudio);

                VideoView videoView = (VideoView)findViewById(R.id.videoView);

                String item = (String)listViewAudio.getItemAtPosition(position);
                final MediaPlayer mediaPlayer;
                try{
                    AssetFileDescriptor assetFileDescriptor = getAssets().openFd(item);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(
                            assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(),
                            assetFileDescriptor.getLength()
                    );
                    mediaPlayer.setDisplay(videoView.getHolder());

                    mediaPlayer.prepare();

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
                            popupWindow.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * add listener on vedio list view
     * */
    private void addListenerOnVedioListView() {
        final ListView listViewVedio = (ListView)this.findViewById(R.id.listViewOfVedio);

        listViewVedio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layoutInflater.inflate(R.layout.vedio_control, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                Button pauseButton = (Button)popupView.findViewById(R.id.pauseVedio);
                Button startButton = (Button)popupView.findViewById(R.id.startVedio);
                Button stopButton = (Button)popupView.findViewById(R.id.stopVedio);

                String item = (String)listViewVedio.getItemAtPosition(position);
                final MediaPlayer mediaPlayer;
                try{
                    AssetFileDescriptor assetFileDescriptor = getAssets().openFd(item);
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor());
                    mediaPlayer.prepare();

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
                            popupWindow.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
