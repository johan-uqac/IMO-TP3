package uqac.dim.mafag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private String defaultMafagName = "Facebook";
    private String defaultMafagURL = "https://www.facebook.com";

    private String screenMafagName = defaultMafagName;
    private String screenMafagURL = defaultMafagURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String savedMafagName = null;
        String savedMafagURL = null;

        if (savedInstanceState != null) {
            savedMafagName = savedInstanceState.getString("mafagName");
            savedMafagURL = savedInstanceState.getString("mafagURL");
        }

        TextView urlTextView = findViewById(R.id.urlTextView);
        urlTextView.setText(savedMafagURL != null ? savedMafagURL : defaultMafagURL);
        screenMafagURL = savedMafagURL != null ? savedMafagURL : defaultMafagURL;

        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(savedMafagName != null ? savedMafagName : defaultMafagName);
        screenMafagName = savedMafagName != null ? savedMafagName : defaultMafagName;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView urlTextView = findViewById(R.id.urlTextView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        outState.putString("mafagURL", urlTextView.getText().toString());
        outState.putString("mafagName", titleTextView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView urlTextView = findViewById(R.id.urlTextView);
        TextView titleTextView = findViewById(R.id.titleTextView);
        String mafagURL;
        String mafagName;

        if (savedInstanceState != null) {
            mafagURL = savedInstanceState.getString("mafagURL");
            mafagName = savedInstanceState.getString("mafagName");
        } else {
            mafagURL = defaultMafagURL;
            mafagName = defaultMafagName;
        }
        screenMafagURL = mafagURL != null ? mafagURL : defaultMafagURL;
        screenMafagName = mafagName != null ? mafagName : defaultMafagName;

        urlTextView.setText(mafagURL);
        titleTextView.setText(mafagName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String mafagNameIntent = data.getStringExtra("mafagBlocked");
            String mafagURLIntent = data.getStringExtra("mafagURL");

            TextView urlTextView = findViewById(R.id.urlTextView);
            urlTextView.setText(mafagURLIntent != null ? mafagURLIntent : defaultMafagURL);
            screenMafagURL = mafagURLIntent != null ? mafagURLIntent : defaultMafagURL;

            TextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setText(mafagNameIntent != null ? mafagNameIntent : defaultMafagName);
            screenMafagName = mafagNameIntent != null ? mafagNameIntent : defaultMafagName;
        }
    }

    public void openInBrowser(View v) {
        TextView urlTextView = findViewById(R.id.urlTextView);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlTextView.getText().toString()));

        startActivity(browserIntent);
    }

    public void changePage(View view) {
        TextView mafagName = findViewById(R.id.titleTextView);
        Intent intent = new Intent(this, MafagActivity.class);
        intent.putExtra("mafagName", mafagName.getText().toString());
        startActivityForResult(intent, 1);
    }

    public void sendNotification(View v) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        TextView mafagName = findViewById(R.id.titleTextView);

        NotificationService.showNotification(this, screenMafagName, "", notificationIntent);
    }
}