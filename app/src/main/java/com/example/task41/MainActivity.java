package com.example.task41;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private String mSpinnerLabel = "";
    private String aux;
//    private String[] yourArray = getResources().getStringArray(R.array.labels_array);
//    private String yourString = yourArray[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the spinner.
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }
    public void showText(View view) {
        EditText editText = findViewById(R.id.editText_main);
        if (editText != null) {
            String showString = (editText.getText().toString() + " - " + mSpinnerLabel);
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
        aux =(adapterView.getItemAtPosition(0).toString());
        if (!mSpinnerLabel.equals(aux)) {
            showText(view);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, "onNothingSelected: ");
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle(R.string.alert_title);
        myAlertBuilder.setMessage(R.string.alert_message);
        // Add the buttons.
        myAlertBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), R.string.pressed_ok,
                        Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked the CANCEL button.
                Toast.makeText(getApplicationContext(), R.string.pressed_cancel,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Create and show the AlertDialog.
        myAlertBuilder.show();

    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.date_picker));
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.time_picker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        // Assign the concatenated strings to dateMessage.
        String dateMessage = (month_string + "/" +
                day_string + "/" + year_string);
        Toast.makeText(this, getString(R.string.date) + dateMessage,
                Toast.LENGTH_SHORT).show();
    }
    public void processTimePickerResult(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);
        Toast.makeText(this, getString(R.string.time) + timeMessage, Toast.LENGTH_SHORT).show();
    }

}