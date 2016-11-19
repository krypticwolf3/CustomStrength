package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class programDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_display);
        Intent displayIntent=getIntent();
    }
}
