package com.example.miras.umbrelladrops;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Umbrella extends View {

    public boolean validate = false;

    List<Point> points = new ArrayList<>();
    int frame = 0;
    int speed = 10;

    public Umbrella(Context context) {
        super(context);
    }

    public Umbrella(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        umbrellaDrop(canvas);
        if (validate) invalidate();
    }

    private void umbrellaDrop(Canvas canvas) {
        int prevx = 0;

        for (Point bound : points) {
            //bound = new Point (bound.x, bound.y + speed);
            bound.set(bound.x, bound.y+speed);
            Bitmap original = BitmapFactory.decodeResource(getResources() ,R.drawable.umbrella2);
            Bitmap scaled = Bitmap.createScaledBitmap(original, 200, 200, true);

            canvas.drawBitmap(scaled, bound.x, bound.y, null);
        }

        frame++;
        if (frame % 30 == 0) {
            Random random = new Random();

            int xnew = random.nextInt(canvas.getWidth());
            while (xnew > canvas.getWidth() - 200 && Math.abs(prevx - xnew) > 200)
                xnew = random.nextInt(canvas.getWidth());
            int ynew = 0;
            prevx = xnew;
            points.add(new Point(xnew, ynew));
        }


        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
