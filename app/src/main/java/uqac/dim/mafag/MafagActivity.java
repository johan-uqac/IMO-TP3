package uqac.dim.mafag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MafagActivity extends AppCompatActivity {

    private String blockedMafag = "Google";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mafag_activity);
        blockedMafag = getIntent().getStringExtra("mafagName");
    }

    public void onIconPressed(View v) {
        String buttonMafagName = v.getContentDescription().toString();

        if (!buttonMafagName.equals(blockedMafag)) {
            String mafagURL;

            if (buttonMafagName.equals("Google")) {
                mafagURL = "https://www.google.com";
            } else if (buttonMafagName.equals("Amazon")) {
                mafagURL = "https://www.amazon.com";
            } else if (buttonMafagName.equals("Facebook")) {
                mafagURL = "https://www.facebook.com";
            } else if (buttonMafagName.equals("Apple")) {
                mafagURL = "https://www.apple.com";
            } else {
                mafagURL = "https://www.microsoft.com";
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra("mafagBlocked", buttonMafagName);
            resultIntent.putExtra("mafagURL", mafagURL);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }
}