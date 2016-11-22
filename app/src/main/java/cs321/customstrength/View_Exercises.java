package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View_Exercises extends AppCompatActivity {
    private static final int NUM_COLS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__exercises);

        Button done1 = (Button) (findViewById(R.id.done1));
        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_startWorkout_page = new Intent(View_Exercises.this, MainActivity.class);
                startActivity(go_to_startWorkout_page);
            }
        });

        final LinearLayout lm = (LinearLayout) findViewById(R.id.test1);

        // create the layout params that will be used to define how
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);

        for (int j = 0; j <= NUM_COLS; j++) {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView
            TextView program = new TextView(this);
            program.setText(" Program" + j + "    ");
            ll.addView(program);

            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setId(j + 1);
            btn.setText("View Exercises");
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            final int index = j;
            // Set click listener for button

            // Button select_program = (Button) (findViewById(R.id.j+1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", "index :" + index);
                    Intent exercise = new Intent(View_Exercises.this, Exercise_List.class);
                    startActivity(exercise);
                    //Log.i(TAG, "Button was clicked (Select_Program)");
                }
            });
                   /* Toast.makeText(getApplicationContext(),
                            "Go to program " + index,
                            Toast.LENGTH_LONG).show();*/

            //Add button to LinearLayout
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }
    }
}
