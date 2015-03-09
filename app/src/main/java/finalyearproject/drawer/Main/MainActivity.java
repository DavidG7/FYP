package finalyearproject.drawer.Main;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import finalyearproject.drawer.Calculator.CalculatorFragment;
import finalyearproject.drawer.ChartFragment.MasterChartFragment;
import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.ExitEvent;
import finalyearproject.drawer.EventBus.FavouritesEvent;
import finalyearproject.drawer.EventBus.WatchlistToISEQEvent;
import finalyearproject.drawer.Exit.ExitFragment;
import finalyearproject.drawer.ISEQ.ISEQFragment;
import finalyearproject.drawer.Observer.Observer;
import finalyearproject.drawer.POJO.ResultWrapper;
import finalyearproject.drawer.Portfolio.PortfolioFragment;
import finalyearproject.drawer.R;
import finalyearproject.drawer.SQLiteDatabase.MySQLiteHelper;
import finalyearproject.drawer.SharedPreferences.SharedPref;
import finalyearproject.drawer.Subject.Subject;


public class MainActivity extends ActionBarActivity implements Observer {
    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawerLinLay;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private ViewFlipper flipper;
    public ResultWrapper result;
    private double mPortfolioValue,mPortfolioCost;
    private MySQLiteHelper stock_group;
    private SharedPref pref;
    private ArrayList<Integer> favourites;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stock_group = new MySQLiteHelper(this);
        stock_group.open();
        setPortfolioValue(stock_group.getPortfolioValueFromSQLLiteDB());
        setPortfolioCost(stock_group.getPortfolioCostFromSQLLiteDB());
        stock_group.close();

        String callResult = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            callResult = bundle.getString("ResultWrapper");
        }
        result = new Gson().fromJson(callResult, ResultWrapper.class);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        final ViewFlipper v = (ViewFlipper) findViewById(R.id.switcher);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLinLay = (LinearLayout) findViewById(R.id.drawer_lin_lay);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);


        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));//, true, "22"));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));//, true, "22"));



        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());



        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, //nav menu toggle icon
                R.string.drawer_open, // nav drawer open - description for accessibility
                R.string.drawer_close // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                // getSupportActionBar().setTitle("iStocks");
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                // getSupportActionBar().setTitle("iStocks");
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };



        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }

        flipper = (ViewFlipper) findViewById(R.id.switcher);
        flipper.setAutoStart(true);
        flipper.setFlipInterval(10000);

        BusProvider.getInstance().register(this);

        pref = new SharedPref(this);
        favourites = pref.loadSavedPreferences();
    }

    @Override
    public void update(Subject subject) {
        stock_group.open();
        setPortfolioValue(stock_group.getPortfolioValueFromSQLLiteDB());
        setPortfolioCost(stock_group.getPortfolioCostFromSQLLiteDB());
        stock_group.close();
    }

    public double getPortfolioValue(){
        return mPortfolioValue;
    }

    private void setPortfolioValue(double portfolioValue){
        mPortfolioValue = portfolioValue;
    }

    public double getPortfolioCost(){
        return mPortfolioCost;
    }

    private void setPortfolioCost(double portfolioCost){
        mPortfolioCost = portfolioCost;
    }


    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerLinLay);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ISEQFragment(this);
                break;
            case 1:
                //fragment = new FindPeopleFragment();
                fragment = new PortfolioFragment();
                break;
            case 2:
                // fragment = new PhotosFragment();
                if(favourites.size()==0){
                    fragment = new WatchListEmptyFragment();
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("Watchlist", true);
                    fragment = new ISEQFragment(this);
                    fragment.setArguments(bundle);
                }
                break;
            case 3:
                //fragment = new PhotosFragment();
                 fragment = new MasterChartFragment();

                break;
            case 4:
                // fragment = new CommunityFragment();
                fragment = new CalculatorFragment();
                break;

            case 5:
                // fragment = new CommunityFragment();
               /* moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/
                fragment = new ExitFragment();
                break;


            default:
                break;
        }

        if (fragment != null) {

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerLinLay);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        //mTitle = title;
        //getSupportActionBar().setTitle("iStocks");
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public ResultWrapper getRESTResult(){
        return result;
    }

    public void setRESTResult(ResultWrapper result){
        this.result = result;
    }


    @Subscribe
    public void navDrawerCallback(ExitEvent event) {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    @Subscribe
    public void favouritesChangedCallback(FavouritesEvent event) {
        this.favourites = event.getFavourites();
    }

    @Subscribe
    public void watchlistToISEQCallback(WatchlistToISEQEvent event) {
        displayView(0);
    }


    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            System.exit(-1);
        }else{
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

}