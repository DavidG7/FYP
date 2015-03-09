package finalyearproject.drawer.Dialogs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import finalyearproject.drawer.R;
import finalyearproject.drawer.RecyclerViewAddOns.DividerItemDecoration;

/**
 * Created by Dvaid on 09/03/2015.
 */
public class ISEQDialog extends FrameLayout{

    SeekBar mBuySeekBar;
    TextView mStockHeading;
    Context mContext;
    View mView;
    RecyclerView mStockDataList;

    public ISEQDialog(Context context) {
        super(context);
        this.mContext = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){
            mView =  inflater.inflate(R.layout.stock_dialog, null);
        }

        mStockDataList = (RecyclerView) mView.findViewById(R.id.rv_stock_data_list);
        //
        mStockDataList.setAdapter(new ISEQDialofRecyclerViewAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        mStockDataList.setLayoutManager(layoutManager);
        mStockDataList.addItemDecoration(new DividerItemDecoration(mContext.getDrawable(R.drawable.divider)));


        addView(mView);
    }
}
