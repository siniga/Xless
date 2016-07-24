package com.tech.sincon.xless.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.sincon.xless.R;

/**
 * Created by getcore03 on 7/23/2016.
 */
public class WidthdrawTab extends Fragment {
    private View view;
    private FragmentActivity c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_withdraw, container, false);
        c = getActivity();


     /*   RecyclerView list = CustomRecyclerViewManager.initList(view, c);
        List<News> news = NewsModel.getNews();
        adapter = new NewsAdapter(c, news);
        list.setAdapter(adapter);*/

        return view;

    }
}
