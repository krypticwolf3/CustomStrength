package cs321.customstrength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class addProgram extends AppCompatActivity {
    TextView weekValue;
    LinearLayout weeks;
    TextView dayValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        weekValue=(TextView) findViewById(R.id.weekValue);
        weekValue.setText("0");
        weeks=(LinearLayout) findViewById(R.id.weeks);
        dayValue=(TextView) findViewById(R.id.dayValue);
        dayValue.setText("0");
    }
    public void weekIncrement(View view) {
        int value=Character.getNumericValue(weekValue.getText().charAt(0));
        if (value<9) {
            weekValue.setText(Integer.toString(value + 1));
            createWeek();
        }
    }
    public void weekDecrement(View view) {
        int value=Character.getNumericValue(weekValue.getText().charAt(0));
        if (value>0) {
            weekValue.setText(Integer.toString(value-1));
            deleteWeek();
        }
    }

    public void dayIncrement(View view) {
        int value=Character.getNumericValue(dayValue.getText().charAt(0));
        if (value<7) {
            dayValue.setText(Integer.toString(value + 1));
        }
    }
    public void dayDecrement(View view) {
        int value=Character.getNumericValue(dayValue.getText().charAt(0));
        if (value>0) {
            dayValue.setText(Integer.toString(value-1));
        }
    }

    public void createWeek() {
        LinearLayout currentWeek=new LinearLayout(this);
        currentWeek.setOrientation(LinearLayout.VERTICAL);
        TextView title=new TextView(this);
        title.setText("\nWeek:");

        currentWeek.addView(title);

        LinearLayout name=new LinearLayout(this);

        TextView nameText=new TextView(this);
        nameText.setText("Name:");
        name.addView(nameText);

        EditText nameInput=new EditText(this);
        nameInput.setInputType(1);
        name.addView(nameInput);

        currentWeek.addView(name);

        LinearLayout days=new LinearLayout(this);
        days.setOrientation(LinearLayout.VERTICAL);

        addDay(days);

        currentWeek.addView(days);

        weeks.addView(currentWeek);
    }
    public void deleteWeek() {
        weeks.removeView(weeks.getChildAt(weeks.getChildCount() - 1));
    }

    private void addDay(LinearLayout days) {
        for (int i=0; i<Character.getNumericValue(dayValue.getText().charAt(0)); i++) {
            LinearLayout currentDay=new LinearLayout(this);
            currentDay.setOrientation(LinearLayout.VERTICAL);

            EditText nameInput=new EditText(this);
            nameInput.setInputType(1);
            nameInput.setText("Name of Day "+i);
            currentDay.addView(nameInput);

            Button addExercise=new Button(this);
            addExercise.setText("Add Exercise");
            currentDay.addView(addExercise);

            days.addView(currentDay);
        }
    }
}
