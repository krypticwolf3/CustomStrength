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
    TextView dayValue;
    LinearLayout mainLayout;

    LinearLayout weeks;//this is the container for all weeks in the program
    int weekCounter=0;
    int dayCounter=0;
    ArrayList<ArrayList<Integer>> dayIds=new ArrayList<>();
    ArrayList<Integer> weekIds=new ArrayList<>();

    ArrayList<Week> weeksArray=new ArrayList<Week>();
    Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        //button stuff and just finding views, don't worry about this
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
        //Create the week being worked on
        LinearLayout currentWeek = new LinearLayout(this);
        currentWeek.setOrientation(LinearLayout.VERTICAL);

        //Add the name input for the week
        EditText nameInput = new EditText(this);
        nameInput.setInputType(1);
        nameInput.setText("Name of Week");
        currentWeek.addView(nameInput);
        weeks.addView(currentWeek);

        //Create the set of days for the week
        LinearLayout days = new LinearLayout(this);
        days.setOrientation(LinearLayout.VERTICAL);
        weekIds.add(View.generateViewId());
        days.setId(weekIds.get(weekCounter));
        //add the days to the week view
        currentWeek.addView(days);

        //Create the actual week data
        Week week=new Week();
        //Call the addDay method to get the data for the ArrayList<Day> in the week and create and update the view
        week.days=addDay();
        //add the week to the array of weeks
        weeksArray.add(week);

        //add the week view to the main layout
        weekCounter++;
    }

    public void deleteWeek() {
        weeks.removeView(weeks.getChildAt(weeks.getChildCount() - 1));
        weekCounter--;
    }

    private ArrayList<Day> addDay() {
        //create an array list of days to add to the week
        ArrayList<Day> daysArrayData=new ArrayList<Day>();
        //create an array list of day view ids
        ArrayList<Integer> dayIdsLocal=new ArrayList<>();
        //for number of days in the week
        for (int i = 0; i < Character.getNumericValue(dayValue.getText().charAt(0)); i++) {
            //Linear layout for the day
            LinearLayout currentDay = new LinearLayout(this);
            currentDay.setOrientation(LinearLayout.VERTICAL);
            dayIdsLocal.add(View.generateViewId());
            currentDay.setId(dayIdsLocal.get(i));

            Day day=new Day("Day "+(i+1));
            //add the day to the array of days for the actual data
            daysArrayData.add(day);

            //Name of the day
            EditText nameInput = new EditText(this);
            nameInput.setInputType(1);
            nameInput.setText("Name of Day " + (i+1));
            currentDay.addView(nameInput);

            //Add buttons for adding exercises to each day
            Button addExercise = new Button(this);
            addExercise.setText("Add Exercise");
            addExercise.setTag((weekCounter*10)+i);
            currentDay.addView(addExercise);

            //add the day view to the week view
            LinearLayout week=(LinearLayout)weeks.findViewById(weekIds.get(weekCounter));
            week.addView(currentDay);

            addExercise.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Button innerButton=(Button)view;
                    //Get result from search intent
                    Intent searchIntent=new Intent(view.getContext(), selectExercise.class);
                    startActivityForResult(searchIntent, (int)innerButton.getTag());
                }
            });
            dayCounter++;
        }
        dayIds.add(dayIdsLocal);
        return daysArrayData;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null) {
            String stringToAdd = data.getStringExtra("Exercise");
            //If intensity or volume<0, there is no user inputted intensity or volume
            int[] intensity = data.getIntArrayExtra("Intensity");
            int[] volume = data.getIntArrayExtra("Volume");
            //If sets==0, it is Cardio
            int sets = data.getIntExtra("Sets", 0);
            //get sets, reps, and weight from this page
            //make the string into exercise data
            ExerciseData exerciseData = LoadExerciseData.PRELOADED_EXERCISES.get(stringToAdd);
            //make the exercise data into an exercise
            exercise = LoadExerciseData.addPreloadedExercise(exerciseData, volume, intensity, sets);
            //exercise should have content now from getting the result
            weeksArray.get(requestCode / 10).days.get(requestCode % 10).exercises.add(exercise);
            TextView text = new TextView(this);
            text.setText(exercise.name);
            LinearLayout currentDay = (LinearLayout) weeks.findViewById(dayIds.get(requestCode / 10).get(requestCode % 10));
            currentDay.addView(text);
        }
    }
    public void saveProgram(View view) {
        Program toAdd=new Program("Figure out names later");
        toAdd.weeks=weeksArray;
        MyPrograms.programs.add(toAdd);
        MyPrograms.expanded.add(false);
        Intent returnIntent=new Intent(this, MyPrograms.class);
        startActivity(returnIntent);
        finish();
    }
}
