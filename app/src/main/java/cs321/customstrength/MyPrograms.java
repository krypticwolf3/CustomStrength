package cs321.customstrength;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class MyPrograms extends Activity {

    static ArrayList<Program> programs = new ArrayList<Program>();
    static ArrayList<Boolean> expanded=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);
        Intent programsIntent=getIntent();
        init();
    }
    public void init() {
        //the LinearLayout to add programs to
        LinearLayout programLayout=(LinearLayout) findViewById(R.id.programLayout);
        //for testing purposes
        createProgram();
        //create the buttons that display the programs
        for (int i = 0; i < programs.size(); i++) {
            //create a new LinearLayout to put the buttons in
            LinearLayout program = new LinearLayout(this);
            //create the button to display the program
            Button button = new Button(this);
            //set the text
            button.setText((i+1)+". "+programs.get(i).toString());
            //add button to row
            program.addView(button);
            //add row to table
            programLayout.addView(program);
            //onClick
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Button innerbutton=(Button) view;
                    //find which program it is
                    int position = Character.getNumericValue(innerbutton.getText().charAt(0));
                    //if it is collapsed, expand it
                    if (MyPrograms.expanded.get(position-1)==false) {
                        innerbutton.setText(position+". "+programs.get(position - 1).toStringExpanded());
                        MyPrograms.expanded.set(position-1, true);
                    }
                    //if it is expanded, collapse it
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
    //onClick for add new program
    public void addProgram(View view) {
        Intent createIntent=new Intent(this, addProgram.class);
        startActivity(createIntent);
    }
}
