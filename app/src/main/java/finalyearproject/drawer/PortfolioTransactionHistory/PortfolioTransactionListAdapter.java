

package finalyearproject.drawer.PortfolioTransactionHistory;

/**
 * Created by Dvaid on 24/11/2014.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.inject.Inject;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import finalyearproject.drawer.Dialogs.TransHistoryMaterialDialog;
import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.FavouritesEvent;
import finalyearproject.drawer.EventBus.ObserverEvent;
import finalyearproject.drawer.ISEQ.RecyclerViewAdapter;
import finalyearproject.drawer.Main.StockItemRow;
import finalyearproject.drawer.R;
import finalyearproject.drawer.SQLiteDatabase.MySQLiteHelper;
import finalyearproject.drawer.SQLiteDatabase.StockPurchase;
import finalyearproject.drawer.SharedPreferences.SharedPref;


public class PortfolioTransactionListAdapter extends RecyclerView.Adapter<PortfolioTransactionListAdapter.ListItemViewHolder> {

    Context mContext;
    ArrayList <StockPurchase> mModelData;


    PortfolioTransactionListAdapter(Context context, ArrayList modelData) {
       this.mContext = context;
       this.mModelData = modelData;
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trans_list_item_row, viewGroup, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder,final int position) {
            holder.mTransTicker.setText(mModelData.get(position).getTickerId());
            holder.mTransNum.setText("(" + mModelData.get(position).getNum()+ ")");
            holder.mTransMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransHistoryMaterialDialog dialog = new TransHistoryMaterialDialog(mContext, mModelData, position);
                    dialog.build();
                }
            });

    }





    @Override
    public int getItemCount() {
       return mModelData.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTransTicker,mTransNum;
        ImageButton mTransMore;

        public ListItemViewHolder(final View itemView) {
            super(itemView);
            mTransTicker = (TextView) itemView.findViewById(R.id.tv_trans_list_ticker);
            mTransNum = (TextView) itemView.findViewById(R.id.tv_trans_list_num);
            mTransMore= (ImageButton) itemView.findViewById(R.id.ib_trans_list_more);
        }
    }




}


