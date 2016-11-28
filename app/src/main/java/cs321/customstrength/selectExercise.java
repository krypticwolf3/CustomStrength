package cs321.customstrength;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class selectExercise extends AppCompatActivity implements SearchView.OnQueryTextListener{

    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);
        mainLayout=(LinearLayout) findViewById(R.id.mainLayout);
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
        displayResults.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < results.size(); i++) {
            Button result = new Button(this);
            result.setText(results.get(i));
            result.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Button innerResult=(Button)view;
                    Intent output=new Intent();
                    output.putExtra("Exercise", innerResult.getText());
                    output.putExtra("Intensity", 2);
                    output.putExtra("Volume", 10);
                    output.putExtra("Sets", 3);
                    setResult(RESULT_OK, output);
                    finish();
                }
            });
            displayResults.addView(result);
        }
        if (results.size() == 0) {
            TextView noResults = new TextView(this);
            noResults.setText(query);
            displayResults.addView(noResults);
        }
        mainLayout.addView(displayResults);
        return true;
    }

    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
}
