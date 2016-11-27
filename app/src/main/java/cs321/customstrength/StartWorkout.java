package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StartWorkout extends AppCompatActivity {

    private Program currentProgram;
    private int currentWeek;
    private int currentDay;
    private TextView showInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent workoutIntent = getIntent();

        showInfo = (TextView) findViewById(R.id.workoutInfoText);

        if (workoutIntent != null) {
            Bundle extraInfo = workoutIntent.getExtras();

            currentWeek = extraInfo.getInt("CURRENT_WEEK");
            currentDay = extraInfo.getInt("CURRENT_DAY");

            showInfo.setText("Program: "
                    + extraInfo.getString("PROGRAM_NAME")
                    + "\nWeek: " + currentWeek
                    + "\nDay: " + currentDay);
        }
    }
}
