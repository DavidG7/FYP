package finalyearproject.drawer.Calculator;

/**
 * Created by Dvaid on 09/01/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import finalyearproject.drawer.R;


public class CalculatorFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.frag_calculator, container, false);
        ((TextView)android.findViewById(R.id.textView)).setText("Calculator");
        return android;
    }}
