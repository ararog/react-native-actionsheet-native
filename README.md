# React Native ActionSheet
React Native ActionSheet is a JavaScript library for [React Native](https://facebook.github.io/react-native/) that implements AcionSheet for Android. Its equivalent to ActionSheetIOS which is part of React Native.

To implement the Android version I used [android-ActionSheet](https://github.com/baoyongzhang/android-ActionSheet).

## Requirements
* React Native >= 0.29.0
* Android

## Installing React Native ActionSheet
```bash
npm install react-native-actionsheet-native --save
```

# Android
* In `android/setting.gradle`

```gradle
...
include ':react-native-actionsheet-native', ':app'
project(':react-native-actionsheet-native').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-actionsheet-native/android')
```

* In `android/app/build.gradle`

```gradle
apply plugin: "com.android.application"
...
dependencies {
    compile fileTree(dir: "libs", include: ["*.jar"])
    compile "com.android.support:appcompat-v7:23.0.1"
    compile "com.facebook.react:react-native:+"
    compile project(":react-native-actionsheet-native") // <--- add this
}
```

* Register Module (in MainApplication.java)

```java
import com.slowpath.actionsheet.ActionSheetModule;   // <--- import this
import com.slowpath.actionsheet.ActionSheetPackage;  // <--- import and this

public class MainApplication extends Application implements ReactApplication {
  ......

  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
      new ActionSheetPackage(), // <------ add this line to yout MainApplication class
      new MainReactPackage());
  }

  ......

}
```

# Usage

From your JS files for both iOS and Android:

```js
import ActionSheet from 'react-native-actionsheet-native';

ActionSheet.showActionSheetWithOptions({
  options: [`Disconnect`, 'Cancel'],
  cancelButtonIndex: 1
},
(buttonIndex) => {
  const { dispatch } = this.props;
  if (buttonIndex === 0) {
    // Do something.
  }
});

```

## License
React Native ActionSheet is BSD-licensed.
