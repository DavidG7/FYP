package finalyearproject.drawer.ChartFragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.ExitEvent;
import finalyearproject.drawer.EventBus.SpinnerEvent;
import finalyearproject.drawer.R;

public class ChartSpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    ArrayList<String> mModelData = null;
    private TypedArray ISEQIcons;

    public ChartSpinnerAdapter(Context context, int resource, ArrayList<String> modelData) {
        super(context,resource,modelData);
        this.mContext = context;
        this.mModelData = modelData;
        this.ISEQIcons = context.getResources().obtainTypedArray(R.array.iseq_icons);

    }


    @Override
    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(mContext).inflate(R.layout.simple_spinner_item_1,null);
        }

        TextView mSpinnerTicker = (TextView) row.findViewById(R.id.tv_spinner_item_ticker);
        ImageView mSpinnerIcon = (ImageView) row.findViewById(R.id.iv_spinner_item_icon);

        mSpinnerTicker.setText(mModelData.get(position));
        Picasso.with(mContext).load(ISEQIcons.getResourceId(position, -1)).into(mSpinnerIcon);

       /*mSpinnerTicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusProvider.getInstance().post(new SpinnerEvent(mModelData.get(position),position));

            }
        });*/
        //BusProvider.getInstance().post(new SpinnerEvent(mModelData.get(position),position));
        return row;

    }
}