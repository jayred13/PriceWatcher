package edu.utep.cs.cs4330.pricewatcher;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/** @author Jayred Loyda
 *  @course CS 4330 - Mobile Application Development, Spring 2019
 * */

public class Item {
    private static double startPrice;
    private static int count;

    private String name;
    private String url;

    public Item() {
        name = null;
        url = null;
    }

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
        setInitialPrice();
    }

    /** @return the name of the item */
    public String getName() {
        return name;
    }

    /** @return the URL of the item */
    public String getUrl() {
        return url;
    }

    /** Sets initial price of item */
    public void setInitialPrice() {
        if(count == 0 ) {
            PriceFinder pf = new PriceFinder();
            startPrice = pf.simulatePrice( getUrl() );
        }
    }

    /** @return initial price of item*/
    public double getInitialPrice() {
        return startPrice;
    }

    /** Implements PriceFinder class
     * @return current price of item
     */
    public double getCurrentPrice() {
        if(count==0) {
            count++;
            return startPrice;
        }
        else {
            PriceFinder pf = new PriceFinder();
            return pf.simulatePrice( getUrl() );
        }
    }

    /** @return percent change of current price and initial price
     *  @param currPrice
     */
    public double priceChange(double currPrice) {
        double percentChange = ((currPrice-startPrice)/startPrice)*100;
        percentChange = (double) Math.round(percentChange*100)/100;
        return percentChange;
    }

    /** @return date accessed */
    public String date() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String currDate = dateFormat.format(date);
        return currDate;
    }

    /** @return list of the item's info */
    public Object[] info() {
        setInitialPrice();
        double currPrice = getCurrentPrice();

        Object[] itemInfo = {"Name: \t",getName(),
                "URL: \t", getUrl(),
                "Initial Price: \t", getInitialPrice(),
                "Current Price: \t", currPrice,
                "Price Change: \t", priceChange( currPrice ),
                "Date Added: \t", date()};
        return itemInfo;
    }
}

