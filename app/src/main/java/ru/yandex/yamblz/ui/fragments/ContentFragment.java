package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.other.ItemTouhHelperSimpleCallback;

public class ContentFragment extends BaseFragment {
    private int spanCount = 1;

    @BindView(R.id.rv)
    RecyclerView rv;
    private GridLayoutManager grid;
    static final String SPAN_COUNT = "SPAN_COUNT";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            spanCount = savedInstanceState.getInt(SPAN_COUNT, 1);
        }
        grid = new GridLayoutManager(getContext(), spanCount);
        rv.setLayoutManager(grid);

        ContentAdapter adapter = new ContentAdapter();
        adapter.setHasStableIds(true);
        rv.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouhHelperSimpleCallback(adapter));
        helper.attachToRecyclerView(rv);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_column){
            spanCount++;
            spanCount = Math.min(30, spanCount);
            grid.setSpanCount(spanCount);
            rv.requestLayout();
            rv.getAdapter().notifyDataSetChanged();
            return true;
        }
        if (id == R.id.del_column) {
            spanCount--;
            spanCount = Math.max(1, spanCount);
            grid.setSpanCount(spanCount);
            rv.requestLayout();
            rv.getAdapter().notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SPAN_COUNT, spanCount);
    }
}
