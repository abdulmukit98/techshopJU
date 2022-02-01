package edu.cseju.orderpcb;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.test.rule.*;
import static androidx.test.espresso.Espresso.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import org.junit.Rule;
import org.junit.Test;

/**
 * Instrumented Test work in API level. thats why different from  Unit Test
 */
public class MainActivityInstrumentedTest
{
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Create our own IntentTestRule For MainActivitys Intent
     */
    public static class IntentsTestRule extends ActivityTestRule<MainActivity>
    {
        public IntentsTestRule(Class<MainActivity> activityClass)
        {
            super(activityClass);
        }
    }

    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule(MainActivity.class);

    /**
     * Activity testing
     * OnCreate method testing
     *
     * An object of MainActivity is created to perform testing
     * assertThat help to match our view with given one.
     * assertEquals only check for return type, wher assertThat work API level
     */
    @Test
    public void onCreateInstrumented() throws Exception
    {
        MainActivity mainActivity = rule.getActivity();
        RadioGroup rgLayer = mainActivity.findViewById(R.id.rgroupLayer);
        assertThat(rgLayer, notNullValue());
        assertThat(rgLayer, instanceOf(RadioGroup.class));

        RadioGroup rgMasking = mainActivity.findViewById(R.id.rgroupMasking);
        assertThat(rgMasking, notNullValue());
        assertThat(rgLayer, instanceOf(RadioGroup.class));

        Button btnAdd = mainActivity.findViewById(R.id.btnAdd);
        assertThat(btnAdd, instanceOf(Button.class));
        Button btnSub = mainActivity.findViewById(R.id.btnSub);
        assertThat(btnSub, instanceOf(Button.class));
        Button btnUpload = mainActivity.findViewById(R.id.btnUpload);
        assertThat(btnUpload, instanceOf(Button.class));
        Button btnCart = mainActivity.findViewById(R.id.btnCart);
        assertThat(btnCart, instanceOf(Button.class));

        EditText edtWidth = mainActivity.findViewById(R.id.edtWidth);
        assertThat(edtWidth, instanceOf(EditText.class));
        EditText edtHeight = mainActivity.findViewById(R.id.edtHeight);
        assertThat(edtHeight, instanceOf(EditText.class));

        DatabaseReference databaseReference = mainActivity.databaseReference;
        assertThat(databaseReference, notNullValue());
        assertThat(databaseReference, instanceOf(DatabaseReference.class));
        StorageReference storageReference = mainActivity.storageReference;
        assertThat(storageReference, notNullValue());
        assertThat(storageReference, instanceOf(StorageReference.class));
    }

    /**
     * This test case check package name of activity with our expected one
     * @throws Exception arise if a nonvalid package detected
     */
    @Test
    public void onContextPackage() throws Exception
    {
        MainActivity mainActivity = rule.getActivity();
        Context context = rule.getActivity().getApplicationContext();
        assertEquals("edu.cseju.orderpcb", context.getPackageName());
    }


    @Test
    public void onCheckedChanged()
    {
        MainActivity mainActivity = rule.getActivity();

    }

    @Test
    public void uploadSchematic()
    {
        MainActivity mainActivity = rule.getActivity();
        //Intent intent = intentsTestRule.getActivity().getIntent();
        //Intent intent = new Intent();
        //intent.setType(Intent.ACTION_GET_CONTENT);
        //intent.setAction("*/*");
        //Intent intent = rule.getActivity()
    }


}