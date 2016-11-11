package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyPrograms extends Activity {
    String[] stringArray = {"Starting Strength", "Stronglifts", "5/3/1"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);
        Intent programsIntent=getIntent();
    }
}
