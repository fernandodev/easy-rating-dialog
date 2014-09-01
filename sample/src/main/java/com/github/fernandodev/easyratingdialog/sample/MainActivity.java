package com.github.fernandodev.easyratingdialog.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;

public class MainActivity extends Activity {

  EasyRatingDialog easyRatingDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    easyRatingDialog = new EasyRatingDialog(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    easyRatingDialog.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    easyRatingDialog.showIfNeeded();
  }

  public void onClickRateNow(View view) {
    easyRatingDialog.rateNow();
  }

  public void onClickNeverReminder(View view) {
    easyRatingDialog.neverReminder();
  }

  public EasyRatingDialog getEasyRatingDialog() {
    return easyRatingDialog;
  }
}
