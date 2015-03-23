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
import android.widget.RelativeLayout;
import android.widget.TextView;

import finalyearproject.drawer.ChartFragment.BarChartFragment;
import finalyearproject.drawer.ChartFragment.LineChartFragment;
import finalyearproject.drawer.Main.MainActivity;
import finalyearproject.drawer.PortfolioTransactionHistory.PortfolioTransactionActivity;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 06/02/2015.
 */
public class PortfolioFragment extends Fragment{

    Fragment mPortChart;
    TextView mPortfolioValueView;
    ImageButton mPortTransHistory;

    String portfolioStringValue,portfolioStringCost;
    TextView mPortValueInfo;
    RelativeLayout  mRelatveLayoutFragment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.frag_portfolio, container, false);
        mPortfolioValueView = (TextView) android.findViewById(R.id.tv_portfolio_value);
        mPortTransHistory = (ImageButton) android.findViewById(R.id.ib_trans_history);
        mPortValueInfo = (TextView) android.findViewById(R.id.tv_portfolio_info);
        //mPortValue.setImageDrawable(new TextDrawable(mPortValue,"i"));

        portfolioStringValue = ("€" + Double.toString(((MainActivity) this.getActivity()).getPortfolioValue()));
        portfolioStringCost = ("€" + Double.toString(((MainActivity) this.getActivity()).getPortfolioCost()));
        mPortfolioValueView.setText(portfolioStringValue);




        mRelatveLayoutFragment = (RelativeLayout) android.findViewById(R.id.rl_chart_port);
        mRelatveLayoutFragment.setOnClickListener(new View.OnClickListener() {
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

        mPortValueInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPortValueInfo.setTextColor(getResources().getColor(R.color.list_divider));
                mPortValueInfo.setBackgroundResource(R.drawable.mycircle_white);
                /*mMaterialDialog = new MaterialDialog(getActivity())

                        .setTitle("Portfolio Value")
                        .setMessage("Current Value: " + portfolioStringValue + "\n"+ "BoughtValue: " + portfolioStringCost)
                                //.setBackgroundResource(R.drawable.dublin_watchlist)
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                mPortValueInfo.setTextColor(getResources().getColor(R.color.White));
                                mPortValueInfo.setBackgroundResource(R.drawable.mycircle);

                            }
                        });


                mMaterialDialog.show();*/
            }
        });


        mPortChart= new LineChartFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fl_chart_port,mPortChart);
        transaction.commit();

        return android;
    }








}
