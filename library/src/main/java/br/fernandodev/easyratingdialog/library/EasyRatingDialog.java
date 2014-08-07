package br.fernandodev.easyratingdialog.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import java.util.Date;

/**
 * Created by fernando on 8/4/14.
 */
public class EasyRatingDialog {
  public interface ConditionTrigger {
    public boolean shouldShow();
  }

  Context mContext;
  SharedPreferences mPreferences;
  ConditionTrigger mCondition;
  AlertDialog mDialog;

  private static final String PREFS_NAME = "erd_rating";
  private static final String KEY_WAS_RATED = "KEY_WAS_RATED";
  private static final String KEY_NEVER_REMINDER = "KEY_NEVER_REMINDER";
  private static final String KEY_FIRST_HIT_DATE = "KEY_FIRST_HIT_DATE";
  private static final String KEY_LAUNCH_TIMES = "KEY_LAUNCH_TIMES";

  public EasyRatingDialog(Context context) {
    mContext = context;
    mPreferences = context.getSharedPreferences(PREFS_NAME, 0);
  }

  public void onStart() {
    if (didRate() || didNeverReminder()) return;

    int lauchTimes = mPreferences.getInt(KEY_LAUNCH_TIMES, 0);
    long firstDate = mPreferences.getLong(KEY_FIRST_HIT_DATE, -1L);

    if (firstDate == -1L) {
      registerDate();
    }

    registerHitCount(++lauchTimes);
  }

  public void showIfNeeded(Activity activity) {
    if (mCondition != null) {
      if (mCondition.shouldShow())
        tryShow(activity);
    } else {
      if (shouldShow())
        tryShow(activity);
    }
  }

  public void neverReminder() {
    mPreferences.edit().putBoolean(KEY_NEVER_REMINDER, true).commit();
  }

  public void rateNow() {
    String appPackage = mContext.getPackageName();
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackage));
    mContext.startActivity(intent);
    mPreferences.edit().putBoolean(KEY_WAS_RATED, true).commit();
  }

  public void remindMeLater() {
    registerHitCount(0);
    registerDate();
  }

  public boolean didRate() {
    return mPreferences.getBoolean(KEY_WAS_RATED, false);
  }

  public boolean didNeverReminder() {
    return mPreferences.getBoolean(KEY_NEVER_REMINDER, false);
  }

  public void setConditionTrigger(ConditionTrigger condition) {
    mCondition = condition;
  }

  public void setCanceable(boolean canceable) {
    mDialog.setCancelable(canceable);
  }

  private void tryShow(Activity activity) {
    //Recycle dialog with current activity
    //References to activities are potentially problematics
    //and dialog alert only be created in an Activity context
    //
    if (mDialog != null && mDialog.isShowing())
      mDialog.dismiss();

    //Window token may expire at the end of activity
    //So it necessary to recreate dialog
    try {
      mDialog = null;
      mDialog = createDialog(activity);
      mDialog.show();
    } catch (Exception e) {
      //It prevents many Android exceptions
      //when user interactions conflicts with UI thread
      //BadTokenException, IllegalStateException ...
      Log.e(getClass().getSimpleName(), e.getMessage());
    }
  }

  private boolean shouldShow() {
    if (mPreferences.getBoolean(KEY_NEVER_REMINDER, false))
      return false;
    if (mPreferences.getBoolean(KEY_WAS_RATED, false))
      return false;

    int lauchTimes = mPreferences.getInt(KEY_LAUNCH_TIMES, 0);
    long firstDate = mPreferences.getLong(KEY_FIRST_HIT_DATE, 0L);
    long today = new Date().getTime();
    int maxLaunchTimes = mContext.getResources().getInteger(R.integer.erd_launch_times);
    int maxDaysAfter = mContext.getResources().getInteger(R.integer.erd_max_days_after);

    if (daysBetween(firstDate, today) > maxDaysAfter || lauchTimes > maxLaunchTimes) {
      return true;
    }

    return false;
  }

  private void registerHitCount(int hitCount) {
    mPreferences
        .edit()
        .putInt(KEY_LAUNCH_TIMES, Math.min(hitCount, Integer.MAX_VALUE))
        .commit();
  }

  private void registerDate() {
    Date today = new Date();
    mPreferences
        .edit()
        .putLong(KEY_FIRST_HIT_DATE, today.getTime())
        .commit();
  }

  private long daysBetween(long firstDate, long lastDate) {
    return (lastDate - firstDate) / (1000 * 60 * 60 * 24);
  }

  private android.app.AlertDialog createDialog(Context context) {
    return new AlertDialog.Builder(context)
        .setTitle(R.string.erd_title)
        .setMessage(R.string.erd_message)
        .setNegativeButton(R.string.erd_no_thanks, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            neverReminder();
          }
        })
        .setNeutralButton(R.string.erd_remind_me_later, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            remindMeLater();
          }
        })
        .setPositiveButton(R.string.erd_rate_now, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            rateNow();
          }
        })
        .setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override
          public void onCancel(DialogInterface dialogInterface) {
            remindMeLater();
          }
        }).create();
  }
}