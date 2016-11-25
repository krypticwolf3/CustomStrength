package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////
    // Create the user interface objects. //
    ////////////////////////////////////////


    // Buttons Here.
    Button startWorkoutBtn;
    Button programsBtn;
    Button exerciseBtn;

    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////
        // Initialize the user interface objects. //
        ////////////////////////////////////////////


        // Buttons Here.
        startWorkoutBtn = (Button) findViewById(R.id.startWorkoutBtn);
        programsBtn = (Button) findViewById(R.id.programsBtn);
        exerciseBtn = (Button) findViewById(R.id.exerciseBtn);

        mContext = this;
    }

    public void exercises(View view) {
        //go to exercises page
        Intent exerciseIntent = new Intent(this, View_Exercises.class);
        startActivity(exerciseIntent);
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

    // returns a Context that can be called
    public static Context getContext() {
        return mContext;
    }
}
