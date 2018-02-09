package com.markazuschlag.flourhour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.markazuschlag.flourhour.MainActivity.EXTRA_INCREMENT_BY;
import static com.markazuschlag.flourhour.MainActivity.EXTRA_TARGET;

public class CountActivity extends AppCompatActivity {
    private TextView mCountText;
    private ImageView mCountButton;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        mCountText = findViewById(R.id.countText);
        mCountButton = findViewById(R.id.countButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mTarget = extras.getString(EXTRA_TARGET);
            String mIncrementBy = extras.getString(EXTRA_INCREMENT_BY);
            mCount = Integer.parseInt(mTarget);
            mCountText.setText(mTarget);
        } else {
            mCount = 0;
        }


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
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
