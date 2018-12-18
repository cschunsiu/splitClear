package com.splitclear.cschunsiu.splitclear.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.GroupRecycleAdapter;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

public class SwipeController extends ItemTouchHelper.SimpleCallback {

    public SwipeController(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        System.out.println("Hello");
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        final View foregroundView = ((GroupRecycleAdapter.GroupViewHolder) viewHolder).itemView.findViewById(R.id.view_foreground);
        try{
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //viewHolder.itemView.setTranslationX(dX / 5);
                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, (dX/5)*2, dY,
                        actionState, isCurrentlyActive);
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((GroupRecycleAdapter.GroupViewHolder) viewHolder).itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }
}
