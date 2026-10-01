package com.hjq.shape.other;

import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ShapeView
 *    time   : 2026/09/22
 *    desc   : 状态选择器工具类
 */
public final class StateSelectorUtils {

    /** 只校验控件自身交互状态，window_focused 排除 */
    private static final int[] INTERACTIVE_STATES = {
        android.R.attr.state_selected,
        android.R.attr.state_pressed,
        android.R.attr.state_focused,
        android.R.attr.state_activated,
        android.R.attr.state_checked,
        android.R.attr.state_hovered,
        android.R.attr.state_drag_hovered
    };

    private StateSelectorUtils() {
        // 工具类，禁止实例化
    }

    /**
     * 判断控件是否处于默认状态
     */
    public static boolean isDefaultState(View view) {
        int[] viewStates = view.getDrawableState();
        boolean hasInteractiveState = false;
        for (int checkState : INTERACTIVE_STATES) {
            for (int s : viewStates) {
                if (s == checkState) {
                    hasInteractiveState = true;
                    break;
                }
            }
            if(hasInteractiveState) {
                break;
            }
        }
        return !hasInteractiveState && view.isEnabled();
    }
}