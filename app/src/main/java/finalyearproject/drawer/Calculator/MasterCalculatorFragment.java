package finalyearproject.drawer.Calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalyearproject.drawer.ChartFragment.BarChartFragment;
import finalyearproject.drawer.ChartFragment.LineChartFragment;
import finalyearproject.drawer.ChartFragment.PieChartFragment;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 24/03/2015.
 */
public class MasterCalculatorFragment extends Fragment{

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    Fragment[] chartFragments = new Fragment[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View charts = inflater.inflate(R.layout.calculator, container, false);

        mPager = (ViewPager) charts.findViewById(R.id.vp_calculator_pager);
        mPagerAdapter = new ScreenSlideCalculatorPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        return charts;
    }



    private class ScreenSlideCalculatorPagerAdapter extends FragmentPagerAdapter {




        public ScreenSlideCalculatorPagerAdapter(FragmentManager fm) {
            super(fm);
            chartFragments[0] = new FoolsRatioCalculatorFragment();
            chartFragments[1] = new FoolsRatioCalculatorFragment();

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

}
