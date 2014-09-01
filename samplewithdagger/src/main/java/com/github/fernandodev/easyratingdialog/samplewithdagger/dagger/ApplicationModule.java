package com.github.fernandodev.easyratingdialog.samplewithdagger.dagger;

import android.content.Context;

import com.github.fernandodev.easyratingdialog.samplewithdagger.Sample;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fernando on 8/31/14.
 */
@Module(library = true)
public class ApplicationModule {
  private Sample app;

  public ApplicationModule(Sample application) {
    app = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return app;
  }
}
