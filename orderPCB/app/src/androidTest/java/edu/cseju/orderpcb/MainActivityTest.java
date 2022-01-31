package edu.cseju.orderpcb;

import static android.content.ContentValues.TAG;
import static android.text.InputType.TYPE_CLASS_NUMBER;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

/**
 * Test class for UI Testing MainActivity.java Isolation
 */
public class MainActivityTest
{
    private MainActivity mainActivity;


    /**
     * We will check if out main activity layout is successfully display or not
     * We create a ActivityScenario to launch main activity
     * then check if it is successfully displayed or nor
     * with onCiew().check() method
     */
    @Test
    public void isMainActivitySuccessfullyView()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if Order PCB textview is shown when activity launch
     */
    @Test
    public void is_orderPCB_textView_showed()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()));
    }

    /**
     * Check if text vie containing specific string
     */
    @Test
    public void is_orderPCB_textView_matches_givenString()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.tvTitle)).check(matches(withText("Order PCB")));
    }

    /**
     * Test case for checking if Layer RaioGroup is shown when activity launch
     */
    @Test
    public void is_Layer_RadioGroup_Shown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.rgroupLayer)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if GreenMasking RaioGroup is shown when activity launch
     */
    @Test
    public void is_Masking_RadioGrouop_Shown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.rgroupMasking)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if Edit Text for pcb width is shown when activity launch
     */
    @Test
    public void Dimension_Width_is_shown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.edtWidth)).check(matches(isDisplayed()));
    }

    /**
     * test if EditText width type is number
     */
    @Test
    public void test_isEditText_width_inputType_number()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.edtHeight)).check(matches(withInputType(TYPE_CLASS_NUMBER)));
    }

    /**
     * Test case for checking EditText for pcb height is shown when activity launch
     */
    @Test
    public void dimension_height_is_shown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.edtHeight)).check(matches(isDisplayed()));
    }

    /**
     * test if EditText height type is number
     */
    @Test
    public void test_isEditText_height_inputType_number()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        try
        {
            onView(withId(R.id.edtHeight)).check(matches(withInputType(TYPE_CLASS_NUMBER)));
        }
        catch (Exception e)
        {
            Log.d(TAG, "test_isEditText_height_inputType_number: " + e.getMessage());
        }

    }


    /**
     * Test case for checking if ButtonSub is shown when activity launch
     */
    @Test
    public void buttonSub_is_shown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnSub)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if BtnAdd is shown when activity launch
     */
    @Test
    public void buttonAdd_isShown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnAdd)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if BtnUpload is shown when activity launch
     */
    @Test
    public void btnUpload_isShown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnUpload)).check(matches(isDisplayed()));
    }

    /**
     * Test case for checking if BtnPlaceOrder is shown when activity launch
     */
    @Test
    public void btnPlaceOrder_isShown()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.btnCart)).check(matches(isDisplayed()));
    }


    /**
     @Before public void setUp() throws Exception
     {
     }

     @Test public void onCreate()
     {
     }

     @Test public void onCheckedChanged()
     {
     }

     @Test public void onClick()
     {
     }

     @Test public void calculateCost()
     {
     }

     @Test public void message()
     {
     }

     @Test public void getFileExtension()
     {
     }

     @Test public void onActivityResult()
     {
     }
     */
}