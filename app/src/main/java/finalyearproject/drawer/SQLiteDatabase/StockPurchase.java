package finalyearproject.drawer.SQLiteDatabase;

/**
 * Created by Dvaid on 19/02/2015.
 */
public class StockPurchase {

    int id;
    String tickerId;
    String name;
    int num;
    double curValue;
    double totalValue;
    double totalCost;

    public StockPurchase(int id,String tickerId,String name,int num,double curValue,double totalValue,double totalCost){
        this.id = id;
        this.tickerId = tickerId;
        this.name = name;
        this.num = num;
        this.curValue = curValue;
        this.totalValue = totalValue;
        this.totalCost = totalCost;
    }

    public int getId(){
        return id;
    }

    public String getTickerId(){
        return tickerId;
    }

    public String getName(){
        return name;
    }

    public int getNum(){
        return num;
    }
    public double getCurValue(){
        return curValue;
    }

    public double getTotalValue(){
        return totalValue;
    }

    public double getTotalCost(){
        return totalCost;
    }


}
