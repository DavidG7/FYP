package finalyearproject.drawer.SharedPreferences;

import android.content.Context;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


/**
 * Created by Dvaid on 15/01/2015.
 */
public class SharedPref {
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";
    private static String KEY = "Favourites";
   // private int numOfFavourites;
    private Context mContext;

    public SharedPref(Context context) {
        this.mContext = context;
    }

    public ArrayList<Integer> loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String savedString = sharedPreferences.getString(KEY, "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        int count = st.countTokens();
        ArrayList<Integer> favourites = new ArrayList<Integer>();
        for (int i = 0; i <count; i++) {
            favourites.add(Integer.parseInt(st.nextToken()));
        }
        return favourites;
     }
   

           
    public void savePreferences(ArrayList<Integer> favourites) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        Editor editor = sharedPreferences.edit();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < favourites.size(); i++) {
            str.append(favourites.get(i)).append(",");
        }
        editor.putString(KEY, str.toString());
        editor.commit();
       // this.numOfFavourites = favourites.length;

    }

}