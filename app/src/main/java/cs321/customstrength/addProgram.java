package cs321.customstrength;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class addProgram extends AppCompatActivity implements SearchView.OnQueryTextListener {
    TextView weekValue;
    LinearLayout weeks;
    TextView dayValue;
    LinearLayout mainLayout;

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
        TextView title = new TextView(this);
        title.setText("\nWeek:");

        currentWeek.addView(title);

        LinearLayout name = new LinearLayout(this);

        TextView nameText = new TextView(this);
        nameText.setText("Name:");
        name.addView(nameText);

        EditText nameInput = new EditText(this);
        nameInput.setInputType(1);
        name.addView(nameInput);

        currentWeek.addView(name);

        LinearLayout days = new LinearLayout(this);
        days.setOrientation(LinearLayout.VERTICAL);

        addDay(days);

        currentWeek.addView(days);

        weeks.addView(currentWeek);
    }

    public void deleteWeek() {
        weeks.removeView(weeks.getChildAt(weeks.getChildCount() - 1));
    }

    private void addDay(LinearLayout days) {
        for (int i = 0; i < Character.getNumericValue(dayValue.getText().charAt(0)); i++) {
            LinearLayout currentDay = new LinearLayout(this);
            currentDay.setOrientation(LinearLayout.VERTICAL);

            EditText nameInput = new EditText(this);
            nameInput.setInputType(1);
            nameInput.setText("Name of Day " + i);
            currentDay.addView(nameInput);

            Button addExercise = new Button(this);
            addExercise.setText("Add Exercise");
            currentDay.addView(addExercise);

            days.addView(currentDay);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        ArrayList<String> results = LoadExerciseData.searchExercises(query, LoadExerciseData.PRELOADED_EXERCISES);
        LinearLayout displayResults = new LinearLayout(this);
        for (int i = 0; i < results.size(); i++) {
            TextView result = new TextView(this);
            result.setText(results.get(i));
            displayResults.addView(result);
        }
        if (results.size() == 0) {
            TextView noResults = new TextView(this);
            noResults.setText(query);
            displayResults.addView(noResults);
        }
        mainLayout.addView(displayResults);
        TextView test = new TextView(this);
        test.setText(getApplicationInfo().dataDir);
        mainLayout.addView(test);
        return true;
    }

    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
}
