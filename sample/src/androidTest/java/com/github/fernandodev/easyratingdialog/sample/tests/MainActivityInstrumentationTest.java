package com.github.fernandodev.easyratingdialog.sample.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.github.fernandodev.easyratingdialog.sample.MainActivity;

/**
 * Created by fernando on 9/1/14.
 */
public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity> {

  EasyRatingDialog easyRatingDialog;
  MainActivity mActivity;

  public MainActivityInstrumentationTest() {
    super(MainActivity.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    mActivity = getActivity();
    easyRatingDialog = mActivity.getEasyRatingDialog();
  }

  public void testShowDialog() {
    for (int i = 0; i < 6; ++i)
      getInstrumentation().callActivityOnStart(mActivity);

    getInstrumentation().callActivityOnResume(mActivity);
    getInstrumentation().waitForIdleSync();

    boolean showing = easyRatingDialog.isShowing();
    assertEquals("The condition was reached and dialog should be opened", true, showing);
  }

  public void testPossibleBadTokenException() {
    for (int i = 0; i < 6; ++i)
      getInstrumentation().callActivityOnStart(mActivity);

    getInstrumentation().callActivityOnResume(mActivity);
    getInstrumentation().waitForIdleSync();

    getInstrumentation().callActivityOnStop(mActivity);
    getInstrumentation().callActivityOnRestart(mActivity);

    boolean showing = easyRatingDialog.isShowing();
    assertEquals("The condition was reached and dialog should be opened", true, showing);
  }
}
