package edu.cmu.lunwenh.artistshow;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lunwenh on 3/31/2016.
 */
public class SendEmail extends Fragment {

    private View fragmentView;

    private void sendEmail() {
        EditText sendToEditText = (EditText)fragmentView.findViewById(R.id.emailTo);
        String to = sendToEditText.getText().toString();
        EditText subjectEditText = (EditText)fragmentView.findViewById(R.id.suject);
        String subject = subjectEditText.getText().toString();
        EditText contentEditText = (EditText)fragmentView.findViewById(R.id.emailContent);
        String content = contentEditText.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.email, container, false);
        Button sendEmailButton = (Button)fragmentView.findViewById(R.id.sendEmail);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        return fragmentView;
    }
}
