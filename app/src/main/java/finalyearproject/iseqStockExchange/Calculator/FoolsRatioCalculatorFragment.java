package finalyearproject.iseqStockExchange.Calculator;

/**
 * Created by Dvaid on 09/01/2015.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import finalyearproject.iseqStockExchange.Animation.CircularReveal;
import finalyearproject.iseqStockExchange.Formatter.NumberFormatter;
import finalyearproject.iseqStockExchange.R;




public class FoolsRatioCalculatorFragment extends Fragment {


    private EditText mPERatioEdit,mEstimateNextEdit,mEstimateCurrentEdit;
    private TextView mCalculate, mResult,mRevealRatio;
    private NumberFormatter mFormatter;
    private CircularReveal mCircularReveal;
    private double mFoolsRatio;
    private int mCurrentColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View foolsRatioView = inflater.inflate(R.layout.frag_fools_ratio, container, false);
        mPERatioEdit =  (EditText) foolsRatioView.findViewById(R.id.et_fools_pe);
        mEstimateNextEdit = (EditText) foolsRatioView.findViewById(R.id.et_fools_estimate_next);
        mEstimateCurrentEdit =(EditText) foolsRatioView.findViewById(R.id.et_fools_estimate_current);
        mCalculate = (TextView) foolsRatioView.findViewById(R.id.tv_fools_calculate);
        mResult =  (TextView) foolsRatioView.findViewById(R.id.tv_fools_result);
        mRevealRatio = (TextView) foolsRatioView.findViewById(R.id.tv_reveal);
        mFormatter = new NumberFormatter();
        mCircularReveal = new CircularReveal(mRevealRatio,getActivity());

        mCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double priceToEarnings = Integer.parseInt(mPERatioEdit.getText().toString());
                    double estimateNext = Integer.parseInt(mEstimateNextEdit.getText().toString());
                    double estimateCurrent = Integer.parseInt(mEstimateCurrentEdit.getText().toString());

                    mFoolsRatio = mFormatter.getFoolsRatio(priceToEarnings,estimateNext,estimateCurrent);
                    DecimalFormat roundDecimal = new DecimalFormat("##.00");
                    mResult.setText(roundDecimal.format(mFoolsRatio));
                    mCircularReveal.revealImageCircular(getResultAsFoolsIndicator(mFoolsRatio));


                }catch(Exception e){
                    e.printStackTrace();
                    mResult.setText("0.0");
                }
            }
        });

        mRevealRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(new FoolsDialogView(getActivity(),mCurrentColor,mRevealRatio,Double.parseDouble(mResult.getText().toString())))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();

                            }
                        });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                final Button buttonPositiveInvolvement = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositiveInvolvement.setTextColor(mCurrentColor);

            }
        });

        return foolsRatioView;
    }

    public int getResultAsFoolsIndicator(double result){

        if(result >= 0.0) {

            if (result <= 0.5) {
                mRevealRatio.setText("1");
                this.mCurrentColor = getResources().getColor(R.color.change_pos);
                return R.drawable.mycircle_green;
            } else if (result <= 0.65) {
                this.mCurrentColor = getResources().getColor(R.color.change_pos);
                mRevealRatio.setText("2");
                return R.drawable.mycircle_green_transparent;
            } else if (result <= 1.00) {
                this.mCurrentColor = getResources().getColor(R.color.app_orange);
                mRevealRatio.setText("3");
                return R.drawable.mycircle;
            } else if (result <= 1.30) {
                this.mCurrentColor = getResources().getColor(R.color.change_neg);
                mRevealRatio.setText("4");
                return R.drawable.mycircle_red_transparent_two;
            } else if (result <= 1.70) {
                this.mCurrentColor = getResources().getColor(R.color.change_neg);
                mRevealRatio.setText("5");
                return R.drawable.mycircle_red_transparent;
            } else {
                this.mCurrentColor = getResources().getColor(R.color.change_neg);
                mRevealRatio.setText("6");
                return R.drawable.mycircle_red;
            }
        }else{
            this.mCurrentColor = getResources().getColor(R.color.change_neg);
            mRevealRatio.setText("6");
            return R.drawable.mycircle_red;
        }


    }


}

