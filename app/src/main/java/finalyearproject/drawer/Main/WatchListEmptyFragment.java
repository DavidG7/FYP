package finalyearproject.drawer.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import finalyearproject.drawer.EventBus.BusProvider;
import finalyearproject.drawer.EventBus.ExitEvent;
import finalyearproject.drawer.EventBus.FavouritesEvent;
import finalyearproject.drawer.EventBus.WatchlistToISEQEvent;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 16/02/2015.
 */
public class WatchListEmptyFragment extends Fragment{

    ImageButton mBubble;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View android = inflater.inflate(R.layout.frag_watchlist_empty, container, false);
        //mBubble = (ImageButton) android.findViewById(R.id.ib_bubble);

       /* mBubble.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.drawable.speech_bubble_pressed);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    BusProvider.getInstance().post(new WatchlistToISEQEvent());
                }
                return false;
            }
        });**/
        return android;
    }

}
