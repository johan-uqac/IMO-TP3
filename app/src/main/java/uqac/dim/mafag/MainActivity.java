package uqac.dim.mafag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private String defaultMafagName = "Google";
    private String defaultMafagURL = "https://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView urlTextView = findViewById(R.id.urlTextView);
        urlTextView.setText(defaultMafagURL);

        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(defaultMafagName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String mafagNameIntent = data.getStringExtra("mafagBlocked");
            String mafagURLIntent = data.getStringExtra("mafagURL");

            TextView urlTextView = findViewById(R.id.urlTextView);
            urlTextView.setText(mafagURLIntent != null ? mafagURLIntent : defaultMafagURL);

            TextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setText(mafagNameIntent != null ? mafagNameIntent : defaultMafagName);
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
}