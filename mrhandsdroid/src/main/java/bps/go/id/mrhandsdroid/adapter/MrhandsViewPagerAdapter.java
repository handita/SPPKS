package bps.go.id.mrhandsdroid.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handi_000 on 6/27/2015.
 */
public class MrhandsViewPagerAdapter extends PagerAdapter {
    List<View> listView;

    int views[];

    public int getCount() {
        return views.length;
    }

    public List<View> getViews() {
        return this.listView;
    }

    public int[] getViewPosition() {
        return this.views;
    }

    LayoutInflater mInflater;

    public MrhandsViewPagerAdapter(Context context, int[] views) {
        this.views = views;
        listView = new ArrayList<View>();
        mInflater = LayoutInflater.from(context);
        for (int i : this.views) {
            View v = mInflater.inflate(i, null);
            listView.add(v);
        }
    }

    public Object instantiateItem(View collection, int position) {
        View view = this.listView.get(position);
        ((ViewPager) collection).addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);

    }

    @Override
    public void finishUpdate(View arg0) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "halaman " + (position + 1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);

    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }
}

