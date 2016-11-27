package cs321.customstrength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartWorkout extends AppCompatActivity {

    private Program currentProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);
    }
}
