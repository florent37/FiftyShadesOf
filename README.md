# FiftyShadesOf

An elegant context-care loading placeholder for Android

#Usage

```xml
FiftyShadesOf.with(context)
             .on(view1, view2, view3)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadedOf/master/media/fadein.gif)](https://www.github.com/florent37/fiftyshadedof)

#Available attributes

## View Selector

```xml
FiftyShadesOf.with(context)

             .on(R.id.view1, R.id.view2, R.id.view3) //views id

             .on(view1, view2, view3) //views references
             
             .on(viewGroup) //group of views
             
             .exept(view1, view2) //skip a view

             .start();
```

## Cross

(By Default)

```xml
FiftyShadesOf.with(context)
             .on(R.id.view)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadedOf/master/media/cross.gif)](https://www.github.com/florent37/fiftyshadedof)

## Fade

```xml
FiftyShadesOf.with(context)
             .on(R.id.view)
             .fadeIn(true)
             .start();
```

[![screen](https://raw.githubusercontent.com/florent37/FiftyShadedOf/master/media/fadein.gif)](https://www.github.com/florent37/fiftyshadedof)

#Download

In your module [![Download](https://api.bintray.com/packages/florent37/maven/FiftyShadesOf/images/download.svg)](https://bintray.com/florent37/maven/FiftyShadesOf/_latestVersion)
```groovy
compile 'com.github.florent37:fiftyshadesof:1.0.0'
```

#Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

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
