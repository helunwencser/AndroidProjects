package edu.cmu.lunwenh.artist;

import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

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
            if(message.what == UPDATE_IMAGE) {
                if(carIndex >= cargallery.length - 1) {
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
     * */
    private void setPersonalInfo() {
        InputStream inputStream = this.getResources().openRawResource(R.raw.info);
        Properties properties = new Properties();
        try{
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = properties.getProperty("name");
        TextView nameTextView = (TextView)this.findViewById(R.id.name);
        nameTextView.setText(name);

        String link = properties.getProperty("link");
        TextView linkTextView = (TextView)this.findViewById(R.id.link);
        linkTextView.setText(link);

        String phoneNumber = properties.getProperty("phoneNumber");
        TextView phoneNumerTextView = (TextView)this.findViewById(R.id.phoneNumber);
        phoneNumerTextView.setText(phoneNumber);

        String email =  properties.getProperty("email");
        TextView emailTextView = (TextView)this.findViewById(R.id.email);
        emailTextView.setText(email);

        String likes = properties.getProperty("likes");
        TextView likesTextView = (TextView)this.findViewById(R.id.likes);
        likesTextView.setText(likes);

        String disLikes = properties.getProperty("disLikes");
        TextView disLikesTextView = (TextView)this.findViewById(R.id.dislikes);
        disLikesTextView.setText(disLikes);

        String dateOfBirth = properties.getProperty("dateOfBirth");
        TextView dateOfBirthTextView = (TextView)this.findViewById(R.id.dateOfBirth);
        dateOfBirthTextView.setText(dateOfBirth);

        String socialNetwork = properties.getProperty("socialNetwork");
        TextView socialNetworkTextView = (TextView)this.findViewById(R.id.socialNetwork);
        socialNetworkTextView.setText(socialNetwork);

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

        linearLayout = (LinearLayout)this.findViewById(R.id.linearLayout);
        reloadCarImage();

        setPersonalInfo();
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
