package edu.cmu.lunwenh.artistshow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lunwenh on 3/31/2016.
 */
public class PersonalInfo extends Fragment {


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.personal_info, container, false);
        setPersonalInfo(view);
        return view;
    }

    /**
     * set the personal information of artist
     */
    private void setPersonalInfo(View view) {
        InputStream inputStream = this.getResources().openRawResource(R.raw.info);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = properties.getProperty("name");
        TextView nameTextView = (TextView)view.findViewById(R.id.name);
        nameTextView.setText("Name: " + name);

        String link = properties.getProperty("link");
        TextView linkTextView = (TextView)view.findViewById(R.id.link);
        linkTextView.setText("Link: " + link);

        String phoneNumber = properties.getProperty("phoneNumber");
        TextView phoneNumerTextView = (TextView)view.findViewById(R.id.phoneNumber);
        phoneNumerTextView.setText("Phone Number: " + phoneNumber);

        String email = properties.getProperty("email");
        TextView emailTextView = (TextView)view.findViewById(R.id.email);
        emailTextView.setText("Email: " + email);

        String likes = properties.getProperty("likes");
        TextView likesTextView = (TextView)view.findViewById(R.id.likes);
        likesTextView.setText("Likes: " + likes);

        String disLikes = properties.getProperty("disLikes");
        TextView disLikesTextView = (TextView)view.findViewById(R.id.dislikes);
        disLikesTextView.setText("Dislike: " + disLikes);

        String dateOfBirth = properties.getProperty("dateOfBirth");
        TextView dateOfBirthTextView = (TextView)view.findViewById(R.id.dateOfBirth);
        dateOfBirthTextView.setText("Birthday: " + dateOfBirth);

        String socialNetwork = properties.getProperty("socialNetwork");
        TextView socialNetworkTextView = (TextView)view.findViewById(R.id.socialNetwork);
        socialNetworkTextView.setText("Facebook: "+ socialNetwork);

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
