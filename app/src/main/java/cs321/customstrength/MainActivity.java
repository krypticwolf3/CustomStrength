package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////
    // Create the user interface objects. //
    ////////////////////////////////////////

    // Text Here.
    TextView titleIntro;

    // Buttons Here.
    Button startWorkoutBtn;
    Button programsBtn;
    Button exerciseBtn;
    Button progressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////
        // Initialize the user interface objects. //
        ////////////////////////////////////////////

        // Text Here.
        titleIntro = (TextView) findViewById(R.id.homeTitleIntro);

        // Buttons Here.
        startWorkoutBtn = (Button) findViewById(R.id.startWorkoutBtn);
        programsBtn = (Button) findViewById(R.id.programsBtn);
        exerciseBtn = (Button) findViewById(R.id.exerciseBtn);
        progressBtn = (Button) findViewById(R.id.progressBtn);

    }

    public void exercises(View view) {
        //go to exercises page
        Intent exerciseIntent = new Intent("Open Exercises");
    }
    public void myPrograms(View view) {
        //go to myPrograms page
        Intent programsIntent = new Intent(this, MyPrograms.class);
        startActivity(programsIntent);
    }
    public void startWorkout(View view) {
        //go to startWorkout page
        Intent startIntent = new Intent("Open Start");
    }
    public void trackProgress(View view) {
        //go to trackProgress page
        Intent progressIntent = new Intent("Open Progress");
    }
}
