package cs321.customstrength;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import java.io.File;

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
        // BRANDON: I WILL HAVE TO ADD MORE TO THIS TO MAKE IT NOT CONSTANTLY OVERWRITE
        File f1 = new File(getContext().getFilesDir(), "ExerciseDataFinal.txt");
        File f2 = new File(getContext().getFilesDir(), "CustomExerciseData.txt");
        // writes new files into internal storage if it doesn't already exist
        // this is how we read CustomExerciseData
        if(!f1.exists() && !f2.exists()){
            LoadExerciseData.writeFilesIntoStorage();
        }
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