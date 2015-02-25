package finalyearproject.drawer.PortfolioTransactionHistory;

/**
 * Created by Dvaid on 17/02/2015.
 */

import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import finalyearproject.drawer.Calculator.CalculatorFragment;
import finalyearproject.drawer.PortfolioTransactionHistory.PortfolioTransactionCircleFragment;
import finalyearproject.drawer.R;


public class PortfolioTransactionActivity extends FragmentActivity{
    /**
     * Called when the activity is first created.
     */

    FrameLayout mTransContainer;
    ImageButton mArrow;
    Fragment[] mFragments = new Fragment[2];

    //private StockPurchase[] mLastTenRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        mTransContainer = (FrameLayout) findViewById(R.id.fl_trans);
        mArrow = (ImageButton) findViewById(R.id.ib_arrow);

        mFragments[0] = new PortfolioTransactionListFragment();
        mFragments[1] = new PortfolioTransactionCircleFragment();

        getSupportFragmentManager().beginTransaction()

                .add(R.id.fl_trans, new PortfolioTransactionListFragment())
                .commit();

        mArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    mArrow.setBackground( getResources().getDrawable(R.drawable.mycircle_white));
                    mArrow.setImageResource(R.drawable.arrow_forward_orange);
                }else if(event.getAction() == android.view.MotionEvent.ACTION_UP){
                    mArrow.setBackground( getResources().getDrawable(R.drawable.mycircle));
                    mArrow.setImageResource(R.drawable.arrow_forward);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left,
                                    R.anim.slide_out_left)
                            .replace(R.id.fl_trans, mFragments[1])
                            .commit();
                    int rotationFrom,rotationTo = 0;

                    RotateAnimation ra =new RotateAnimation(0,180,mArrow.getWidth()/2,mArrow.getHeight()/2);
                   // ra.initialize(mArrow.getWidth(),mArrow.getHeight(),mArrow.getWidth(),mArrow.getHeight());
                    ra.setFillAfter(true);
                    ra.setDuration(1000);

                    mArrow.startAnimation(ra);
                }

                return false;
            }
        });

    }




    /*public StockPurchase[] getLastTenRecords() {
        return mLastTenRecords;
    }*/




}