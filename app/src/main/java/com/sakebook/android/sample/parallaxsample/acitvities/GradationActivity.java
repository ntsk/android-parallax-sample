package com.sakebook.android.sample.parallaxsample.acitvities;

import android.animation.ArgbEvaluator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

import com.sakebook.android.sample.parallaxsample.views.adapters.ParallaxPagerAdapter;
import com.sakebook.android.sample.parallaxsample.R;
import com.sakebook.android.sample.parallaxsample.fragments.GradationFragment;

public class GradationActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private SparseArray<Fragment> viewSparseArray = new SparseArray<>();
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private Integer[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradation);
        initColor();
        initViewPager();
    }

    private void initColor() {
        this.colors = new Integer[]{
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.purple),
                ContextCompat.getColor(this, R.color.orange),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.orange), // for end evaluate
        };
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ParallaxPagerAdapter adapter = new ParallaxPagerAdapter(fragmentManager, this, makeFragments());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private SparseArray<Fragment> makeFragments() {
        viewSparseArray.put(0, GradationFragment.newInstance(R.string.gradation_title_red));
        viewSparseArray.put(1, GradationFragment.newInstance(R.string.gradation_title_green));
        viewSparseArray.put(2, GradationFragment.newInstance(R.string.gradation_title_purple));
        viewSparseArray.put(3, GradationFragment.newInstance(R.string.gradation_title_orange));
        viewSparseArray.put(4, GradationFragment.newInstance(R.string.gradation_title_blue));
        return viewSparseArray;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        viewPager.setBackgroundColor((Integer) mArgbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
