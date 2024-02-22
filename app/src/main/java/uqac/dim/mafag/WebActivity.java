package uqac.dim.mafag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String mafagName = getIntent().getStringExtra("mafagName");
        String mafagURL;

        if (mafagName.equals("Google")) {
            mafagURL = "https://www.google.com";
        } else if (mafagName.equals("Amazon")) {
            mafagURL = "https://www.amazon.com";
        } else if (mafagName.equals("Facebook")) {
            mafagURL = "https://www.facebook.com";
        } else if (mafagName.equals("Apple")) {
            mafagURL = "https://www.apple.com";
        } else {
            mafagURL = "https://www.microsoft.com";
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mafagURL));

        startActivity(browserIntent);
    }
}
