package com.mycompany.tamikowilliamsinvestmentcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Miko on 3/8/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
    public void testCalculator() {
        MainActivity activity = getActivity();
        final EditText paymentEditText = (EditText) activity.findViewById(R.id.payment_edit_text);
        final EditText rateEditText = (EditText) activity.findViewById(R.id.rate_edit_text);
        final EditText periodEditText = (EditText) activity.findViewById(R.id.period_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                paymentEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("1000.00");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                rateEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("3.4");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                periodEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("5");
        getInstrumentation().waitForIdleSync();

        // Tap "Calculate" button
        // ----------------------

        Button calculateButton =
                (Button) activity.findViewById(R.id.calculate_button);

        TouchUtils.clickView(this, calculateButton);

        TextView calculateMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = calculateMessage.getText().toString();
        assertEquals("$5351.76", actualText);

    }




}
