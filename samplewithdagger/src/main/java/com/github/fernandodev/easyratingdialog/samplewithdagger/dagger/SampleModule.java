package com.github.fernandodev.easyratingdialog.samplewithdagger.dagger;

import android.content.Context;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.github.fernandodev.easyratingdialog.samplewithdagger.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fernando on 8/31/14.
 */
@Module(
    injects = {
        //Activities
        MainActivity.class,
    },

    library = true,
    complete = false,
    overrides = false
)
public class SampleModule {
  @Provides EasyRatingDialog provideRatingDialog(@ForActivity Context context) {
    return new EasyRatingDialog(context);
  }
}
