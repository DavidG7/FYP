package finalyearproject.drawer.Portfolio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 12/02/2015.
 */
public class PortfolioChartActivity extends Activity{

    LinearLayout mToBeInvisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        mToBeInvisible = (LinearLayout) findViewById(R.id.ll_chart_ticker);
        mToBeInvisible.setVisibility(View.GONE);
    }
}
