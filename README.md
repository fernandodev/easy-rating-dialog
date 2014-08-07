Easy Rating Dialog [![Build Status](https://travis-ci.org/fernandodev/easy-rating-dialog.svg?branch=master)](https://travis-ci.org/fernandodev/easy-rating-dialog)
==

![Easy Rating Dialog Light](http://i.imgur.com/3m6WCTP.png?1)
![Easy Rating Dialog Dark](http://i.imgur.com/4yCXV7f.png?1)

This lib provides a simple way to display an alert dialog for rating app.

Default conditions to show:

1. User opened the app more than 5 times
2. User opened the app after 7 days of first opening.

- [Easy Rating Dialog](#easy-rating-dialog)
  - [Installation](#installation)
  - [Using](#using)
  - [Tips](#tips)
  - [How to contribute?](#how-to-contribute)
  - [License](#license)

## Installation

* Manually

Create in your root project folder a folder called `libraries`.

Download the library folder and import it into your project and rename `library` to `EasyRatingDialog`.

Now, to setup with gradle, you need to do the following steps:

1. In `gradle.settings` add: `':libraries:EasyRatingDialog'`
2. In `gradle.build` at `dependencies` add `compile project(':libraries:EasyRatingDialog')`

* Gradle Dependency: _**coming soon**_

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
  easyRatingDialog.showIfNeeded(this);
}
```

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