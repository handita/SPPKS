package bps.go.id.sppks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import bps.go.id.mrhandsdroid.adapter.MrhandsViewPagerAdapter;

/**
 * Created by handi_000 on 6/29/2015.
 */
public abstract class AbstractKuesionerActivity extends Activity {


    String [] bulanArray ={
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
    };

    ViewPager viewPager;
    PageIndicator indicator;

    public abstract int [] getViews();

    MrhandsViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuesioner);
//        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        adapter=new MrhandsViewPagerAdapter(this,getViews());

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(adapter.getCount());

        indicator=(CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}
