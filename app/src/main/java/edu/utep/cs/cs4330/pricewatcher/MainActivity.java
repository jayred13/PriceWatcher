package edu.utep.cs.cs4330.pricewatcher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URL;

/** @author Jayred Loyda
 *  @course CS 4330 - Mobile Application Development, Spring 2019
 * */
public class MainActivity extends AppCompatActivity {
    private Item item;
    private String itemName = "LCD Monitor";
    private String itemURL = "https://www.bestbuy.com/site/lg-24-class-led-720p-hdtv/5734900.p?skuId=5734900&ref=212&loc=1&extStoreId=829&ds_rl=1260573&ds_rl=1266837&ds_rl=1268709&ref=212&loc=1&ds_rl=1266837&gclid=CjwKCAiAwJTjBRBhEiwA56V7q3PAaTf-YlO-ae3bM8pK63b-HlofUh1T6OUUujE9cyU_YNFjyhyiYhoChj4QAvD_BwE&gclsrc=aw.ds";

    TextView urlView;
    TextView initialPrice;
    TextView currentPrice;
    TextView percentChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlView = findViewById(R.id.itemUrl);
        initialPrice = findViewById(R.id.initialPriceDisplay);
        currentPrice = findViewById(R.id.currentPriceDisplay);
        percentChange = findViewById(R.id.percentChangeDisplay);

        item = new Item(itemName,itemURL);
        String urlText = itemURL.substring(0,itemURL.indexOf(".com")+4);
        urlView.setText(urlText);
        initialPrice.setText("$"+item.getInitialPrice());
        currentPrice.setText("$"+item.getInitialPrice());
        percentChange.setText("0.00%");


        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            handleSendText(intent); // Handle text being sent
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("initialPrice",(String)initialPrice.getText());
        outState.putString("currentPrice",(String)currentPrice.getText());
        outState.putString("percentChange",(String)percentChange.getText());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        initialPrice.setText( savedInstanceState.getString("initialPrice"));
        currentPrice.setText( savedInstanceState.getString("currentPrice"));
        percentChange.setText( savedInstanceState.getString("percentChange"));
    }

    public void checkPriceClick(View view){
        double currPrice = item.getCurrentPrice();
        currentPrice.setText("$"+currPrice);
        percentChange.setText(item.priceChange(currPrice)+"%");
    }

    public void viewPageClick(View view){
        Intent viewPage = new Intent(Intent.ACTION_VIEW);
        viewPage.setData(Uri.parse(itemURL));
        startActivity(viewPage);
    }

    private void handleSendText(Intent intent){
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            itemURL = sharedText;
            String urlText = itemURL.substring(0,itemURL.indexOf(".com")+4);
            urlView.setText(urlText);
        }
    }
}
