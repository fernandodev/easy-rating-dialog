package com.github.fernandodev.easyratingdialog.samplewithdagger.dagger;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by fernando on 8/31/14.
 */
@Qualifier @Retention(RUNTIME)
public @interface ForApplication {
}
