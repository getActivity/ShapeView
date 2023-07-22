package com.hjq.shape.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2023/07/15
 *    desc   : ShapeDrawable 工具类（仅供内部使用）
 */
final class ShapeDrawableUtils {
   
   static void saveCanvasLayer(Canvas canvas, float left, float top, float right, float bottom, @Nullable Paint paint) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         canvas.saveLayer(left, top, right, bottom, paint);
      } else {
         canvas.saveLayer(left, top, right, bottom, paint, 0x04);
      }
   }

   static float[] computeLinearGradientCoordinate(RectF r, float level, int orientation) {
      float x0, x1, y0, y1;
      switch (orientation) {
         case ShapeGradientOrientation.TOP_TO_BOTTOM:
            x0 = r.left;            y0 = r.top;
            x1 = x0;                y1 = level * r.bottom;
            break;
         case ShapeGradientOrientation.TOP_RIGHT_TO_BOTTOM_LEFT:
            x0 = r.right;           y0 = r.top;
            x1 = level * r.left;    y1 = level * r.bottom;
            break;
         case ShapeGradientOrientation.RIGHT_TO_LEFT:
            x0 = r.right;           y0 = r.top;
            x1 = level * r.left;    y1 = y0;
            break;
         case ShapeGradientOrientation.BOTTOM_RIGHT_TO_TOP_LEFT:
            x0 = r.right;           y0 = r.bottom;
            x1 = level * r.left;    y1 = level * r.top;
            break;
         case ShapeGradientOrientation.BOTTOM_TO_TOP:
            x0 = r.left;            y0 = r.bottom;
            x1 = x0;                y1 = level * r.top;
            break;
         case ShapeGradientOrientation.BOTTOM_LEFT_TO_TOP_RIGHT:
            x0 = r.left;            y0 = r.bottom;
            x1 = level * r.right;   y1 = level * r.top;
            break;
         case ShapeGradientOrientation.LEFT_TO_RIGHT:
            x0 = r.left;            y0 = r.top;
            x1 = level * r.right;   y1 = y0;
            break;
         case ShapeGradientOrientation.TOP_LEFT_TO_BOTTOM_RIGHT:
         default:
            x0 = r.left;            y0 = r.top;
            x1 = level * r.right;   y1 = level * r.bottom;
            break;
      }
      return new float[] {x0, y0, x1, y1};
   }
}