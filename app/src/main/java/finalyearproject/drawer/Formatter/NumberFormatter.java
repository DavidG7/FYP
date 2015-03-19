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
}
