package finalyearproject.drawer.Dialogs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import finalyearproject.drawer.R;
import finalyearproject.drawer.RecyclerViewAddOns.DividerItemDecoration;
import finalyearproject.drawer.SQLiteDatabase.StockPurchase;

/**
 * Created by Dvaid on 20/02/2015.
 */
public class TransHistoryMaterialDialogView extends FrameLayout {

    Context mContext;
    View view;
    ArrayList<String> mRecyclerViewData;
    String[] mListText;
    TextView mListTextView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TransHistoryMaterialDialogView(Context context,StockPurchase dataSource) {
        super(context);
        this.mContext = context;
        mRecyclerViewData = new ArrayList<String>();
        addData(dataSource);


        mListText = new String[]{"Ticker ID: ","Number of Stocks Bought: ","Cost of Purchase: ","Current Value: "};
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){
            view =  inflater.inflate(R.layout.custom_dialog_view, null);
        }
        mListTextView =(TextView) view.findViewById(R.id.tv_dialog_data);

        for(int i = 0;i<mListText.length;i++) {
            mListTextView.setText(mListText[i]+mRecyclerViewData.get(i)+"\n");
        }

       /* RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_custom_dialog);
      //
        recyclerView.setAdapter(new TransHistoryMaterialDialogRecyclerAdapter(mRecyclerViewData));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext.getDrawable(R.drawable.divider)));*/

        addView(view);

    }


    public void addData(StockPurchase dataSource){
        mRecyclerViewData.add(dataSource.getTickerId());
        mRecyclerViewData.add(Integer.toString(dataSource.getNum()));
        mRecyclerViewData.add(Double.toString(dataSource.getTotalCost()));
        mRecyclerViewData.add(Double.toString(dataSource.getCurValue()));
    }
}
