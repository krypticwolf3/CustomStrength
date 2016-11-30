package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StartWorkout extends AppCompatActivity {

    private Program currentProgram;
    private String currentProgName;
    private int currentProgNumber;
    private int currentWeek;
    private int currentDay;
    private TextView showInfo;

    private ListView myListView;
    private ArrayAdapter<Week> myLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent workoutIntent = getIntent();

        showInfo = (TextView) findViewById(R.id.workoutInfoText);

        if (workoutIntent != null) {
            Bundle extraInfo = workoutIntent.getExtras();

            currentProgNumber = extraInfo.getInt("CURRENT_PROGRAM_NUMBER");
            currentWeek = extraInfo.getInt("CURRENT_WEEK");
            currentDay = extraInfo.getInt("CURRENT_DAY");

            // Track which program is currently selected
            if ( (currentProgNumber >= 0) && (currentProgNumber < MyPrograms.programs.size()) ) {
                currentProgram = MyPrograms.programs.get(currentProgNumber);
            } else {

            }
            showInfo.setText(
                    "Program: " + currentProgName
                    + "\nWeek: " + currentWeek
                    + "\nDay: " + currentDay
                    + "\nProgram Number: " + currentProgNumber);
        }

        //////////////////////////////////
        // Set up the list view layout. //
        //////////////////////////////////

        if (currentProgram != null) {
            myLayoutAdapter = new ArrayAdapter<Week>(this,
                    android.R.layout.simple_list_item_1, currentProgram.weeks);

            myListView = (ListView) findViewById(R.id.currentProgramWeeksList);
            myListView.setAdapter(myLayoutAdapter);

            myListView.setOnItemClickListener(listItemClickedHandler);
        } else {
            Toast.makeText(getApplicationContext(),
                    "ERROR: CURRENT PROGRAM WAS NULL", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Define the private functions:
     * listItemClickedHandler - handles the expandable view item clicks.
     */


    //
    private AdapterView.OnItemClickListener listItemClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            Toast.makeText(StartWorkout.this, currentProgram.weeks.get(position).toString(), Toast.LENGTH_LONG).show();
        }
    };
}
