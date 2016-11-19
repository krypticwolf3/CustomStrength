package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;

public class MyPrograms extends Activity {

    ArrayList<Program> programs = new ArrayList<Program>();
    static ArrayList<Boolean> expanded=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);
        Intent programsIntent=getIntent();
        init();
    }
    public void init() {
        TableLayout tableLayout=(TableLayout) findViewById(R.id.programTable);
        createProgram();
        for (int i = 0; i < programs.size(); i++) {
            TableRow row = new TableRow(this);
            Button button = new Button(this);
            button.setText((i+1)+". "+programs.get(i).toString());
            row.addView(button);
            tableLayout.addView(row);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Button innerbutton=(Button) view;
                    int position = Character.getNumericValue(innerbutton.getText().charAt(0));
                    if (MyPrograms.expanded.get(position-1)==false) {
                        innerbutton.setText(position+". "+programs.get(position - 1).toStringExpanded());
                        MyPrograms.expanded.set(position-1, true);
                    }
                    else {
                        innerbutton.setText(position+". "+programs.get(position-1).toString());
                        MyPrograms.expanded.set(position-1, false);
                    }
                }
            });
        }
    }
    //for testing purposes
    public void createProgram() {
        Program program=new Program("Starting Strength");
        Week week=new Week("Week One");
        Day day=new Day("Legs");
        week.addDay(day);
        program.addWeek(week);
        programs.add(program);
        expanded.add(false);
    }
}
