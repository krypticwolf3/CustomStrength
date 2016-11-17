package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyPrograms extends Activity {
    Program[] programs = {new Program("Starting Strength"), new Program("Stronglifts"), new Program("5/3/1")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);
        Intent programsIntent=getIntent();
        init();
    }
    public void init() {
        TableLayout tableLayout=(TableLayout) findViewById(R.id.programTable);
        for (int i = 0; i < programs.length; i++) {
            TableRow row = new TableRow(this);
            TextView text = new TextView(this);
            text.setText(programs[i].toString());
            row.addView(text);
            tableLayout.addView(row);
        }
    }
}
