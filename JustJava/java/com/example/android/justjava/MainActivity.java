package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringReader;
import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.R.attr.onClick;
import static android.support.v7.appcompat.R.id.text;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int price = 0;
    private int pricePerCup = 0;
    private String message = "";
    boolean isWhipped;
    boolean hasChocolate;
    private String nameCustomer = "";
    private String subject = "";
    private String [] addresses = {"eabbae@yahoo.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"Cannot order more than 100 coffees.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity ==  1) {
            return;
        }else {
            quantity = quantity - 1;
            Toast.makeText(this,"Please order at least 1 cup of coffee!", Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.name_text);
        nameCustomer = nameText.getText().toString();
        CheckBox wc = (CheckBox)findViewById(R.id.whipped_box);
        isWhipped = wc.isChecked();
        CheckBox choc = (CheckBox) findViewById(R.id.chocolate_box);
        hasChocolate = choc.isChecked();
        price = calculatePrice(isWhipped, hasChocolate);
        createOrderSummary(nameCustomer, isWhipped, hasChocolate, quantity, price);
        subject = getString(R.string.order_email_subject);
        composeEmail(addresses, subject);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + " " + nameCustomer);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
    * This method displays the given quantity value on the screen.
    */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(boolean isWhipped, boolean hasChocolate) {
        pricePerCup = 5;
        if(isWhipped) {
            pricePerCup += 1;
        }
        if (hasChocolate) {
            pricePerCup += 2;
        }
        return quantity * pricePerCup;
    }

    private String createOrderSummary(String nameCustomer, boolean isWhipped, boolean hasChocolate, int quantity, int price) {
        message = getString(R.string.name_text) + ": " + nameCustomer + "\n" +
                getString(R.string.add_whipped) + " " + isWhipped  + "\n" +
                getString(R.string.add_chocolate) + " " + hasChocolate + "\n" +
                getString(R.string.quantity) + ": " + quantity + "\n" +
                getString(R.string.total) + price + "\n" +
                NumberFormat.getCurrencyInstance().format(price);
                getString(R.string.thank_you);
        return message;
    }

}