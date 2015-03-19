package finalyearproject.drawer.POJO;

/**
 * Created by Dvaid on 24/11/2014.
 */
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Quote {

    private String symbol;//TICKER ID /*######*/
    private String Ask;//THE CURRENT PRICE
    private String AverageDailyVolume;
    private String Bid;
    private String AskRealtime;
    private String BidRealtime;
    private String BookValue;
    @SerializedName("Change_PercentChange")
    private String ChangePercentChange;
    private String Change;
    private Object Commission;
    private String Currency;
    private String ChangeRealtime;
    private String AfterHoursChangeRealtime;
    private String DividendShare; /*######*/
    private String LastTradeDate;
    private Object TradeDate; /*######*/
    private String EarningsShare;
    private Object ErrorIndicationreturnedforsymbolchangedinvalid;
    private String EPSEstimateCurrentYear; /*######*/
    private String EPSEstimateNextYear;
    private String EPSEstimateNextQuarter;
    private String DaysLow; /*######*/
    private String DaysHigh; /*######*/
    private String YearLow; /*######*/
    private String YearHigh; /*######*/
    private String HoldingsGainPercent;
    private Object AnnualizedGain;
    private Object HoldingsGain;
    private String HoldingsGainPercentRealtime;
    private Object HoldingsGainRealtime;
    private String MoreInfo;
    private Object OrderBookRealtime;
    private Object MarketCapitalization;
    private Object MarketCapRealtime;
    private String EBITDA;
    private String ChangeFromYearLow;
    private String PercentChangeFromYearLow;
    private String LastTradeRealtimeWithTime;
    private String ChangePercentRealtime;
    private String ChangeFromYearHigh;
    private String PercebtChangeFromYearHigh;
    private String LastTradeWithTime;
    private String LastTradePriceOnly;
    private Object HighLimit;
    private Object LowLimit;
    private String DaysRange; /*######*/
    private String DaysRangeRealtime;
    private String FiftydayMovingAverage;
    private String TwoHundreddayMovingAverage;
    private Object ChangeFromTwoHundreddayMovingAverage;
    private Object PercentChangeFromTwoHundreddayMovingAverage;
    private Object ChangeFromFiftydayMovingAverage;
    private Object PercentChangeFromFiftydayMovingAverage;
    private String Name;//NAME OF THE STOCK
    private Object Notes;
    private String Open;
    private String PreviousClose;//*THE CURRENT PRICE
    private Object PricePaid;
    private String ChangeinPercent;//AN INDICATION WHETHER THE STOCK HAS RISEN OR FALLEN/*THE CURRENT PRICE
    private Object PriceSales;
    private Object PriceBook;
    private Object ExDividendDate;
    private Object PERatio;
    private Object DividendPayDate;
    private Object PERatioRealtime;
    private Object PEGRatio;
    private Object PriceEPSEstimateCurrentYear;
    private Object PriceEPSEstimateNextYear;
    private String Symbol;
    private Object SharesOwned;
    private Object ShortRatio;
    private String LastTradeTime;
    private String TickerTrend;
    private Object OneyrTargetPrice;
    private String Volume;
    private Object HoldingsValue;
    private Object HoldingsValueRealtime;
    private String YearRange;
    private String DaysValueChange;
    private String DaysValueChangeRealtime;
    private String StockExchange;
    private Object DividendYield;
    private String PercentChange;


    public String getName(){
        return Name;
    }

    public String getsymbol(){
        return symbol;
    }

    public String getDaysLow(){
        return DaysLow;
    }

    public String getDaysHigh(){
        return DaysHigh;
    }

    public String getYearsLow(){
        return YearLow;
    }

    public String getYearsHigh(){
        return YearHigh;
    }

    public String getVolume(){
        return Volume;
    }


    public String getLastTradeTime(){
        return LastTradeTime;
    }


    /*public String getChangeinPercent(){
        return ChangeinPercent;
    }*/

    public double getLastTradePriceOnly(){
        return Double.parseDouble(LastTradePriceOnly);
    }

    public String getChangeInPercent(){
        /*try {
            Double current = getLastTradePriceOnly();
            Double previous = Double.parseDouble(PreviousClose);


            Double changeUnFormatted = (current - previous) / previous;

             return ((double)Math.round(changeUnFormatted * 10000) / 10000);
            //temp = temp.movePointRight(2);
            //return temp.doubleValue();

        }catch(NullPointerException e){
            Double d = 0.00;
            return d;

        }*/
        try {
            String temp = ChangeinPercent.replaceAll("[^\\d.]", "");
            if(ChangeinPercent.contains("-")){
                return "-1" + temp +"%";
            }
            temp = "+" + temp +"%";
            return temp;
        }catch(NullPointerException e){
            return "0.00";
        }
    }

    /*public double getNullAsk(){
        String valueFromPercent = ChangeinPercent.replaceAll("[^.0-9]","");
        System.out.print("GAGAGAGAGAGGAGAGAG "+valueFromPercent+"   "+ PreviousClose);
        try {
            double result = Double.parseDouble(PreviousClose) / 100 * (100 + Double.parseDouble(valueFromPercent));
            return Double.parseDouble(String.format("%.4f", result));
        }catch(Exception e){
            return 0.0;
        }
    }*/

}