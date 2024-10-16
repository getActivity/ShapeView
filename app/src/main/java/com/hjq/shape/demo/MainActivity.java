package com.hjq.shape.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.shape.view.ShapeButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleBar titleBar = findViewById(R.id.tb_main_bar);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onTitleClick(TitleBar titleBar) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(titleBar.getTitle().toString()));
                startActivity(intent);
            }
        });

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