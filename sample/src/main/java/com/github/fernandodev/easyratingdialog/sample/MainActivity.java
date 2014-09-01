package com.github.fernandodev.easyratingdialog.sample;

import android.app.Activity;
import android.os.Bundle;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.github.fernandodev.easyratingdialog.sample.R;

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
}
