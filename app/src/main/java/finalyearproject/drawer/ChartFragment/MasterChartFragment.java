package finalyearproject.drawer.ChartFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 21/01/2015.
 */
public class MasterChartFragment extends Fragment {

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView mLine,mBar,mPie;

    private LineChart mLineChart;
    private BarChart mBarChart;
    private PieChart mPieChart;

    Fragment[] chartFragments = new Fragment[3];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View charts = inflater.inflate(R.layout.chart, container, false);

        mPager = (ViewPager) charts.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        mLine = (ImageView) charts.findViewById(R.id.iv_line_chart);
        mBar = (ImageView) charts.findViewById(R.id.iv_bar_chart);
        mPie = (ImageView) charts.findViewById(R.id.iv_pie_chart);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 Log.i("CURRENT_PAGE: ", Integer.toString(position));
                 iconSwitch(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return charts;
    }




/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    int count = 0;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
        chartFragments[0] = new BarChartFragment();
        chartFragments[1] = new LineChartFragment();
        chartFragments[2] = new PieChartFragment();
    }

    @Override
    public Fragment getItem(int position) {
            return chartFragments[position];
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }



}
    public ViewPager getPager(){
        return mPager;
    }




    public void iconSwitch(int position){
        switch ( position ) {
           case 0:
                mBar.setImageResource(R.drawable.bar_chart_selected);
                mLine.setImageResource(R.drawable.line_chart);
                mPie.setImageResource(R.drawable.pie_chart);
                break;
            case 1:
                mBar.setImageResource(R.drawable.bar_chart);
                mLine.setImageResource(R.drawable.line_chart_selected);
                mPie.setImageResource(R.drawable.pie_chart);
                break;
            case 2:
                mBar.setImageResource(R.drawable.bar_chart);
                mLine.setImageResource(R.drawable.line_chart);
                mPie.setImageResource(R.drawable.pie_chart_selected);
                break;
        }
    }

}

