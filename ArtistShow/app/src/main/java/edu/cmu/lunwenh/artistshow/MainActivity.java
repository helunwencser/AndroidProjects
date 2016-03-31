package edu.cmu.lunwenh.artistshow;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout);
        reloadCarImage();

/*      final FragmentManager fragmentManager = this.getFragmentManager();

        Button personalInfoButton = (Button)this.findViewById(R.id.personalInfoButton);
        personalInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new PersonalInfo());
                fragmentTransaction.commit();
            }
        });
        Button songsButton = (Button)this.findViewById(R.id.songsButton);
        songsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Songs());
                fragmentTransaction.commit();
            }
        });
        Button vediosButton = (Button)this.findViewById(R.id.vediosButton);
        vediosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new Vedios());
                fragmentTransaction.commit();
            }
        });
        Button emailButton = (Button)this.findViewById(R.id.sendEmailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new SendEmail());
                fragmentTransaction.commit();
            }
        });*/
    }

    public void selectFragment(View view) {
        Fragment fragment;
        if(view == findViewById(R.id.personalInfoButton)) {
            fragment = new PersonalInfo();
        } else if(view == findViewById(R.id.songsButton)) {
            fragment = new Songs();
        } else if(view == findViewById(R.id.vediosButton)) {
            fragment = new Vedios();
        } else {
            fragment = new SendEmail();
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
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
