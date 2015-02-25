package finalyearproject.drawer.PortfolioTransactionHistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 23/02/2015.
 */
public class PortfolioTransactionListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View android = inflater.inflate(R.layout.frag_calculator, container, false);
        return android;
    }
}
