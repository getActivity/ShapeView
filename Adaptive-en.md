# About Attribute Adaptation Issues

* Hello, if you are new to this library, you can ignore this. If you have used the `ShapeView` library before, that is, versions below `9.0`, you need to adapt after upgrading to version `9.0`, otherwise `Android Studio` will report a `compilation failed` error. I apologize for this issue. The naming of `xml` attributes in lower versions was not very standardized. Now, in version `9.0`, it has been optimized. Although the cost of this change is relatively high, I will do it without hesitation. If you use `ShapeView` but do not want to adapt, please do not upgrade the library version.

#### Adaptation Plan for Upgrading from Version 9.2 to 9.3

* Modification

    * Changed the attribute name `app:shape_strokeGradientColor`, please use `app:shape_strokeGradientEndColor` instead

#### Adaptation Plan for Upgrading from Version 9.0 to 9.2

* New Attributes

    * Added `app:shape_radiusInTopStart` attribute

    * Added `app:shape_radiusInTopEnd` attribute

    * Added `app:shape_radiusInBottomStart` attribute

    * Added `app:shape_radiusInBottomEnd` attribute

* Supplement Attribute Values

    * Added several layout reverse direction attribute values for `app:shape_solidGradientOrientation` and `app:shape_strokeGradientOrientation` attributes

```xml
<!-- Draw gradient from left to right (0 degrees) -->
<enum name="startToEnd" value="10" />

<!-- Draw gradient from right to left (180 degrees) -->
<enum name="endToStart" value="1800" />

<!-- Draw gradient from top left to bottom right (315 degrees) -->
<enum name="topStartToBottomEnd" value="3150" />

<!-- Draw gradient from bottom left to top right (45 degrees) -->
<enum name="bottomStartToTopEnd" value="450" />

<!-- Draw gradient from top right to bottom left (225 degrees) -->
<enum name="topEndToBottomStart" value="2250" />

<!-- Draw gradient from bottom right to top left (135 degrees) -->
<enum name="bottomEndToTopStart" value="1350" />
```

#### Adaptation Plan for Upgrading from Version 8.5 to 9.0

* Added

    * Added `app:shape_strokeGradientOrientation` attribute

* Deleted

    * Deleted `app:shape_useLevel` attribute, reason: rarely used, if needed, please raise your voice

* Modified

    * Changed the attribute name `app:shape`, please use `app:shape_type` instead

    * Changed the attribute name `app:shape_topLeftRadius`, please use `app:shape_radiusInTopLeft` instead

    * Changed the attribute name `app:shape_topRightRadius`, please use `app:shape_radiusInTopRight` instead

    * Changed the attribute name `app:shape_bottomLeftRadius`, please use `app:shape_radiusInBottomLeft` instead

    * Changed the attribute name `app:shape_bottomRightRadius`, please use `app:shape_radiusInBottomRight` instead

    * Changed the attribute name `app:shape_startColor`, please use `app:shape_solidGradientStartColor` instead

    * Changed the attribute name `app:shape_centerColor`, please use `app:shape_solidGradientCenterColor` instead

    * Changed the attribute name `app:shape_endColor`, please use `app:shape_solidGradientEndColor` instead

    * Changed the attribute name `app:shape_gradientType`, please use `app:shape_solidGradientType` instead

    * Changed the attribute name `app:shape_centerX`, please use `app:shape_solidGradientCenterX` instead

    * Changed the attribute name `app:shape_centerY`, please use `app:shape_solidGradientCenterY` instead

    * Changed the attribute name `app:shape_gradientRadius`, please use `app:shape_solidGradientRadius` instead

    * Changed the attribute name `app:shape_strokeWidth`, please use `app:shape_strokeSize` instead

    * Changed the attribute name `app:shape_dashWidth`, please use `app:shape_strokeDashSize` instead

    * Changed the attribute name `app:shape_dashGap`, please use `app:shape_strokeDashGap` instead

    * Changed the attribute name `app:shape_innerRadius`, please use `app:shape_ringInnerRadiusSize` instead

    * Changed the attribute name `app:shape_innerRadiusRatio`, please use `app:shape_ringInnerRadiusRatio` instead

    * Changed the attribute name `app:shape_thickness`, please use `app:shape_ringThicknessSize` instead

    * Changed the attribute name `app:shape_thicknessRatio`, please use `app:shape_ringThicknessRatio` instead

* Split

    * Split the attribute `app:shape_angle` into `app:shape_solidGradientOrientation` and `app:shape_strokeGradientOrientation`. The previous `app:shape_angle` attribute is equivalent to the current `app:shape_solidGradientOrientation` attribute. You can directly search and replace. Also, the value type has been changed. For specific values, please refer to the following:

```xml
<!-- Draw gradient from left to right (0 degrees) -->
<enum name="leftToRight" value="0" />
<!-- Draw gradient from right to left (180 degrees) -->
<enum name="rightToLeft" value="180" />
<!-- Draw gradient from bottom to top (90 degrees) -->
<enum name="bottomToTop" value="90" />
<!-- Draw gradient from top to bottom (270 degrees) -->
<enum name="topToBottom" value="270" />

<!-- Draw gradient from top left to bottom right (315 degrees) -->
<enum name="topLeftToBottomRight" value="315" />
<!-- Draw gradient from bottom left to top right (45 degrees) -->
<enum name="bottomLeftToTopRight" value="45" />
<!-- Draw gradient from top right to bottom left (225 degrees) -->
<enum name="topRightToBottomLeft" value="225" />
<!-- Draw gradient from bottom right to top left (135 degrees) -->
<enum name="bottomRightToTopLeft" value="135" />
```