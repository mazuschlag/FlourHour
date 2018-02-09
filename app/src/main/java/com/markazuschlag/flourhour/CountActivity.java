package com.markazuschlag.flourhour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.markazuschlag.flourhour.MainActivity.EXTRA_INCREMENT_BY;
import static com.markazuschlag.flourhour.MainActivity.EXTRA_TARGET;

public class CountActivity extends AppCompatActivity {
    public static final String SAVE_COUNT = "com.markazuschlag.flourhour.save.COUNT";
    public static final String SAVE_DESCRIPTION = "com.markazuschlag.flourhour.save.DESCRIPTION";
    private TextView mCountText;
    private TextView mDescriptionText;
    private ImageView mCountButton;
    private int mCount;
    private String mTarget;
    private String mIncrementBy;
    private String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        mCountText = findViewById(R.id.countText);
        mDescriptionText = findViewById(R.id.descriptionTextView);
        mCountButton = findViewById(R.id.countButton);

        Bundle extras = getIntent().getExtras();
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(SAVE_COUNT);
            mTarget = String.valueOf(mCount);
            mIncrementBy = savedInstanceState.getString(SAVE_DESCRIPTION);

        } else if (extras != null) {
            mTarget = extras.getString(EXTRA_TARGET);
            mCount = Integer.parseInt(mTarget);
            mIncrementBy = extras.getString(EXTRA_INCREMENT_BY);

        } else {
            mCount = 10;
            mTarget = String.valueOf(mCount);
            mIncrementBy = getString(R.string.default_increment);
        }

        mDescription = mIncrementBy + getString(R.string.cups_left);
        mDescriptionText.setText(mDescription);
        mCountText.setText(mTarget);

        mCountButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mCountButton.setImageResource(R.drawable.ic_on_touch);
                        mCountText.setText(String.valueOf(--mCount));
                        break;
                    case MotionEvent.ACTION_UP:
                        mCountButton.setImageResource(R.drawable.ic_no_touch);
                        if (mCount <= 0) {
                            endCounting();
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_COUNT, mCount);
        outState.putString(SAVE_DESCRIPTION, mIncrementBy);

    }

    public void endCounting() {
        Intent intent = new Intent(this, FinishActivity.class);
        startActivity(intent);
    }
}
