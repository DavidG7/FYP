package finalyearproject.drawer.ChartFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.SpinnerEvent;
import finalyearproject.drawer.EventBus.WatchlistToISEQEvent;
import finalyearproject.drawer.POJO.Quote;
import finalyearproject.drawer.POJO.ResultWrapper;
import finalyearproject.drawer.R;


/**
 * Created by Dvaid on 21/01/2015.
 */
public class MasterChartFragment extends Fragment {

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView mLine,mBar,mPie;
    private TextView mSpinnerHeading;

    private ResultWrapper mResult;
    private Quote[] mQuotes;
    private Quote mActiveQuote;

    Fragment[] chartFragments = new Fragment[3];

    private ArrayList<String> state = new ArrayList<String>();

    public MasterChartFragment(ResultWrapper result){
        this.mResult = result;
        this.mQuotes = mResult.getQuery().getResults().getQuote();
        for(int i = 0;i<mQuotes.length;i++) {
            state.add(mQuotes[i].getsymbol());
        }
        mActiveQuote = mQuotes[0];
        BusProvider.getInstance().register(this);

    }


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
        mSpinnerHeading = (TextView) charts.findViewById(R.id.chart_spinner_heading);

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

        ChartSpinnerAdapter spinnerAdapter = new ChartSpinnerAdapter(getActivity(),0,state);


        final Spinner spinner = (Spinner) charts.findViewById(R.id.chart_spinner);



        final ArrayAdapter<String> adapter1 = new ChartSpinnerAdapter(
                getActivity(), android.R.layout.simple_spinner_item,
                state);
        spinner.setAdapter(adapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                mSpinnerHeading.setText(mQuotes[position].getsymbol());
                mActiveQuote = mQuotes[position];
                //mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
                mPager.setAdapter(new ScreenSlidePagerAdapter(getChildFragmentManager()));
            }


            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        return charts;
    }




/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
        chartFragments[0] = new BarChartFragment(mActiveQuote);
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


    @Subscribe
    public void spinnerPressCallback(SpinnerEvent event) {
        String textForSpinnerHeading = event.getActiveQuoteText();
        int position = event.getPosition();
        mSpinnerHeading.setText(textForSpinnerHeading);
    }

}

