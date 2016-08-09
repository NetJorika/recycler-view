package ru.yandex.yamblz.ui.other;

/**
 * Created by GEORGY on 08.08.2016.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.yandex.yamblz.R;

public class BorderItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int strokeWidth = 5;
    private boolean draw = true;
    private int firstItem = -1;
    private int secondItem = -1;
    private Context context;
    private Drawable drawable;


    public BorderItemDecoration(Context context) {
        super();
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth * 2);
        drawable = ContextCompat.getDrawable(context, R.drawable.mark);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (!draw) return;
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < parent.getChildCount(); i++) {

            final View child = parent.getChildAt(i);
            int id = parent.getChildAdapterPosition(child) % 2;
            if (parent.getChildAdapterPosition(child) % 2 == 0) {
                c.drawRect(
                        layoutManager.getDecoratedLeft(child),
                        layoutManager.getDecoratedTop(child),
                        layoutManager.getDecoratedRight(child),
                        layoutManager.getDecoratedBottom(child),
                        paint);
            }

        }
        if (firstItem != -1 && secondItem != -1) {

            RecyclerView.ViewHolder child1Holder = parent.findViewHolderForAdapterPosition(firstItem);
            RecyclerView.ViewHolder child2Holder = parent.findViewHolderForAdapterPosition(secondItem);

            if (child1Holder != null && child2Holder != null) {
                onMarkSelectedItem(c, parent, child1Holder.itemView);
                onMarkSelectedItem(c, parent, child2Holder.itemView);
            }
        }
    }

    public void setIndexSelectedItem(int firstIndex, int secondIndex) {
        firstItem = firstIndex;
        secondItem = secondIndex;
    }

    private void onMarkSelectedItem(Canvas canvas, RecyclerView parent, View child) {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        drawable.setBounds(layoutManager.getDecoratedRight(child) - drawable.getMinimumWidth(),
                layoutManager.getDecoratedTop(child),
                layoutManager.getDecoratedRight(child),
                layoutManager.getDecoratedTop(child) + drawable.getMinimumHeight());
        drawable.draw(canvas);
    }
}