package oliot.lutemons.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import oliot.lutemons.R;
// Was able to clone the repo.
// Everything seems to be working fine.
// Added these comments to see that commit and push are working.
// The app does not run, because there is no activity_main xml?

// I created a seperate branch for local testing.
// You can decline this no biggie. It made some local additions to gradle.xml and misc.xml for some reason.

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
