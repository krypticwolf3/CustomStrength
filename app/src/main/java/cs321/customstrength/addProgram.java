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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_program);
        weekValue=(TextView) findViewById(R.id.weekValue);
        weekValue.setText("0");
        weeks=(LinearLayout) findViewById(R.id.weeks);
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
    public void createWeek() {
        TextView title=new TextView(this);
        title.setText("\nWeek:");

        weeks.addView(title);

        LinearLayout name=new LinearLayout(this);

        TextView nameText=new TextView(this);
        nameText.setText("Name:");
        name.addView(nameText);

        EditText nameInput=new EditText(this);
        nameInput.setInputType(1);
        name.addView(nameInput);

        weeks.addView(name);

        LinearLayout numDays=new LinearLayout(this);

        TextView daysQuestion=new TextView(this);
        daysQuestion.setText("How many different days in the week? ");
        numDays.addView(daysQuestion);

        Button minusButton=new Button(this);
        minusButton.setWidth(32);
        minusButton.setHeight(32);
        minusButton.setText("-");
        numDays.addView(minusButton);

        TextView daysText=new TextView(this);
        daysText.setText("0");
        numDays.addView(daysText);

        Button plusButton=new Button(this);
        plusButton.setWidth(32);
        plusButton.setHeight(32);
        plusButton.setText("+");
        numDays.addView(plusButton);

        weeks.addView(numDays);
    }
    public void deleteWeek() {
        for (int i=0; i<3; i++) {
            weeks.removeView(weeks.getChildAt(weeks.getChildCount() - 1));
        }
    }
}
