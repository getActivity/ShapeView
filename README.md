# Shape 框架

* 项目地址：[Github](https://github.com/getActivity/ShapeView)、[码云](https://gitee.com/getActivity/ShapeView)

* 博客介绍：[震惊，没想到 Shape 也可以这么写](https://www.jianshu.com/p/1288d8873440)

* 点击此处 [下载 Demo](ShapeView.apk) 进行演示或者测试

#### 集成步骤

* 在项目根目录下的 `build.gradle` 文件中加入

```groovy
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

* 在项目 app 模块下的 `build.gradle` 文件中加入

```groovy
android {
    // 支持 JDK 1.8
    compileOptions {
        targetCompatibility JavaVersion.VERSION_1_8
        sourceCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Shape 框架：https://github.com/getActivity/ShapeView
    implementation 'com.github.getActivity:ShapeView:6.0'
}
```

#### AndroidX

* 如果项目是基于 **AndroidX** 包，请在项目 `gradle.properties` 文件中加入

```groovy
# 表示将第三方库迁移到 AndroidX
android.enableJetifier = true
```

* 如果项目是基于 **Support** 包则不需要加入此配置

#### 框架文档

* Java 代码设置

```java
ShapeButton shapeButton = findViewById(R.id.btn_main_test);
shapeButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    
        shapeButton.getShapeDrawableBuilder()
                .setSolidColor(0xFF000000)
                .setStrokeColor(0xFF5A8DDF)
                .intoBackground();

        shapeButton.getTextColorBuilder()
                .setTextColor(0xFFFFFFFF)
                .intoTextColor();

        shapeButton.setText("颜色已经改变啦");
    }
});
```

* 布局属性大全

```xml
<resources>

    <!-- Shape 形状（默认是矩形） -->
    <attr name="shape">
        <!-- 矩形 -->
        <enum name="rectangle" value="0" />
        <!-- 椭圆形 -->
        <enum name="oval" value="1" />
        <!-- 线条 -->
        <enum name="line" value="2" />
        <!-- 圆环 -->
        <enum name="ring" value="3" />
    </attr>
    <!-- Shape 宽度 -->
    <attr name="shape_width" format="dimension" />
    <!-- Shape 高度 -->
    <attr name="shape_height" format="dimension" />

    <!-- 填充色（默认状态） -->
    <attr name="shape_solidColor" format="color|reference" />
    <!-- 填充色（按下状态） -->
    <attr name="shape_solidPressedColor" format="color|reference" />
    <!-- 填充色（选中状态） -->
    <attr name="shape_solidCheckedColor" format="color|reference" />
    <!-- 填充色（禁用状态） -->
    <attr name="shape_solidDisabledColor" format="color|reference" />
    <!-- 填充色（焦点状态） -->
    <attr name="shape_solidFocusedColor" format="color|reference" />
    <!-- 填充色（选择状态） -->
    <attr name="shape_solidSelectedColor" format="color|reference" />

    <!-- 圆角大小 -->
    <attr name="shape_radius" format="dimension" />
    <!-- 左上角的圆角大小 -->
    <attr name="shape_topLeftRadius" format="dimension" />
    <!-- 右上角的圆角大小 -->
    <attr name="shape_topRightRadius" format="dimension" />
    <!-- 左下角的圆角大小 -->
    <attr name="shape_bottomLeftRadius" format="dimension" />
    <!-- 右下角的圆角大小 -->
    <attr name="shape_bottomRightRadius" format="dimension" />

    <!-- 渐变色起始颜色 -->
    <attr name="shape_startColor" format="color" />
    <!-- 渐变色中间颜色（可不设置） -->
    <attr name="shape_centerColor" format="color" />
    <!-- 渐变色结束颜色 -->
    <attr name="shape_endColor" format="color" />
    <!-- 是否将用于缩放渐变 -->
    <attr name="shape_useLevel" format="boolean" />
    <!-- 渐变角度（仅用于线性渐变。必须是 0-315 范围内的值，并且是 45 的倍数） -->
    <attr name="shape_angle" format="float" />
    <!-- 渐变类型（默认类型是线性渐变） -->
    <attr name="shape_gradientType">
        <!-- 线性渐变 -->
        <enum name="linear" value="0" />
        <!-- 径向渐变 -->
        <enum name="radial" value="1" />
        <!-- 扫描渐变 -->
        <enum name="sweep"  value="2" />
    </attr>
    <!-- 渐变中心 X 点坐标的相对位置（默认值为 0.5）-->
    <attr name="shape_centerX" format="float|fraction" />
    <!-- 渐变中心 Y 点坐标的相对位置（默认值为 0.5）-->
    <attr name="shape_centerY" format="float|fraction" />
    <!-- 渐变色半径（仅用于径向渐变） -->
    <attr name="shape_gradientRadius" format="float|fraction|dimension" />

    <!-- 边框色（默认状态） -->
    <attr name="shape_strokeColor" format="color|reference" />
    <!-- 边框色（按下状态） -->
    <attr name="shape_strokePressedColor" format="color|reference" />
    <!-- 边框色（选中状态） -->
    <attr name="shape_strokeCheckedColor" format="color|reference" />
    <!-- 边框色（禁用状态） -->
    <attr name="shape_strokeDisabledColor" format="color|reference" />
    <!-- 边框色（焦点状态） -->
    <attr name="shape_strokeFocusedColor" format="color|reference" />
    <!-- 边框色（选择状态） -->
    <attr name="shape_strokeSelectedColor" format="color|reference" />

    <!-- 边框宽度 -->
    <attr name="shape_strokeWidth" format="dimension" />
    <!-- 边框虚线宽度（为 0 就是实线，大于 0 就是虚线） -->
    <attr name="shape_dashWidth" format="dimension" />
    <!-- 边框虚线间隔（虚线与虚线之间的间隔） -->
    <attr name="shape_dashGap" format="dimension" />

    <!-- 内环的半径（仅在 shape="ring" 生效） -->
    <attr name="shape_innerRadius" format="dimension" />
    <!-- 内环的半径比率（仅在 shape="ring" 生效），计算公式：整个圆环 / innerRadiusRatio = innerRadius -->
    <attr name="shape_innerRadiusRatio" format="float" />
    <!-- 外环的厚度（仅在 shape="ring" 生效） -->
    <attr name="shape_thickness" format="dimension" />
    <!-- 外环的厚度比率（仅在 shape="ring" 生效），计算公式：整个圆环 / thicknessRatio = thickness -->
    <attr name="shape_thicknessRatio" format="float" />

    <!-- 阴影大小 -->
    <attr name="shape_shadowSize" format="dimension" />
    <!-- 阴影颜色 -->
    <attr name="shape_shadowColor" format="color" />
    <!-- 阴影水平偏移 -->
    <attr name="shape_shadowOffsetX" format="dimension" />
    <!-- 阴影垂直偏移 -->
    <attr name="shape_shadowOffsetY" format="dimension" />

    <!-- 文本色（默认状态） -->
    <attr name="shape_textColor" format="color|reference" />
    <!-- 文本色（按下状态） -->
    <attr name="shape_textPressedColor" format="color|reference" />
    <!-- 文本色（选中状态） -->
    <attr name="shape_textCheckedColor" format="color|reference" />
    <!-- 文本色（禁用状态） -->
    <attr name="shape_textDisabledColor" format="color|reference" />
    <!-- 文本色（焦点状态） -->
    <attr name="shape_textFocusedColor" format="color|reference" />
    <!-- 文本色（选择状态） -->
    <attr name="shape_textSelectedColor" format="color|reference" />

    <!-- 文本渐变色起始颜色 -->
    <attr name="shape_textStartColor" format="color" />
    <!-- 文本渐变色中间颜色（可不设置） -->
    <attr name="shape_textCenterColor" format="color" />
    <!-- 文本渐变色结束颜色 -->
    <attr name="shape_textEndColor" format="color" />
    <!-- 文本渐变方向（默认类型是水平渐变） -->
    <attr name="shape_textGradientOrientation">
        <!-- 水平渐变 -->
        <enum name="horizontal" value="0" />
        <!-- 垂直渐变 -->
        <enum name="vertical" value="1" />
    </attr>

    <!-- CheckBox 或者 RadioButton 图标（默认状态） -->
    <attr name="shape_buttonDrawable" format="reference" />
    <!-- CheckBox 或者 RadioButton 图标（按下状态） -->
    <attr name="shape_buttonPressedDrawable" format="reference" />
    <!-- CheckBox 或者 RadioButton 图标（选中状态） -->
    <attr name="shape_buttonCheckedDrawable" format="reference" />
    <!-- CheckBox 或者 RadioButton 图标（禁用状态） -->
    <attr name="shape_buttonDisabledDrawable" format="reference" />
    <!-- CheckBox 或者 RadioButton 图标（焦点状态） -->
    <attr name="shape_buttonFocusedDrawable" format="reference" />
    <!-- CheckBox 或者 RadioButton 图标（选择状态） -->
    <attr name="shape_buttonSelectedDrawable" format="reference" />

</resources>
```

* 目前支持这些属性的控件有：

    * View 的子类：ShapeView、ShapeTextView、ShapeButton、ShapeImageView、ShapeRadioButton、ShapeCheckBox、ShapeEditText

    * ViewGroup 的子类：ShapeLinearLayout、ShapeFrameLayout、ShapeRelativeLayout、ShapeConstraintLayout、ShapeRecyclerView

#### [常见疑问解答](HelpDoc.md)

#### [使用案例文档]((UseDemo.md))

#### 框架亮点

* 更加便捷：无需新增 Xml 文件，直接定义控件属性即可

* 即时生效：在布局中可实时预览效果，即见所得

* 无学习成本：控件属性和原生 Shape 命名保持一致，无需额外学习

* 覆盖范围广：几乎涵盖所有常见的 View 控件，并且控件名称无任何记忆成本

* 支持状态选择器：不仅支持设置背景色的状态选择器，还支持设置文本颜色的状态选择器

* 功能覆盖全面：不仅支持设置背景阴影色，还支持设置文本渐变色

#### 作者的其他开源项目

* 安卓技术中台：[AndroidProject](https://github.com/getActivity/AndroidProject)

* 权限框架：[XXPermissions](https://github.com/getActivity/XXPermissions)

* 吐司框架：[ToastUtils](https://github.com/getActivity/ToastUtils)

* 网络框架：[EasyHttp](https://github.com/getActivity/EasyHttp)

* 标题栏框架：[TitleBar](https://github.com/getActivity/TitleBar)

* 国际化框架：[MultiLanguages](https://github.com/getActivity/MultiLanguages)

* 悬浮窗框架：[XToast](https://github.com/getActivity/XToast)

* Gson 解析容错：[GsonFactory](https://github.com/getActivity/GsonFactory)

* 日志查看框架：[Logcat](https://github.com/getActivity/Logcat)

#### 微信公众号：Android轮子哥

![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/official_ccount.png)

#### Android 技术分享 QQ 群：78797078

#### 如果您觉得我的开源库帮你节省了大量的开发时间，请扫描下方的二维码随意打赏，要是能打赏个 10.24 :monkey_face:就太:thumbsup:了。您的支持将鼓励我继续创作:octocat:

![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/pay_ali.png) ![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/pay_wechat.png)

#### [点击查看捐赠列表](https://github.com/getActivity/Donate)

## License

```text
Copyright 2021 Huang JinQun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```