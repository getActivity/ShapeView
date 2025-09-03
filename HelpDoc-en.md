## FAQ

#### Why does my setXxx call not take effect?

* If you are setting attributes for Shape, shadow, or background state selector, you need to call the `intoBackground` method to take effect.

* If you are setting text color or state selector attributes, you need to call the `intoTextColor` method to take effect.

* If you are setting the icon or state selector attributes for **CheckBox** or **RadioButton**, you need to call the `intoButtonDrawable` method to take effect.

#### What if the framework is too intrusive?

* Many people say that the framework is very intrusive after it went online. I must admit this. I have also seen implementations using `LayoutInflater.Factory` or DataBinding, which are less intrusive, but there is a fatal flaw: you cannot preview in the layout editor. Doesn't that suddenly make it less appealing? High intrusiveness certainly has disadvantages but also advantages. We can't just look at the bad side, otherwise, the view is too one-sided. However, its shortcomings are not fatal. For example, if you use a custom View called `XxxTextView`, you definitely can't use **ShapeTextView** anymore. So what should we do in this case? There are roughly two solutions:

  * The first is to use the native Shape to implement it, either by defining it in xml or setting it dynamically in code. Everyone should understand this approach, so I won't elaborate. However, there is a problem: the native Shape does not support setting shadows. If you want to use shadows, you have to use the second approach.

  * The second is to use the **ShapeDrawable** class provided by the framework and set it dynamically in Java code. The usage is actually very simple: whichever attribute you use in the layout, use the corresponding method in code.

  * Another point to note: if you use **GradientDrawable** or **ShapeDrawable** alone in Java code and involve dashed lines or shadows, it has been verified that on some devices it will not take effect unless you turn off hardware acceleration. Of course, **ShapeDrawable** exposes the **intoBackground** method, which will help you determine whether you need to turn off hardware acceleration.

* Currently, no Shape framework can be perfect. It depends on personal choice. Regardless of pros and cons, while enjoying the advantages of the framework, you also need to tolerate its shortcomings.

#### Can Layout clipping sub-View functionality be added?

* I refuse to do this because the positioning of the framework is very clear: it is only to help everyone write less xml. If you ask me to add a clipping function, is that appropriate? No, it's not appropriate. I personally recommend using Google's **CardView** for clipping sub-Views, and if necessary, you can use it together with **ShapeDrawable**.

#### Can ImageView rounded corner clipping be added?

* For rounded corner clipping of the src in ImageView, first, this attribute belongs to the content of the View, while the framework is for setting the background of the View. Second, even if you want to do clipping, the Shape rounded corner attribute is set to the background Drawable of the View. When it comes to the src of ImageView, how do you distinguish between the two? (Should the rounded corner be applied to the background or the src? What if the background's rounded corner is large and the src's is small?) Some people may say, just add a few more attributes, right? Yes, but the framework will become complicated, and most users may not be able to distinguish between them. I don't think it's necessary. The rounded corner function can be uniformly implemented by Glide. If the framework does it again, it will be redundant.

#### Finally, let me share my opinion

* Finally, let me share my opinion. I think making a good framework does not mean implementing every function. It's not that I can't do it, but whether it's necessary. If you do everything, the framework may eventually become a general store, and even the author may not know what the framework is for. For example, when I made the [TitleBar framework](https://github.com/getActivity/TitleBar), many people asked me to add immersive status bar functionality, but I refused and suggested they use a separate immersive framework. First, let me clarify: what I made is a title bar framework, and immersive status bar is a function that should be provided by an immersive framework. If I break the rule and add immersive status bar functionality, later people will ask me to add immersive bottom navigation bar, and then status bar font color change... If I do all these, will you still use such a framework that binds the title bar and immersive together? Should it be called a title bar framework or an immersive framework? What if someone only wants the title bar function but not the immersive function?

* After hearing this, I believe you can understand my thoughts. I put a lot of thought into making the framework. The time spent refactoring code is far more than writing code. The time spent thinking about problems is far more than refactoring code. Making a good framework is not just about writing code. More importantly, I know what everyone wants and what effects you want. At the same time, I will humbly accept all kinds of suggestions, but likewise, I will refuse all unreasonable requests.