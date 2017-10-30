package com.example.miras.chessboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Miras on 25.10.2017.
 */

public class Chess extends View{
    public Chess(Context context) {
        super(context);
    }

    public Chess(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint black = new Paint();
        black.setColor(Color.BLACK);

        int d = 100,cnt = 0;

        for (int j = 0; j < canvas.getHeight(); j += d)
            for (int i = 0; i < canvas.getWidth(); i += d)
                if (cnt++ % 2 == 0)  canvas.drawRect(i, j, i+d, j+d, black);
    }
}
