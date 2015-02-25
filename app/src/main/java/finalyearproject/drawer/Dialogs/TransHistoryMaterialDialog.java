package finalyearproject.drawer.Dialogs;

import android.content.Context;
import android.view.View;

import finalyearproject.drawer.SQLiteDatabase.StockPurchase;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Dvaid on 19/02/2015.
 */
public class TransHistoryMaterialDialog {

    Context mContext;
    MaterialDialog mMaterialDialog;
    StockPurchase[] mLastTenRecords;
    int mPosition;

    public TransHistoryMaterialDialog(Context context,StockPurchase[] lastTenRecords, int position){
        this.mContext = context;
        this.mLastTenRecords = lastTenRecords;
        this.mPosition = position;
    }

    public void build() {

       // ListView contentView = new ListView(mContext);

        String[] stringArray = new String[] { mLastTenRecords[mPosition].getTickerId(),
                Integer.toString(mLastTenRecords[mPosition].getNum()),Double.toString(mLastTenRecords[mPosition].getTotalCost()),
                Double.toString(mLastTenRecords[mPosition].getTotalValue()) };

        //ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
        //contentView.setAdapter(modeAdapter);
        mMaterialDialog = new MaterialDialog(mContext)

                .setView(new TransHistoryMaterialDialogView(mContext,mLastTenRecords[mPosition]))
                //.setBackgroundResource(R.drawable.dublin_watchlist)
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();

                    }
                });


        mMaterialDialog.show();
    }
}