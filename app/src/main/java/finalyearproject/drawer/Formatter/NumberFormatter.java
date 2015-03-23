package finalyearproject.drawer.Formatter;

/**
 * Created by Dvaid on 12/03/2015.
 */
public class NumberFormatter {

    public String percentChange(double totalCost, double totalValue){
        String percentChangeAsString = "0.0%";
        try {
            double temp = totalValue - totalCost;
            double percent = temp / totalCost;
            percentChangeAsString = percent+"%";
        }catch(ArithmeticException e){
            e.printStackTrace();
        }
        return percentChangeAsString;
    }


    public double getPercentOfOneNumFromAnother(double smaller, double larger){
        double result = 0.0;
        if(smaller != 0.0 || larger != 0.0){

            result = (smaller / larger) * 100;
        }
        return result;
    }

    public double getFoolsRatio(double priceToEarnings, double estimateNext,double estimateCurrent){

        double growthRate = getGrowthRate(estimateNext,estimateCurrent);
        double foolsRatio = (priceToEarnings/growthRate);
        return foolsRatio;
    }

    public double getGrowthRate(double estimateNext,double estimateCurrent){
        double growthRate = (((estimateNext-estimateCurrent)/estimateCurrent)*100);
        return growthRate;
    }
}
