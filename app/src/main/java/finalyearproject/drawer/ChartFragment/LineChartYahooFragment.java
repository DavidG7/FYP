package finalyearproject.drawer.ChartFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import finalyearproject.drawer.POJO.Quote;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 26/03/2015.
 */
public class LineChartYahooFragment extends Fragment {


    WebView mYahooChart;
    private static int ZOOM_LEVEL = 125;
    Quote mQuote;

    public LineChartYahooFragment(Quote quote){
        this.mQuote = quote;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  yahooBarGraph = inflater.inflate(R.layout.barchart_yahoo, container, false);

        String tickerIdBeforeFullStop = mQuote.getsymbol().split("\\.")[0];

        mYahooChart = (WebView) yahooBarGraph.findViewById(R.id.wv_yahoo_chart);
        WebSettings webSettings = mYahooChart.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mYahooChart.setWebViewClient(new MyWebViewClient());
        mYahooChart.setInitialScale(ZOOM_LEVEL);
        mYahooChart.scrollTo(250,475);
        mYahooChart.loadUrl("http://www.marketwatch.com/investing/stock/"+tickerIdBeforeFullStop+"/charts?CountryCode=ie");

        return yahooBarGraph;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }
    }






}
