package com.github.fernandodev.easyratingdialog.samplewithdagger;

import android.app.Activity;
import android.app.Application;

import com.github.fernandodev.easyratingdialog.samplewithdagger.dagger.ActivityModule;
import com.github.fernandodev.easyratingdialog.samplewithdagger.dagger.ApplicationModule;
import com.github.fernandodev.easyratingdialog.samplewithdagger.dagger.SampleModule;

import dagger.ObjectGraph;

/**
 * Created by fernando on 8/31/14.
 */
public class Sample extends Application {
  ObjectGraph graph;
  ApplicationModule applicationModule;
  ActivityModule activityModule;
  SampleModule sampleModule;

  @Override
  public void onCreate() {
    applicationModule = new ApplicationModule(this);
    activityModule = new ActivityModule();
    sampleModule = new SampleModule();
    graph = ObjectGraph.create(
        applicationModule,
        activityModule,
        sampleModule);
  }

  public void injectActivity(Activity activity) {
    activityModule.setActivity(activity);
    graph.inject(activity);
  }
}
