package com.example.miras.paintpencil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miras on 01.11.2017.
 */

public class PaintBoard extends View{

    public Boolean clear = false;

    List<Path> paths;
    List<Paint> paints;

    public PaintBoard(Context context) {
        super(context);
        paths = new ArrayList<>();
        paints = new ArrayList<>();
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paths = new ArrayList<>();
        paints = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint black = new Paint();
        black.setColor(Color.BLACK);

        if (clear == true) {
            canvas.drawRect(0, 0, canvas.getHeight(), canvas.getWidth(), black);

            for (int i = 0; i < paths.size(); ++i)
                paths.get(i).reset();

            clear = false;
        }



        for (int i = 0; i < paths.size(); ++i)
        canvas.drawPath(paths.get(i), paints.get(i));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                paths.get(paths.size()-1).moveTo(event.getX(), event.getY());
                invalidate();
                return true;
        }
        else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            paths.get(paths.size()-1).lineTo(event.getX(), event.getY());
            invalidate();
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            paths.get(paths.size()-1).lineTo(event.getX(), event.getY());
            invalidate();
        }

        return false;
    }


}
