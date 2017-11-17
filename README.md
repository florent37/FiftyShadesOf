# FiftyShadesOf

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FiftyShadesOf-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4505)

<a target='_blank' rel='nofollow' href='https://app.codesponsor.io/link/iqkQGAc2EFNdScAzpwZr1Sdy/florent37/FiftyShadesOf'>
  <img alt='Sponsor' width='888' height='68' src='https://app.codesponsor.io/embed/iqkQGAc2EFNdScAzpwZr1Sdy/florent37/FiftyShadesOf.svg' />
</a>

An elegant context-care loading placeholder for Android

# Usage

```java
FiftyShadesOf.with(context)
             .on(view1, view2, view3)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadesOf/master/media/fadein.gif)](https://www.github.com/florent37/fiftyshadedof)

#Available attributes

## View Selector

```java
FiftyShadesOf.with(context)

             .on(R.id.view1, R.id.view2, R.id.view3) //views id

             .on(view1, view2, view3) //views references
             
             .on(viewGroup) //group of views
             
             .except(view1, view2) //skip a view

             .start();
```

## Cross

(By Default)

```java
FiftyShadesOf.with(context)
             .on(R.id.view)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadesOf/master/media/cross.gif)](https://www.github.com/florent37/fiftyshadedof)

## Fade

```java
FiftyShadesOf.with(context)
             .on(R.id.view)
             .fadeIn(true)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadesOf/master/media/fadein.gif)](https://www.github.com/florent37/fiftyshadedof)

# Download

<a href='https://ko-fi.com/A160LCC' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

In your module [![Download](https://api.bintray.com/packages/florent37/maven/FiftyShadesOf/images/download.svg)](https://bintray.com/florent37/maven/FiftyShadesOf/_latestVersion)
```gradle
compile 'com.github.florent37:fiftyshadesof:1.0.0'
```

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

<a href="https://play.google.com/store/apps/details?id=com.github.florent37.florent.champigny">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>
<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2016 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
