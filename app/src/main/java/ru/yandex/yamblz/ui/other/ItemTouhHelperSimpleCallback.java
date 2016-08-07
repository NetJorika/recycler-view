package ru.yandex.yamblz.ui.other;

/**
 * Created by GEORGY on 07.08.2016.
 */

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import ru.yandex.yamblz.ui.fragments.ContentAdapter;

public class ItemTouhHelperSimpleCallback extends ItemTouchHelper.SimpleCallback {
    private ContentAdapter contentAdapter;

    public ItemTouhHelperSimpleCallback(ContentAdapter contentAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                , ItemTouchHelper.RIGHT);
        this.contentAdapter = contentAdapter;
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
}