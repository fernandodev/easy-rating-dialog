package com.github.fernandodev.easyratingdialog.sample.tests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import com.github.fernandodev.easyratingdialog.sample.MainActivity;
import com.github.fernandodev.easyratingdialog.sample.R;

/**
 * Created by fernando on 9/1/14.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

  MainActivity mActivity;
  SharedPreferences mPreferences;

  private static final String PREFS_NAME = "erd_rating";
  private static final String KEY_WAS_RATED = "KEY_WAS_RATED";
  private static final String KEY_NEVER_REMINDER = "KEY_NEVER_REMINDER";

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    Intent intent = new Intent(
        getInstrumentation().getTargetContext(),
        MainActivity.class
    );
    startActivity(intent, null, null);
    mActivity = getActivity();

    mPreferences = mActivity.getSharedPreferences(PREFS_NAME, 0);
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    mPreferences.edit().clear().commit();
  }

  public void testPerformRateNow() {
    int buttonId = R.id.rate_now;
    Button button = (Button) mActivity.findViewById(buttonId);
    assertNotNull("Button not allowed to be null", button);

    button.performClick();

    Intent triggeredIntent = getStartedActivityIntent();
    assertNotNull("Intent was null", triggeredIntent);

    Uri data = triggeredIntent.getData();
    assertEquals(
        "Incorrect data passed via the intent",
        Uri.parse("https://play.google.com/store/apps/details?id=com.github.fernandodev.easyratingdialog.sample"),
        data
    );

    boolean wasRated = mPreferences.getBoolean(KEY_WAS_RATED, false);
    assertEquals("User was rated but no value was stored", true, wasRated);
  }

  public void testPerformNeverReminder() {
    int buttonId = R.id.never_reminder;
    Button button = (Button) mActivity.findViewById(buttonId);
    assertNotNull("Button not allowed to be null", button);

    button.performClick();

    boolean neverReminder = mPreferences.getBoolean(KEY_NEVER_REMINDER, false);
    assertEquals("User wants to never be remembered but no value was stored", true, neverReminder);
  }
}
