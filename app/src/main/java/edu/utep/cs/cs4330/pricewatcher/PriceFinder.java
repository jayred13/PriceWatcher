package edu.utep.cs.cs4330.pricewatcher;

import java.util.Random;


/** @author Jayred Loyda
 *  @course CS 4330 - Mobile Application Development, Spring 2019
 * */

public class PriceFinder {

    public double simulatePrice( String url ) {
        Random r = new Random();

        double min = 100.0, max = 200.0;
        double randVal = min + (max-min) * r.nextDouble();

        randVal = (double) Math.round(randVal*100)/100;
        return randVal;
    }
}