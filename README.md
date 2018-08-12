Easy Rating Dialog [![Build Status](https://travis-ci.org/fernandodev/easy-rating-dialog.svg?branch=master)](https://travis-ci.org/fernandodev/easy-rating-dialog)[![Android Arsenal](http://img.shields.io/badge/Android%20Arsenal-easy--rating--dialog-blue.svg?style=flat)](http://android-arsenal.com/details/1/844)
==

<img src="https://raw.githubusercontent.com/fernandodev/easy-rating-dialog/master/screenshots/device-2017-05-07-185513.png" width="320px">

This lib provides a simple way to display an alert dialog for rating app.

Default conditions to show:

1. User opened the app more than 5 times
2. User opened the app after 7 days of first opening.

* Please Note: The lastest version uses AppCompat. Your application Theme has to be inherited from *Theme.AppCompat*

- [Easy Rating Dialog](#easy-rating-dialog-!build-statushttpstravis-ciorgfernandodeveasy-rating-dialogsvgbranch=masterhttpstravis-ciorgfernandodeveasy-rating-dialog)
  - [Installation](#installation)
  - [Using](#using)
  - [Tips](#tips)
    - [Condition triggers](#condition-triggers)
    - [Useful public methods](#useful-public-methods)
    - [Internationalization](#internationalization)
    - [Constants](#constants)
    - [Theme](#theme)
  - [Dagger Issues](#dagger-issues)
  - [Samples Usage](#samples-usage)
  - [Testing](#testing)
  - [Showcase](#showcase)
  - [Change Logs](#change-logs)
  - [License](#license)

## Installation

It's very simple with gradle ;)

Add `mavenCentral` as repository source:

```gradle
repositories {
  mavenCentral()
}
```

And finally add this line inside `dependencies { }` section:

```gradle
compile 'com.github.fernandodev.easyratingdialog:easyratingdialog:+'
```

* The `+` symbol indicates to gradle to get the latest version.
* Current version: `1.1.2`

**ATTENTION**

If you are using [afollestad:material-dialogs](https://github.com/afollestad/material-dialogs) you must esclude this module from EasyRatingDialog lib to avoid lib conflicting:

```gradle
  compile('com.github.fernandodev.easyratingdialog:easyratingdialog:1.1.2') {
    exclude module: 'material-dialogs'
  }
```

* See the sample if there are any doubts.

## Using

The main flow usage is:

Create dialog in your main activity or your start activity:

```java
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  easyRatingDialog = new EasyRatingDialog(this);
}
```

after you need to start dialog at:

```java
@Override
protected void onStart() {
  super.onStart();
  easyRatingDialog.onStart();
}
```

this line inc. counters and initialize first app access date if necessary

And to show when needed just call in `onResume`:

```java
@Override
protected void onResume() {
  super.onResume();
  easyRatingDialog.showIfNeeded();
}
```

* all **exceptions** are catched when dialog tries to show because I assume the app running is more important than to show the dialog.

## Tips

### Condition triggers

If you want to change the default lib behavior you can create a custom Condition Trigger:

```java
EasyRatingDialog.ConditionTrigger conditionTrigger = new EasyRatingDialog.ConditionTrigger() {
  @Override
  public boolean shouldShow() {
    //Your custom condition here
    return false;
  }
};

easyRatingDialog.setConditionTrigger(conditionTrigger);
```

### Useful public methods

If you need to create for user an action rate and link it with dialog conditions, don't be afraid, just create
the easy rating dialog instance and call rate now as below:

```java
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  easyRatingDialog = new EasyRatingDialog(this);
}
```

```java
public void onClickRateNow() {
  super.onResume();
  easyRatingDialog.rateNow();
}
```

You can do it for `neverRemider()` and `remindMeLater()` actions too.

To check stored values just call `didNeverReminder()`, `didRate()`.

### Internationalization

Do you liked the lib but you need to change default strings in en-US, you can do it easily as section below.

Just override default values in your `strings.xml`:

```xml
<resources>
  <string name="erd_title">Rate this app</string>
  <string name="erd_message">Hi, take a minute to rate this app and help support to improve more new features. ;)</string>
  <string name="erd_no_thanks">No, thanks.</string>
  <string name="erd_remind_me_later">Remind me later.</string>
  <string name="erd_rate_now">Rate now.</string>
</resources>
```

### Constants

Do you want to change hit times or days after condition? It's simple!

You need to override default values the lib, for that, just create in `res/values` folder, or alter, a file
named `constants.xml`.

And override the values:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <integer name="erd_launch_times">10</integer>
  <integer name="erd_max_days_after">14</integer>
</resources>
```

### Theme

You can customize the alert dialog using style settings:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="AppTheme" parent="Theme.AppCompat">
        <item name="alertDialogTheme">@style/AlertDialogTheme</item>
    </style>

    <!--You can customize dialog theme as below-->

    <style name="AlertDialogTheme" parent="Theme.AppCompat.Light.Dialog.Alert">
        <item name="buttonBarNegativeButtonStyle">@style/NegativeButtonStyle</item>
        <item name="buttonBarPositiveButtonStyle">@style/PositiveButtonStyle</item>
    </style>

    <style name="NegativeButtonStyle" parent="Widget.AppCompat.Button.ButtonBar.AlertDialog">
        <item name="android:textColor">#f00</item>
    </style>

    <style name="PositiveButtonStyle" parent="Widget.AppCompat.Button.ButtonBar.AlertDialog">
        <item name="android:textColor">#00f</item>
    </style>
</resources>
```

## Dagger Issues

If you are using dagger pay attention to some items.

One, you must provide an Activity Context to EasyRatingDialog to show the dialog. So you can do this as
below:

```java
@Provides EasyRatingDialog provideRatingDialog(@ForActivity Context context) {
  return new EasyRatingDialog(context);
}
```

where `@ForActivity` is an interface that overrides other contexts provided by other modules.

```java
@Qualifier @Retention(RUNTIME)
  public @interface ForActivity {
}
```

Otherwise if you provide other context and try to show an execption can be occur because dialogs only can be
attached to Activity's context.

The code below prevents you to get a BadTokenException exception
`E/EasyRatingDialog﹕ Unable to add window -- token android.os.BinderProxy@536c3920 is not valid; is your activity running?`

```java
@Provides @ForActivity Context provideActivityContext() {
  return activity;
}
```

If you use `@Singleton annotation to provide the Activity's context a BadTokenException can be occur after restoring from background.

Remember, all **exceptions** are catched when dialog tries to show because
I assume the app running is more important than to show the dialog.

## Samples Usage

There are two samples, the first is just a simple acitivity that shows the dialog and the second uses dagger injection.

To run samples you can follow steps below

```shell
$ git clone git@github.com:fernandodev/easy-rating-dialog.git
$ cd easyratingdialog
$ ./gradlew installSampleDebug installSampleWithDaggerDebug --daemon
```

## Testing

There are a simple test for the rating dialog. If you want to contribute check the tests too.

```shell
$ git clone git@github.com:fernandodev/easy-rating-dialog.git
$ cd easyratingdialog
$ ./gradlew assembleSampleDebugTest connectedAndroidTestSampleDebug --daemon
```

You must open an emulator before.

## Showcase

Have you used my library in your project? Tell me and I'll sponsor your app here ;)

* [I Ching - The Oracle](https://play.google.com/store/apps/details?id=com.creativecode.iching)
* [Say It! - English Learning](https://play.google.com/store/apps/details?id=com.cesarsk.say_it) : An Android App aimed to improve your English Pronunciation. [Github Page](https://github.com/cesarsk/say_it)
* [Find my car : Last Park](https://play.google.com/store/apps/details?id=com.ages.arabamnerede) : Find My Car allows you to save the exact location of your parked car so you can later remember where it's.
* [App permissions](https://play.google.com/store/apps/details?id=in.arjsna.permissionchecker) - App to see details of permissions required by all the installed apps [Github Page](https://github.com/Arjun-sna/android-permission-checker-app).
* [Quick Audio Recorder](https://play.google.com/store/apps/details?id=com.rwsoftwares.waqas.quickaudiorecorder) : A simple audio recorder for Android.

## Change Logs

See [Change Logs file](https://github.com/fernandodev/easy-rating-dialog/blob/master/CHANGELOGS.md).

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
