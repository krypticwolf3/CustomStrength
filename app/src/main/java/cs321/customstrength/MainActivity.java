package cs321.customstrength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exercises(View view) {
        //go to exercises page
    }
    public void myPrograms(View view) {
        //go to myPrograms page
    }
    public void startWorkout(View view) {
        //go to startWorkout page
    }
    public void trackProgress(View view) {
        //go to trackProgress page
    }
}
