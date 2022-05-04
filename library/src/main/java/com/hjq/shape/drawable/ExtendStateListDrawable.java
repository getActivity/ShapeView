package com.hjq.shape.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.util.HashMap;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2022/04/23
 *    desc   : 基于 StateListDrawable 类进行扩展
 */
public class ExtendStateListDrawable extends StateListDrawable {

   private static final int[] STATE_DEFAULT = new int[]{};
   private static final int[] STATE_PRESSED = new int[]{android.R.attr.state_pressed};
   private static final int[] STATE_CHECKED = new int[]{android.R.attr.state_checked};
   private static final int[] STATE_DISABLED = new int[]{-android.R.attr.state_enabled};
   private static final int[] STATE_FOCUSED = new int[]{android.R.attr.state_focused};
   private static final int[] STATE_SELECTED = new int[]{android.R.attr.state_selected};

   private final HashMap<int[], Drawable> mDrawableMap = new HashMap<>();

   @Override
   public void addState(int[] stateSet, Drawable drawable) {
      super.addState(stateSet, drawable);
      if (drawable == null) {
         return;
      }
      mDrawableMap.put(stateSet, drawable);
   }

   public void setDefaultDrawable(Drawable drawable) {
      addState(STATE_DEFAULT, drawable);
   }

   public Drawable getDefaultDrawable() {
      return mDrawableMap.get(STATE_DEFAULT);
   }

   public void setPressedDrawable(Drawable drawable) {
      addState(STATE_PRESSED, drawable);
   }

   public Drawable getPressedDrawable() {
      return mDrawableMap.get(STATE_PRESSED);
   }

   public void setCheckDrawable(Drawable drawable) {
      addState(STATE_CHECKED, drawable);
   }

   public Drawable getCheckDrawable() {
      return mDrawableMap.get(STATE_CHECKED);
   }

   public void setDisabledDrawable(Drawable drawable) {
      addState(STATE_DISABLED, drawable);
   }

   public Drawable getDisabledDrawable() {
      return mDrawableMap.get(STATE_DISABLED);
   }

   public void setFocusedDrawable(Drawable drawable) {
      addState(STATE_FOCUSED, drawable);
   }

   public Drawable getFocusedDrawable() {
      return mDrawableMap.get(STATE_FOCUSED);
   }

   public void setSelectDrawable(Drawable drawable) {
      addState(STATE_SELECTED, drawable);
   }

   public Drawable getSelectDrawable() {
      return mDrawableMap.get(STATE_SELECTED);
   }
}