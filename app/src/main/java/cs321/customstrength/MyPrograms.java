package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        TableRow header= new TableRow(this);
        ContextThemeWrapper headerContext= new ContextThemeWrapper(this, R.style.headerText);
        TextView text= new TextView(headerContext);
        text.setText(R.string.programsBtnLabel);
        header.addView(text);
        tableLayout.addView(header);
        for (int i = 0; i < programs.length; i++) {
            TableRow row = new TableRow(this);
            Button button = new Button(this);
            button.setText(programs[i].toString());
            row.addView(button);
            tableLayout.addView(row);
        }
    }
}
