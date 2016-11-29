package cs321.customstrength;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

public class selectExercise extends AppCompatActivity implements SearchView.OnQueryTextListener{

    LinearLayout mainLayout;
    NumberPicker numberPicker;
    LinearLayout volumeAndIntensity;
    Switch setSwitch;
    int[] volume;
    int[] intensity;
    int displayResultsId;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);
        mainLayout=(LinearLayout) findViewById(R.id.mainLayout);

        //Number picker stuff
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(99);
        numberPicker.setValue(1);

        //For getting volume and intensity
        volumeAndIntensity = (LinearLayout) findViewById(R.id.volumeAndIntensity);
        setSwitch=(Switch) findViewById(R.id.setSwitch);

        displayResultsId=View.generateViewId();
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
        displayResults.setId(displayResultsId);
        mainLayout.addView(displayResults);
        for (int i = 0; i < results.size(); i++) {
            Button result = new Button(this);
            result.setText(results.get(i));
            result.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Button innerResult=(Button)view;
                    resultString=innerResult.getText().toString();
                    Button save=new Button(view.getContext());
                    mainLayout.addView(save);
                    save.setTag(resultString);
                    save.setText("Save");
                    save.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Button innerButton = (Button)view;
                            if (LoadExerciseData.PRELOADED_EXERCISES.get(innerButton.getTag()).type == "Cardio" || LoadExerciseData.PRELOADED_EXERCISES.get(innerButton.getTag()).type == "Stretching") {
                                volume=new int[volumeAndIntensity.getChildCount()/2];
                                for (int i = 1; i < volumeAndIntensity.getChildCount(); i += 2) {
                                    if (Character.isDigit(((EditText) volumeAndIntensity.getChildAt(i)).getText().charAt(0))) {
                                        volume[(i-1)/2] = Integer.parseInt(((EditText) volumeAndIntensity.getChildAt(i)).getText().toString());
                                    } else {
                                        volume[(i-1)/2] = -1;
                                    }
                                }
                            }
                            else {
                                volume=new int[volumeAndIntensity.getChildCount()/3];
                                intensity=new int[volumeAndIntensity.getChildCount()/3];
                                for (int i = 0; i < volumeAndIntensity.getChildCount(); i++) {
                                    if (i%3 != 0) {
                                        if (Character.isDigit(((EditText) volumeAndIntensity.getChildAt(i)).getText().charAt(0))) {
                                            if ((i-1)%3 == 0) {
                                                volume[(i-1)/3] = Integer.parseInt(((EditText) volumeAndIntensity.getChildAt(i)).getText().toString());
                                            }
                                            else {
                                                intensity[(i-2)/3] = Integer.parseInt(((EditText) volumeAndIntensity.getChildAt(i)).getText().toString());
                                            }
                                        }
                                        else {
                                            if ((i-1)%3 == 0) {
                                                volume[(i-1)/3] = -1;
                                            }
                                            else {
                                                intensity[(i-1)/3] = -1;
                                            }
                                        }
                                    }
                                }
                            }
                            Intent output=new Intent();
                            output.putExtra("Exercise", resultString);
                            output.putExtra("Intensity", intensity);
                            output.putExtra("Volume", volume);
                            output.putExtra("Sets", numberPicker.getValue());
                            setResult(RESULT_OK, output);
                            finish();
                        }
                    });
                    if (LoadExerciseData.PRELOADED_EXERCISES.get(innerResult.getText()).type == "Cardio") {
                        if (setSwitch.isChecked()) {
                            for (int i=0; i<numberPicker.getValue(); i++) {
                                TextView setNumber=new TextView(view.getContext());
                                setNumber.setText("Set "+(i+1));
                                volumeAndIntensity.addView(setNumber);

                                EditText volume=new EditText(view.getContext());
                                volume.setText("Minutes");
                                volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                                volumeAndIntensity.addView(volume);
                            }
                        }
                        else {
                            TextView setNumber=new TextView(view.getContext());
                            setNumber.setText("All Sets");
                            volumeAndIntensity.addView(setNumber);

                            EditText volume=new EditText(view.getContext());
                            volume.setText("Minutes");
                            volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                            volumeAndIntensity.addView(volume);
                        }
                        intensity = new int[]{-1};
                    }
                    else if(LoadExerciseData.PRELOADED_EXERCISES.get(innerResult.getText()).type == "Stretching") {
                        if (setSwitch.isChecked()) {
                            for (int i=0; i<numberPicker.getValue(); i++) {
                                TextView setNumber=new TextView(view.getContext());
                                setNumber.setText("Set "+(i+1));
                                volumeAndIntensity.addView(setNumber);

                                EditText volume=new EditText(view.getContext());
                                volume.setText("Reps/Seconds");
                                volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                                volumeAndIntensity.addView(volume);
                            }
                        }
                        else {
                            TextView setNumber=new TextView(view.getContext());
                            setNumber.setText("All Sets");
                            volumeAndIntensity.addView(setNumber);

                            EditText volume=new EditText(view.getContext());
                            volume.setText("Reps/Seconds");
                            volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                            volumeAndIntensity.addView(volume);
                        }
                        intensity=new int[]{-1};
                    }
                    else {
                        if (setSwitch.isChecked()) {
                            for (int i = 0; i<numberPicker.getValue(); i++) {
                                TextView setNumber=new TextView(view.getContext());
                                setNumber.setText("Set "+(i+1));
                                volumeAndIntensity.addView(setNumber);

                                EditText volume=new EditText(view.getContext());
                                volume.setText("Reps");
                                volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                                volumeAndIntensity.addView(volume);

                                EditText intensity=new EditText(view.getContext());
                                intensity.setText("Weight");
                                intensity.setInputType(InputType.TYPE_CLASS_NUMBER);
                                volumeAndIntensity.addView(intensity);
                            }
                        }
                        else {
                            TextView setNumber=new TextView(view.getContext());
                            setNumber.setText("All Sets");
                            volumeAndIntensity.addView(setNumber);

                            EditText volume=new EditText(view.getContext());
                            volume.setText("Reps");
                            volume.setInputType(InputType.TYPE_CLASS_NUMBER);
                            volumeAndIntensity.addView(volume);

                            EditText intensity=new EditText(view.getContext());
                            intensity.setText("Weight");
                            intensity.setInputType(InputType.TYPE_CLASS_NUMBER);
                            volumeAndIntensity.addView(intensity);
                        }
                    }
                    mainLayout.removeView(findViewById(displayResultsId));
                }
            });
            displayResults.addView(result);
        }
        if (results.size() == 0) {
            TextView noResults = new TextView(this);
            noResults.setText("No existing exercise called" + query +"...");
            displayResults.addView(noResults);
            Button createExercise=new Button(this);
            createExercise.setText("Create One");
        }
        return true;
    }

    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
}
