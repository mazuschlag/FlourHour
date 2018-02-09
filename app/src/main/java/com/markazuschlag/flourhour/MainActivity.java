package com.markazuschlag.flourhour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_TARGET = "com.markazuschlag.flourhour.extra.TARGET";
    public static final String EXTRA_INCREMENT_BY = "com.markazuschlag.flourhour.extra.INCREMENTBY";
    public static final String SAVE_TARGET = "com.markazuschlag.flourhour.save.TARGET";
    public static final String SAVE_INCREMENT_BY = "com.markazuschlag.flourhour.save.INCREMENTBY";
    private EditText mTargetEditText;
    private Spinner mIncrementSpin;
    private String mTarget;
    private String mIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTargetEditText = findViewById(R.id.targetEditText);

        mIncrementSpin = findViewById(R.id.incrementSpinner);
        if (mIncrementSpin != null) {
            mIncrementSpin.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> incrementAdapt = ArrayAdapter.createFromResource(this,
                R.array.pref_increments, android.R.layout.simple_spinner_item);

        incrementAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (mIncrementSpin != null) {
            mIncrementSpin.setAdapter(incrementAdapt);
        }

        if (savedInstanceState != null) {
            String currentTarget = savedInstanceState.getString(SAVE_TARGET);
            int mIncrementIndex = savedInstanceState.getInt(SAVE_INCREMENT_BY);

            mTargetEditText.setText(currentTarget);
            mIncrementSpin.setSelection(mIncrementIndex);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Get the selected item and trigger the showText function
        mIncrement = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Nothing to see here
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_TARGET, mTargetEditText.getText().toString());
        outState.putInt(SAVE_INCREMENT_BY, mIncrementSpin.getSelectedItemPosition());
    }

    public void startCounting(View view) {
        mIncrement = mIncrementSpin.getSelectedItem().toString();
        mTarget = mTargetEditText.getText().toString();

        Bundle extras = new Bundle();
        extras.putString(EXTRA_TARGET, mTarget);
        extras.putString(EXTRA_INCREMENT_BY, mIncrement);

        Intent intent = new Intent (this, CountActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
