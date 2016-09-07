package cn.hylstudio.android.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HYL on 2016/9/7.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布背景色
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        //绘制矩形，内部不填充
        // 抗锯齿
        paint.setAntiAlias(true);
        //画笔颜色
        paint.setColor(Color.GREEN);
        //设置填充类型
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔宽度
        paint.setStrokeWidth(8);
        //绘制普通矩形
        canvas.drawRect(50, 50, 300, 300, paint);
        //绘制三角形，内部填充
        Path path = new Path();
        path.moveTo(10, 500);
        path.lineTo(500, 120);
        path.lineTo(100, 500);
        path.close();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        //绘制文本
        paint.setTextSize(50);
        paint.setColor(Color.BLUE);
        paint.setStrikeThruText(true);
        canvas.drawText("hyl exercise", 15, 500, paint);
        Path pathText = new Path();
        pathText.addCircle(600, 600, 200, Path.Direction.CCW);
        canvas.drawTextOnPath("2014011311 hyl 2016/9/7", pathText, 0, 50, paint);
    }
}
