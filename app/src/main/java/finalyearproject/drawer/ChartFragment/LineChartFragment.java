package finalyearproject.drawer.ChartFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 21/01/2015.
 */
public class LineChartFragment extends Fragment {

    LineChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View lineGraph = inflater.inflate(R.layout.linechart, container, false);

        chart = (LineChart) lineGraph.findViewById(R.id.line_chart);
        chart.animateXY(3000, 3000);
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(1.43f, 0); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1.22f, 1); // 1 == quarter 2 ...
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(1.27f, 2); // 0 == quarter 1
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(1.56f, 3); // 1 == quarter 2 ...
        valsComp1.add(c1e4);

        // and so on ...



        LineDataSet setComp1 = new LineDataSet(valsComp1, "BOI : Bank Of Ireland");
        setComp1.setColors(new int[] { R.color.list_divider}, getActivity());

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(setComp1);


        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Jan"); xVals.add("Feb"); xVals.add("March"); xVals.add("April");

        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);

        return lineGraph;
    }

    public LineChart getLineChart(){
        return chart;
    }

}
