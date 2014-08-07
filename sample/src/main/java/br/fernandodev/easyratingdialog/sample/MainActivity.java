package br.fernandodev.easyratingdialog.sample;

import android.app.Activity;
import android.os.Bundle;

import br.fernandodev.easyratingdialog.library.EasyRatingDialog;

public class MainActivity extends Activity {

  EasyRatingDialog easyRatingDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    easyRatingDialog = new EasyRatingDialog(this);


    EasyRatingDialog.ConditionTrigger conditionTrigger = new EasyRatingDialog.ConditionTrigger() {
      @Override
      public boolean shouldShow() {
        //Your custom condition
        return false;
      }
    };

    easyRatingDialog.setConditionTrigger(conditionTrigger);


  }

  @Override
  protected void onStart() {
    super.onStart();
    easyRatingDialog.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    easyRatingDialog.showIfNeeded(this);
  }
}
