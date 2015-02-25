package finalyearproject.drawer.PortfolioTransactionHistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import finalyearproject.drawer.PortfolioTransactionHistory.TransHistoryView;
import finalyearproject.drawer.R;
import finalyearproject.drawer.SQLiteDatabase.MySQLiteHelper;
import finalyearproject.drawer.SQLiteDatabase.StockPurchase;

/**
 * Created by Dvaid on 24/02/2015.
 */
public class PortfolioTransactionCircleFragment extends Fragment {

    FrameLayout mTransCircleContainer;
    private StockPurchase[] mLastTenRecords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.frag_trans_circle, container, false);
        mTransCircleContainer = (FrameLayout) android.findViewById(R.id.fl_trans_circle_container);
        MySQLiteHelper stock_individual = new MySQLiteHelper(getActivity());
        stock_individual.open();
        mLastTenRecords = new StockPurchase[10];
        mLastTenRecords = stock_individual.getStockGroupEntry();
        stock_individual.close();
        mTransCircleContainer.addView(new TransHistoryView(getActivity(), mLastTenRecords));
        return android;
    }


    public StockPurchase[] getLastTenRecords() {
        return mLastTenRecords;
    }
}