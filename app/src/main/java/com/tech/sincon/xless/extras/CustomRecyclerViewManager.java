package com.tech.sincon.xless.extras;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by getcore03 on 7/4/2016.
 */
public class CustomRecyclerViewManager {
    private static RecyclerView list;
    private static LinearLayoutManager layoutManager;

    public  static RecyclerView initList(View view, Context c, int id){

        list = (RecyclerView) view.findViewById(id);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);


        return list;
    }
}
