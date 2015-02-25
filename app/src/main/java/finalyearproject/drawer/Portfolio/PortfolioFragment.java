package finalyearproject.drawer.Portfolio;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import finalyearproject.drawer.ChartFragment.BarChartFragment;
import finalyearproject.drawer.Main.MainActivity;
import finalyearproject.drawer.PortfolioTransactionHistory.PortfolioTransactionActivity;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 06/02/2015.
 */
public class PortfolioFragment extends Fragment{

    Fragment mPortChart;
    TextView mPortfolioValueView;
    FrameLayout mFragmentContainer;
    ImageButton mPortTransHistory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.frag_portfolio, container, false);
        mPortfolioValueView = (TextView) android.findViewById(R.id.tv_portfolio_value);
        mPortTransHistory = (ImageButton) android.findViewById(R.id.ib_trans_history);
        mPortfolioValueView.setText(Double.toString(((MainActivity) this.getActivity()).getPortfolioValue()));

        mFragmentContainer = (FrameLayout) android.findViewById(R.id.fl_chart_port);

        mFragmentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent portfolioChart = new Intent(getActivity(), PortfolioChartActivity.class);
                startActivity(portfolioChart);
            }
        });

        mPortTransHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent portfolioTrans = new Intent(getActivity(), PortfolioTransactionActivity.class);
                startActivity(portfolioTrans);*/
               /* Fragment transCircle = new PortfolioTransactionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, transCircle);
                transaction.addToBackStack(null);
                transaction.commit();*/
                Intent trans_history = new Intent(getActivity(), PortfolioTransactionActivity.class);
                startActivity(trans_history);
            }
        });

        mPortChart= new BarChartFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fl_chart_port,mPortChart);
        transaction.commit();
        return android;
    }








}
