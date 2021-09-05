package com.hjq.shape.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hjq.shape.view.ShapeButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}