package cs321.customstrength;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class addProgram extends AppCompatActivity {
    TextView weekValue;
    LinearLayout weeks;
    TextView dayValue;
    LinearLayout mainLayout;
    ArrayList<Week> weeksArray=new ArrayList<Week>();
    ExerciseData exercise;
    Day day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        weekValue = (TextView) findViewById(R.id.weekValue);
        weekValue.setText("0");
        weeks = (LinearLayout) findViewById(R.id.weeks);
        dayValue = (TextView) findViewById(R.id.dayValue);
        dayValue.setText("0");
        mainLayout = (LinearLayout) findViewById(R.id.activity_add_program);
    }

    public void weekIncrement(View view) {
        int value = Character.getNumericValue(weekValue.getText().charAt(0));
        if (value < 9) {
            weekValue.setText(Integer.toString(value + 1));
            createWeek();
        }
    }

    public void weekDecrement(View view) {
        int value = Character.getNumericValue(weekValue.getText().charAt(0));
        if (value > 0) {
            weekValue.setText(Integer.toString(value - 1));
            deleteWeek();
        }
    }

    public void dayIncrement(View view) {
        int value = Character.getNumericValue(dayValue.getText().charAt(0));
        if (value < 7) {
            dayValue.setText(Integer.toString(value + 1));
        }
    }

    public void dayDecrement(View view) {
        int value = Character.getNumericValue(dayValue.getText().charAt(0));
        if (value > 0) {
            dayValue.setText(Integer.toString(value - 1));
        }
    }

    public void createWeek() {
        LinearLayout currentWeek = new LinearLayout(this);
        currentWeek.setOrientation(LinearLayout.VERTICAL);

        EditText nameInput = new EditText(this);
        nameInput.setInputType(1);
        nameInput.setText("Name of Week");

        currentWeek.addView(nameInput);

        LinearLayout days = new LinearLayout(this);
        days.setOrientation(LinearLayout.VERTICAL);

        Week week=new Week("First One");
        week.days=addDay(days);

        currentWeek.addView(days);

        weeks.addView(currentWeek);
    }

    public void deleteWeek() {
        weeks.removeView(weeks.getChildAt(weeks.getChildCount() - 1));
    }

    private ArrayList<Day> addDay(LinearLayout days) {
        ArrayList<Day> daysArray=new ArrayList<Day>();
        //for number of days in the week
        for (int i = 0; i < Character.getNumericValue(dayValue.getText().charAt(0)); i++) {
            //Linear layout for the day
            LinearLayout currentDay = new LinearLayout(this);
            currentDay.setOrientation(LinearLayout.VERTICAL);

            //Name of the day
            EditText nameInput = new EditText(this);
            nameInput.setInputType(1);
            nameInput.setText("Name of Day " + (i+1));
            currentDay.addView(nameInput);

            Button addExercise=new Button(this);
            addExercise.setText("Add Exercise");
            addExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //empty out exercise
                    exercise=null;
                    Intent searchIntent=new Intent(view.getContext(), selectExercise.class);
                    startActivityForResult(searchIntent, 1);

                    //onActivityResult(1, );
                    day.exercises.add(exercise);
                }
            });
            currentDay.addView(addExercise);

            days.addView(currentDay);

            daysArray.add(day);
        }
        return daysArray;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            String toAdd=data.getStringExtra("Exercise");
            //make this into an exercise not exerciseData
            //also get sets, reps, and weight from this page
            exercise=LoadExerciseData.PRELOADED_EXERCISES.get(toAdd);
    }
}
