package br.fernandodev.easyratingdialog.sample;

import android.app.Activity;
import android.os.Bundle;

import br.fernandodev.easyratingdialog.library.EasyRatingDialog;

public class MainActivity extends Activity {

  EasyRatingDialog ratingDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ratingDialog = new EasyRatingDialog(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    ratingDialog.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    ratingDialog.showIfNeeded(this);
  }
}
