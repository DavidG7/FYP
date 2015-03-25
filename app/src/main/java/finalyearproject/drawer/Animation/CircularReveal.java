package finalyearproject.drawer.Animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

/**
 * Created by Dvaid on 24/03/2015.
 */
public class CircularReveal {

    View mView;

    public CircularReveal(View view){
        this.mView = view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void hideImageCircular() {


       Animator anim = ViewAnimationUtils.createCircularReveal(mView, mView.getWidth(),
                        mView.getHeight(),   0,
                        mView.getHeight() * 2);

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility( View.INVISIBLE );
            }
        });

        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void revealImageCircular() {

        Animator anim = ViewAnimationUtils.createCircularReveal(mView, mView.getWidth(),
                mView.getHeight(),   0,
                mView.getHeight() * 2);
        /*ValueAnimator anim = ViewAnimationUtils.createCircularReveal(mView, mView.getWidth(),
                        mView.getHeight(),   0,
                        mView.getHeight() * 2);*/


        anim.setDuration( 1000 );
        anim.addListener( new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mView.setVisibility( View.VISIBLE );
            }
        });

        anim.start();
    }
}
