package finalyearproject.drawer.Calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import finalyearproject.drawer.Constants.Constants;
import finalyearproject.drawer.R;

/**
 * Created by Dvaid on 25/03/2015.
 */
public class FoolsDialogView extends LinearLayout {

    TextView mFoolsScore, mFoolsExplanation , mHeading;
    View view;
    TextView mReveal;

    public FoolsDialogView(Context context, int color, TextView reveal) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            view = inflater.inflate(R.layout.fools_dialog, null);
        }
        this.mReveal = reveal;
        mFoolsScore = (TextView) view.findViewById(R.id.tv_fools_dialog_score);
        mFoolsExplanation = (TextView) view.findViewById(R.id.tv_fools_dialog_explanation);
        mHeading = (TextView) view.findViewById(R.id.tv_fools_dialog_heading);



        mHeading.setTextColor(color);
        mFoolsScore.setText(mReveal.getText());
        mFoolsScore.setTextColor(color);
        mFoolsExplanation.setText(Constants.myFoolsMapping.get(mReveal.getText()));
        mFoolsExplanation.setTextColor(color);

        addView(view);

    }
}
