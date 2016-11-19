# ActivityFrameMetrics
Detect janky frames with Android Nougat FrameMetrics

# FrameMetrics
Starting from Android SDK 24 (Nougat) there is a new API to monitor application UI performance. In older Android version we were be able to analyse data from last rendered 120 frames for each aby with adb shell command: `adb shell dumpsys gfxinfo framestats displays`. Now thanks you FrameMetrics this data is available directly from code and can be analysed in realtime. 

More about FrameMetrics can be found in [documentation]() and Android Nougat [what's new](https://developer.android.com/about/versions/nougat/android-7.0.html#framemetrics_api) section.

# ActivityFrameMetrics
ActivityFrameMetrics is small 1-class library which prints janky frames warnings to Logcat for each Activity.

![logcat.png](https://raw.githubusercontent.com/frogermcs/ActivityFrameMetrics/master/art/logcat.png)

```bash
W/FrameMetrics: Janky frame detected on MainActivity with total duration: 23.13ms
Layout/measure: 0.31ms, draw:0.95ms, gpuCommand:4.00ms others:17.87ms
Janky frames: 22/638(3.4482758%)
W/FrameMetrics: Janky frame detected on MainActivity with total duration: 25.02ms
Layout/measure: 0.20ms, draw:0.44ms, gpuCommand:1.38ms others:23.01ms
Janky frames: 23/768(2.9947915%)
W/FrameMetrics: Janky frame detected on MainActivity with total duration: 24.46ms
Layout/measure: 0.16ms, draw:0.28ms, gpuCommand:21.03ms others:3.00ms
Janky frames: 24/770(3.116883%)
E/FrameMetrics: Janky frame detected on MainActivity with total duration: 41.17ms
Layout/measure: 0.25ms, draw:0.42ms, gpuCommand:2.10ms others:38.40ms
Janky frames: 25/1268(1.9716088%)
W/FrameMetrics: Janky frame detected on MainActivity with total duration: 17.66ms
Layout/measure: 0.32ms, draw:0.32ms, gpuCommand:2.41ms others:14.62ms
Janky frames: 26/1372(1.8950438%)
E/FrameMetrics: Janky frame detected on MainActivity with total duration: 46.68ms
Layout/measure: 0.33ms, draw:0.69ms, gpuCommand:2.51ms others:43.14ms
Janky frames: 27/1378(1.9593613%)
```

## Usage

Register ActivityFrameMetrics as an ActivityLifecycleCallback in your `Application` class. 

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityFrameMetrics.Builder().build());
    }
}
```

By default library prints Logcat warning for every frame which was rendered longer than 17ms and Logcat error for these which was rendered longer than 34ms.

Default values (and others) can be changed with `ActivityFrameMetrics.Builder` params:

```java
new ActivityFrameMetrics.Builder()
        .warningLevelMs(20)     //default: 17ms
        .errorLevelMs(40)       //default: 34ms
        .showWarnings(true)     //default: true
        .showErrors(true)       //default: true
        .build();
```

## Download

### Java code

If you don't want to add another dependency to your project, just copy [ActivityFrameMetrics.java](https://github.com/frogermcs/ActivityFrameMetrics/blob/master/activityframemetrics/src/main/java/com/frogermcs/activityframemetrics/ActivityFrameMetrics.java) to your source directory.

### Library dependency

```gradle
dependencies {
  compile 'com.frogermcs.activityframemetrics:activityframemetrics:0.1.0'
}
```

## License

    Copyright (C) 2016 Miroslaw Stanek

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

