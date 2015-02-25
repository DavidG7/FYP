package finalyearproject.drawer.ISEQ;

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

import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.FavouritesEvent;
import finalyearproject.drawer.EventBus.ObserverEvent;
import finalyearproject.drawer.Main.StockItemRow;
import finalyearproject.drawer.R;
import finalyearproject.drawer.SQLiteDatabase.MySQLiteHelper;
import finalyearproject.drawer.SharedPreferences.SharedPref;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListItemViewHolder> {


    private List<StockItemRow> stockItems;
    private TypedArray ISEQIcons;
    private Context mContext;
    private ArrayList<Integer> favourites;
    private SharedPref pref;
    //private int fav_res;
    private TreeMap<Integer,Integer> fav_res = new TreeMap<Integer,Integer>();
    private boolean isWatchList;




    RecyclerViewAdapter(Context context, List modelData, boolean isWatchList) {
        if (modelData == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        this.stockItems = modelData;

        this.ISEQIcons = context.getResources().obtainTypedArray(R.array.iseq_icons);
        this.mContext = context;
        this.favourites = new ArrayList<Integer>();
        this.pref = new SharedPref(mContext);
        this.favourites = pref.loadSavedPreferences();
        this.isWatchList = isWatchList;
        //BusProvider.getInstance().register(this);

    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_row, viewGroup, false);
        return new ListItemViewHolder(itemView);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ListItemViewHolder viewHolder, int position) {
        StockItemRow stockModel = stockItems.get(position);
        viewHolder.txtViewSymbol.setText(stockModel.getSymbol());
        viewHolder.txtViewChange.setText(stockModel.getChange());
        viewHolder.txtViewPrice.setText("€" +   Double.toString(stockModel.getLastTradePrice()));
        if(isWatchList == true){

            //viewHolder.icon.setImageResource(ISEQIcons.getResourceId(favourites.get(position), -1));
           Picasso.with(mContext).load(ISEQIcons.getResourceId(favourites.get(position), -1)).into(viewHolder.icon);
        }else {
            //viewHolder.icon.setImageResource(ISEQIcons.getResourceId(position, -1));
           Picasso.with(mContext).load(ISEQIcons.getResourceId(position, -1)).into(viewHolder.icon);
        }

        String temp = stockModel.getChange();
        if(Double.parseDouble(temp.substring(0,temp.length()-1))>0){
            viewHolder.change.setImageResource(R.drawable.change_pos);
            viewHolder.txtViewChange.setTextColor(mContext.getResources().getColor(R.color.change_pos));
            viewHolder.txtViewSymbol.setTextColor(mContext.getResources().getColor(R.color.change_pos));
            viewHolder.txtViewPrice.setTextColor(mContext.getResources().getColor(R.color.change_pos));

        }else if (Double.parseDouble(temp.substring(0,temp.length()-1))<0){
            viewHolder.change.setImageResource(R.drawable.change_neg);
            viewHolder.txtViewChange.setTextColor(mContext.getResources().getColor(R.color.change_neg));
            viewHolder.txtViewSymbol.setTextColor(mContext.getResources().getColor(R.color.change_neg));
            viewHolder.txtViewPrice.setTextColor(mContext.getResources().getColor(R.color.change_neg));
        }else if(Double.parseDouble(temp.substring(0,temp.length()-1))==0){
            viewHolder.change.setImageResource(R.drawable.change_neu);
            viewHolder.txtViewChange.setTextColor(mContext.getResources().getColor(R.color.change_neu));
            viewHolder.txtViewSymbol.setTextColor(mContext.getResources().getColor(R.color.change_neu));
            viewHolder.txtViewPrice.setTextColor(mContext.getResources().getColor(R.color.change_neu));
        }

        if(isWatchList==true){
            //fav_res = R.drawable.star_pressed;
            fav_res.put(position,R.drawable.star_pressed);
        }else {
            //fav_res = R.drawable.star;
            int test = R.drawable.star;
            for (int i = 0; i < favourites.size(); i++) {
                if (position == favourites.get(i)) {
                    test = R.drawable.star_pressed;
                    break;
                }
            }
            fav_res.put(position,test);
        }


        viewHolder.star.setImageResource(fav_res.get(position));


        viewHolder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isWatchList == true) {
                  removeAt(viewHolder.getPosition());
                  favourites.remove(viewHolder.getPosition());

                    pref.savePreferences(favourites);
                } else if (isWatchList == false) {
                    if (fav_res.get(viewHolder.getPosition()) == R.drawable.star) {

                        //fav_res = R.drawable.star_pressed;
                        HashSet tempHash = new HashSet();
                        tempHash.addAll(favourites);
                        tempHash.add(viewHolder.getPosition());
                        favourites.clear();
                        favourites.addAll(tempHash);

                        Collections.sort(favourites);
                        pref.savePreferences(favourites);
                        fav_res.remove(viewHolder.getPosition());
                        fav_res.put(viewHolder.getPosition(), R.drawable.star_pressed);


                    } else if (fav_res.get(viewHolder.getPosition()) == R.drawable.star_pressed) {


                        for (int i = 0; i < favourites.size(); i++) {
                            if (viewHolder.getPosition() == favourites.get(i)) {
                                favourites.remove(i);
                            }
                        }
                        pref.savePreferences(favourites);
                        fav_res.remove(viewHolder.getPosition());
                        fav_res.put(viewHolder.getPosition(), R.drawable.star);


                    }

                    BusProvider.getInstance().post(new FavouritesEvent(favourites));
                    viewHolder.star.setImageResource(fav_res.get(viewHolder.getPosition()));

                }
            }
        });


       viewHolder.extra.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String value;
               int position = viewHolder.getPosition();
               MySQLiteHelper stock_group = new MySQLiteHelper(mContext);
               stock_group.open();
               value = Double.toString(stockItems.get(position).getLastTradePrice());
               stock_group.createStockItemEntry(position,stockItems.get(position).getSymbol(),stockItems.get(position).getName(), 1, Double.parseDouble(value), 1 * Double.parseDouble(value),1 * Double.parseDouble(value));
               BusProvider.getInstance().post(new ObserverEvent());
               stock_group.close();
           }
       });


    }


    public void removeAt(int position){
        stockItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,stockItems.size());
    }



    @Override
    public int getItemCount() {
        return stockItems.size();
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewSymbol,txtViewPrice,txtViewChange;
        ImageView icon,change,star;
        ImageView extra;


        public ListItemViewHolder(final View itemView) {
            super(itemView);
            txtViewSymbol= (TextView) itemView.findViewById(R.id.tv_ticker);
            txtViewPrice = (TextView) itemView.findViewById(R.id.tv_last_trade_price);
            txtViewChange = (TextView) itemView.findViewById(R.id.tv_change);
            icon = (ImageView) itemView.findViewById(R.id.logo);
            change = (ImageView) itemView.findViewById(R.id.change_indicator);
            extra = (ImageView) itemView.findViewById(R.id.extra);
            star= (ImageView) itemView.findViewById(R.id.star);

        }
    }




}

