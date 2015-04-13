

package finalyearproject.iseqStockExchange.PortfolioTransactionHistory;

/**
 * Created by Dvaid on 24/11/2014.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//import com.google.inject.Inject;

import java.util.ArrayList;

import finalyearproject.iseqStockExchange.Constants.Constants;
import finalyearproject.iseqStockExchange.Dialogs.TransHistoryMaterialDialog;
import finalyearproject.iseqStockExchange.R;
import finalyearproject.iseqStockExchange.SQLiteDatabase.StockPurchase;


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
            if(mModelData.get(position).getType().equals(Constants.BUY)){
                holder.mTransIcon.setImageResource(R.drawable.bought_image_green);
                holder.mTransTicker.setTextColor(mContext.getResources().getColor(R.color.change_pos));
                holder.mTransNum.setTextColor(mContext.getResources().getColor(R.color.change_pos));
            }else if(mModelData.get(position).getType().equals(Constants.SELL)){
                holder.mTransIcon.setImageResource(R.drawable.sold_image_red);
                holder.mTransTicker.setTextColor(mContext.getResources().getColor(R.color.change_neg));
                holder.mTransNum.setTextColor(mContext.getResources().getColor(R.color.change_neg));
            }

            holder.mTransMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransHistoryMaterialDialog dialog = new TransHistoryMaterialDialog(mContext, mModelData, position);
                    dialog.build();
                }
            });

           //holder.mTransDate.setText(mModelData.get(position).getDate().substring(0,5));

    }





    @Override
    public int getItemCount() {
       return mModelData.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTransTicker,mTransNum,mTransDate;
        ImageButton mTransMore;
        ImageView mTransIcon;

        public ListItemViewHolder(final View itemView) {
            super(itemView);
            mTransTicker = (TextView) itemView.findViewById(R.id.tv_trans_list_ticker);
            mTransNum = (TextView) itemView.findViewById(R.id.tv_trans_list_num);
          //  mTransDate = (TextView) itemView.findViewById(R.id.tv_trans_list_date);
            mTransMore= (ImageButton) itemView.findViewById(R.id.ib_trans_list_more);
            mTransIcon = (ImageView) itemView.findViewById(R.id.iv_trans_list_icon);
        }
    }




}


