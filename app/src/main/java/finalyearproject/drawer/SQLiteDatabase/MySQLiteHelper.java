package finalyearproject.drawer.SQLiteDatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import finalyearproject.drawer.Main.StockItemRow;
import finalyearproject.drawer.POJO.Quote;
import finalyearproject.drawer.POJO.ResultWrapper;

public class MySQLiteHelper {


    public static final String KEY_P_ID = "PURCHASE_ID";
    public static final String KEY_S_GID = "GROUP_ID";
    public static final String KEY_S_NUM = "NUMBER";
    public static final String KEY_S_COST = "INDIVIDUAL_COST";
    public static final String KEY_S_TCOST = "TOTAL_COST";
    public static final String NAME_INDIVIDUAL = "STOCK_INDIVIDUAL";

    public static final String S_ID = "STOCK_ID";
    public static final String S_TID = "TICKER_ID";
    public static final String S_NAME = "NAME";
    public static final String S_NUM = "NUMBER_BOUGHT";
    public static final String S_INDVAL = "INDIVIDUAL_VALUE";
    public static final String S_TVAL= "TOTAL_VALUE";
    public static final String S_TCOST = "TOTAL_COST";
    public static final String S_DATE = "DATE";
    public static final String NAME_GROUP = "STOCK_GROUP";


    public static final String KEY_SYMBOL = "SYMBOL";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_PRICE = "PRICE";
    public static final String KEY_CHANGE_PERCENT = "CHANGE_PERCENT";
    public static final String NAME_BACKUP = "STOCK_BACKUP";

    private static final String DATABASE_NAME = "stock_portfolio.db";
    private static final int DATABASE_VERSION = 2;



    private DatabaseHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public double getNumberOfStocksIndividualTranche(int position) {
        String[] columns = new String[]{S_NUM};
        Cursor c = ourDatabase.query(NAME_GROUP, columns, null, null, null, null, null);

        double result = 0.0;

        try {
            int i_num = c.getColumnIndex(S_NUM);


            c.moveToPosition(position);
            result = c.getDouble(i_num);
        }catch(CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return result;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL("CREATE TABLE "  + NAME_GROUP + " (" +
                            S_ID + " INTEGER, "+
                            S_TID + " TEXT, "+
                            S_NAME + " TEXT, " +
                            S_NUM + " INTEGER, " +
                            S_INDVAL + " DOUBLE, " +
                            S_TVAL + " DOUBLE, " +
                            S_TCOST + " DOUBLE, " +
                            S_DATE + " TEXT);"
            );

            db.execSQL("CREATE TABLE "  + NAME_INDIVIDUAL + " (" +
                            KEY_P_ID + " INTEGER PRIMARY KEY, "+
                            KEY_S_GID + " INTEGER, "+
                            KEY_S_NUM + " INTEGER, "+
                            KEY_S_COST+ " INTEGER, "+
                            KEY_S_TCOST+ " INTEGER);"
            );

            db.execSQL("CREATE TABLE "  + NAME_BACKUP + " (" +
                            KEY_SYMBOL + " TEXT, "+
                            KEY_NAME + " TEXT, "+
                            KEY_PRICE + " INTEGER, " +
                            KEY_CHANGE_PERCENT+ " TEXT);"
            );



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + NAME_GROUP);
            db.execSQL("DROP TABLE IF EXISTS " + NAME_INDIVIDUAL);
            db.execSQL("DROP TABLE IF EXISTS " + NAME_BACKUP);

            onCreate(db);
        }

    }


    public MySQLiteHelper(Context c){
        ourContext = c;
        ourHelper = new DatabaseHelper(ourContext);
    }



    public MySQLiteHelper open()throws SQLException {
        //ourHelper = new DatabaseHelpeAr(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }



    public void close(){
        ourHelper.close();
    }



    public long createStockItemEntry(int s_id, String s_tid, String s_name, int s_num, double s_indv, double s_tval,double s_tcost, String s_date) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(S_ID, s_id);
        cv.put(S_TID, s_tid);
        cv.put(S_NAME, s_name);
        cv.put(S_NUM, s_num);
        cv.put(S_INDVAL, s_indv);
        cv.put(S_TVAL,s_tval);
        cv.put(S_TCOST,s_tcost);
        cv.put(S_DATE,s_date);

        return ourDatabase.insert(NAME_GROUP, null, cv);

    }


    public ArrayList<StockPurchase> getStockGroupEntry() {
        // TODO Auto-generated method stub

        String[] columns = new String[]{S_ID,S_TID,S_NAME,S_NUM,S_INDVAL,S_TVAL,S_TCOST,S_DATE};
        Cursor c = ourDatabase.query(NAME_GROUP, columns, null, null, null, null, null);

        ArrayList<StockPurchase> stockGroupData = new ArrayList<StockPurchase>();

        int i_id = c.getColumnIndex(S_ID);
        int i_tid = c.getColumnIndex(S_TID);
        int i_name = c.getColumnIndex(S_NAME);
        int i_num = c.getColumnIndex(S_NUM);
        int i_cval = c.getColumnIndex(S_INDVAL);
        int i_tval = c.getColumnIndex(S_TVAL);
        int i_tcost = c.getColumnIndex(S_TCOST);
        int i_date = c.getColumnIndex(S_DATE);


        int i = 0;
        for(c.moveToLast();  !c.isBeforeFirst() ;c.moveToPrevious()){
            // result = result + c.getString(b_symbol) + "//split//" + c.getString(b_name) + "//split//" + c.getString(b_change_percent)+"//split//" + c.getString(b_price) + "//split//";
            stockGroupData.add(new StockPurchase(c.getInt(i_id), c.getString(i_tid), c.getString(i_name),c.getInt(i_num), c.getDouble(i_cval), c.getDouble(i_tval),c.getDouble(i_tcost),c.getString(i_date)));
            i++;
        }
        return stockGroupData;

    }


    public long createStockIndividualEntry(int s_gid, int s_num, double cost, double t_cost) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_S_GID, s_gid);
        cv.put(KEY_S_NUM, s_num);
        cv.put(KEY_S_COST, cost);
        cv.put(KEY_S_TCOST, t_cost);



        return ourDatabase.insert(NAME_INDIVIDUAL, null, cv);

    }


    public String getStockItemIndividualData() {
        // TODO Auto-generated method stub

        String[] columns = new String[]{KEY_P_ID,KEY_S_GID,KEY_S_NUM,KEY_S_COST,KEY_S_TCOST};
        Cursor c = ourDatabase.query(NAME_INDIVIDUAL, columns, null, null, null, null, null);

        String result = "";

        int i_pid = c.getColumnIndex(KEY_P_ID);
        int i_gid = c.getColumnIndex(KEY_S_GID);
        int i_num = c.getColumnIndex(KEY_S_NUM);
        int i_cost = c.getColumnIndex(KEY_S_COST);
        int i_tcost = c.getColumnIndex(KEY_S_TCOST);


        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            result = result + c.getString(i_pid) + "//split//" + c.getString(i_gid) + "//split//" + c.getString(i_num)+"//split//" + c.getString(i_cost) + "//split//" + c.getString(i_tcost)+"//split//";

        }
        return result;

    }


    public double getPortfolioValueFromSQLLiteDB() {

        double portfolioValue = 0.0;
        Cursor cursor = ourDatabase.rawQuery(
                "SELECT SUM(" + S_TVAL + ") FROM " + NAME_GROUP, null);
        if (cursor.moveToFirst()) {
            portfolioValue = cursor.getDouble(0);
        }
        return portfolioValue;
    }

    public double getStockItemPortfolioValueFromSQLLiteDB(String symbol) {

        double portfolioValue = 0.0;
        Cursor cursor = ourDatabase.rawQuery(
                "SELECT SUM(" + S_TVAL + ") FROM " + NAME_GROUP + " WHERE " + S_TID +" LIKE "+ "'" + symbol + "'" , null);
        if (cursor.moveToFirst()) {
            portfolioValue = cursor.getDouble(0);
        }
        return portfolioValue;
    }

    public double getPortfolioCostFromSQLLiteDB() {

        double portfolioCost = 0.0;
        Cursor cursor = ourDatabase.rawQuery(
                "SELECT SUM(" + S_TCOST + ") FROM " + NAME_GROUP, null);
        if (cursor.moveToFirst()) {
            portfolioCost = cursor.getDouble(0);
        }
        return portfolioCost;
    }



    public long createStockBackupEntry(String b_symbol, String b_name, double b_price, String b_change_percent) {
        // TODO Auto-generated method stub
        ourDatabase.delete(NAME_BACKUP, null, null);
        ContentValues cv = new ContentValues();
        cv.put(KEY_SYMBOL, b_symbol);
        cv.put(KEY_NAME, b_name);
        cv.put(KEY_PRICE, b_price);
        cv.put(KEY_CHANGE_PERCENT, b_change_percent);

        return ourDatabase.insert(NAME_BACKUP, null, cv);

    }

    public void truncateTable(String TABLE_NAME) {
        // TODO Auto-generated method stub
        ourDatabase.delete(TABLE_NAME, null, null);
 }

    public ArrayList<StockItemRow> getStockBackup() {
        // TODO Auto-generated method stub


        String[] columns = new String[]{KEY_SYMBOL,KEY_NAME,KEY_CHANGE_PERCENT,KEY_PRICE};
        Cursor c = ourDatabase.query(NAME_BACKUP, columns, null, null, null, null, null);

        ArrayList<StockItemRow> backups = new ArrayList<StockItemRow>();

        int b_symbol = c.getColumnIndex(KEY_SYMBOL);
        int b_name = c.getColumnIndex(KEY_NAME);
        int b_change_percent = c.getColumnIndex(KEY_CHANGE_PERCENT);
        int b_price = c.getColumnIndex(KEY_PRICE);

        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            backups.add(new StockItemRow(c.getString(b_symbol),c.getString(b_name),c.getString(b_change_percent),c.getDouble(b_price)));
        }

        return backups ;

    }


    public void updateStockValues(ResultWrapper result)
    {
        Quote[] quotes = result.getQuery().getResults().getQuote();
        ContentValues args = new ContentValues();
        try {
            for (int i = 0; i < quotes.length; i++) {
                args.put(S_INDVAL, quotes[i].getLastTradePriceOnly());
                args.put(S_TVAL, quotes[i].getLastTradePriceOnly() * getNumberOfStocksIndividualTranche(i));
                ourDatabase.update(NAME_GROUP, args, S_ID + "=" + i, null);
            }
        }catch (NullPointerException e){
              e.printStackTrace();
    }
    }

}








