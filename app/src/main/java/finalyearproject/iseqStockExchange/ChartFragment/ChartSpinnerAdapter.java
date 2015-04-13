package finalyearproject.iseqStockExchange.ChartFragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import finalyearproject.iseqStockExchange.R;

public class ChartSpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mModelData = null;
    private TypedArray mISEQIcons;
    private TextView mCurrentStockHeading;

    public ChartSpinnerAdapter(Context context, int resource, ArrayList<String> modelData,TextView currentStockHeading) {
        super(context,resource,modelData);
        this.mContext = context;
        this.mModelData = modelData;
        this.mISEQIcons = context.getResources().obtainTypedArray(R.array.iseq_icons);
        this.mCurrentStockHeading = currentStockHeading;
    }


    @Override
    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        View spinnerRow = convertView;
        if (spinnerRow == null) {
            spinnerRow = LayoutInflater.from(mContext).inflate(R.layout.chart_spinner_row_item,null);
        }

        TextView spinnerTicker = (TextView) spinnerRow.findViewById(R.id.tv_spinner_item_ticker);
        ImageView spinnerIcon = (ImageView) spinnerRow.findViewById(R.id.iv_spinner_item_icon);
        ImageView spinnerSelected = (ImageView) spinnerRow.findViewById(R.id.iv_spinner_item_indicator);


        spinnerTicker.setText(mModelData.get(position));
        if(mCurrentStockHeading.getText().equals(spinnerTicker.getText())){//If currently selected stock item set inidicator (R.drawble.mycircle)
            spinnerSelected.setImageResource(R.drawable.mycircle);
        }else{
            spinnerSelected.setImageResource(R.drawable.mycircle_white_scale);
        }
        Picasso.with(mContext).load(mISEQIcons.getResourceId(position, -1)).into(spinnerIcon);



        return spinnerRow;

    }
}