# 关于属性适配的问题

* 你好，如果你是刚使用这个库的人可以不必理会，如果你之前使用了 `ShapeView` 这个库，也就是 `9.0` 版本以下的，在升级到 `9.0` 版本后需要进行适配，否则 `Android Studio` 会报错`编译不通过`，对于这个问题我表示十分抱歉，低版本的 `xml` 属性命名得并不是很规范，现在在 `9.0` 版本进行优化，尽管这次的代价比较大，但是我会义无反顾去做，如果你使用了 `ShapeView ` 但是不想进行适配，请不要升级依赖库版本。

#### 从 8.5 版本升级到 9.0 版本适配方案

* 新增

    * 新增 `app:shape_strokeGradientOrientation` 属性

* 删除

    * 删除 `app:shape_useLevel` 属性，删除原因：比较少用到，有用到的同学吱一声

* 修改

    * 修改 `app:shape` 属性名，请使用 `app:shape_type` 代替

    * 修改 `app:shape_topLeftRadius` 属性名，请使用 `app:shape_radiusInTopLeft` 代替

    * 修改 `app:shape_topRightRadius` 属性名，请使用 `app:shape_radiusInTopRight` 代替

    * 修改 `app:shape_bottomLeftRadius` 属性名，请使用 `app:shape_radiusInBottomLeft` 代替

    * 修改 `app:shape_bottomRightRadius` 属性名，请使用 `app:shape_radiusInBottomRight` 代替

    * 修改 `app:shape_startColor` 属性名，请使用 `app:shape_solidGradientStartColor` 代替

    * 修改 `app:shape_centerColor` 属性名，请使用 `app:shape_solidGradientCenterColor` 代替

    * 修改 `app:shape_endColor` 属性名，请使用 `app:shape_solidGradientEndColor` 代替

    * 修改 `app:shape_gradientType` 属性名，请使用 `app:shape_solidGradientType` 代替

    * 修改 `app:shape_centerX` 属性名，请使用 `app:shape_solidGradientCenterX` 代替

    * 修改 `app:shape_centerY` 属性名，请使用 `app:shape_solidGradientCenterY` 代替

    * 修改 `app:shape_gradientRadius` 属性名，请使用 `app:shape_solidGradientRadius` 代替

    * 修改 `app:shape_strokeWidth` 属性名，请使用 `app:shape_strokeSize` 代替

    * 修改 `app:shape_dashWidth` 属性名，请使用 `app:shape_strokeDashSize` 代替

    * 修改 `app:shape_dashGap` 属性名，请使用 `app:shape_strokeDashGap` 代替

    * 修改 `app:shape_innerRadius` 属性名，请使用 `app:shape_ringInnerRadiusSize` 代替

    * 修改 `app:shape_innerRadiusRatio` 属性名，请使用 `app:shape_ringInnerRadiusRatio` 代替

    * 修改 `app:shape_thickness` 属性名，请使用 `app:shape_ringThicknessSize` 代替

    * 修改 `app:shape_thicknessRatio` 属性名，请使用 `app:shape_ringThicknessRatio` 代替

* 拆分

    * 拆分 `app:shape_angle` 属性，将原有属性拆分为 `app:shape_solidGradientOrientation` 和 `app:shape_strokeGradientOrientation`，并且修改了值的类型，具体赋值可以参考以下：

    ```xml
    <!-- 从左到右绘制渐变（0 度） -->
    <enum name="leftToRight" value="0" />
    <!-- 从右到左绘制渐变（180 度） -->
    <enum name="rightToLeft" value="180" />
    <!-- 从下到上绘制渐变（90 度） -->
    <enum name="bottomToTop" value="90" />
    <!-- 从上到下绘制渐变（270 度） -->
    <enum name="topToBottom" value="270" />

    <!-- 从左上角到右下角绘制渐变（315 度） -->
    <enum name="topLeftToBottomRight" value="315" />
    <!-- 从左下角到右上角绘制渐变（45 度） -->
    <enum name="bottomLeftToTopRight" value="45" />
    <!-- 从右上角到左下角绘制渐变（225 度） -->
    <enum name="topRightToBottomLeft" value="225" />
    <!-- 从右下角到左上角绘制渐变（135 度） -->
    <enum name="bottomRightToTopLeft" value="135" />
    ```