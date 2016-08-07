package ru.yandex.yamblz.ui.other;

/**
 * Created by GEORGY on 07.08.2016.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

public class ItemTouhHelperSimpleCallback extends ItemTouchHelper.SimpleCallback {
    private ContentAdapter contentAdapter;
    Paint paint;

    public ItemTouhHelperSimpleCallback(ContentAdapter contentAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                , ItemTouchHelper.RIGHT);
        this.contentAdapter = contentAdapter;
        paint = new Paint();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        contentAdapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        contentAdapter.removeItem(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            View itemView = viewHolder.itemView;
            if (dX > 0) {
                paint.setARGB((int) (255*Math.min(dX,itemView.getWidth())/itemView.getWidth()),255,0,0);
                c.drawRect(itemView.getLeft(),
                        (float) itemView.getTop(),
                        itemView.getLeft() + dX,
                        (float) itemView.getBottom(), paint);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

