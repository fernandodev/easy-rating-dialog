package com.github.fernandodev.easyratingdialog.samplewithdagger.dagger;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fernando on 8/31/14.
 */
@Module(library = true)
public class ActivityModule {
  Activity activity;

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  @Provides @Singleton Activity provideActivity() { return activity; }

  @Provides @ForActivity Context provideActivityContext() {
    return activity;
  }
}
