package com.sachin_kumar.droidcafe;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity=2;
    public void submitOrder(View view){
        EditText name=(EditText)findViewById(R.id.edit_text);
        CheckBox check_cream=(CheckBox)findViewById(R.id.checkbox_cream);
        CheckBox check_chocolate=(CheckBox)findViewById(R.id.checkbox_chocolate);
        int price=quantity*5;
        price+=CalculatePrice(check_cream.isChecked(), check_chocolate.isChecked());
        String pricemessage = "Name: " + name.getText() + "\nWhipped Cream: "
                + check_cream.isChecked() + "\nChocolate: " + check_chocolate.isChecked() +
                "\nQuantity: " + quantity + "\nTotal : $" + price + "\nThank You!";
        createOrderSummary(pricemessage);
        email(price);

        /*** This is for Whatsapp Intent ***/
//        Intent whatsapp_intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("content://com.android.contacts/data/"));
//        whatsapp_intent.setType("text/plain");
//        whatsapp_intent.setPackage("com.whatsapp");
//        whatsapp_intent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for " + name.getText());
//        whatsapp_intent.putExtra(Intent.EXTRA_TEXT, "Whipped Cream: "
//                + check_cream.isChecked() + "\nChocolate: " + check_chocolate.isChecked() +
//                "\n\nQuantity: " + quantity + "\nTotal : $" + price + "\n\nThank You for Your Order!");
//        if (whatsapp_intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(whatsapp_intent);
//        }
    }
    /*** This is for Email Intent ***/
    void email(int price) {
        EditText name=(EditText)findViewById(R.id.edit_text);
        CheckBox check_cream=(CheckBox)findViewById(R.id.checkbox_cream);
        CheckBox check_chocolate=(CheckBox)findViewById(R.id.checkbox_chocolate);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","sachin.roy12345@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for " + name.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Whipped Cream: "
                + check_cream.isChecked() + "\nChocolate: " + check_chocolate.isChecked() +
                "\n\nQuantity: " + quantity + "\nTotal : $" + price + "\n\nThank You for Your Order!");
        if(emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    public int CalculatePrice(boolean cream, boolean chocolate){
        int price=0;
        if(cream) {
            price+=quantity*1;
        }
        if(chocolate){
            price+=quantity*2;
        }
        return price;
    }
    public void increment(View view){
        if(quantity==100){
            Toast.makeText(this, "You cannot have coffee more than 100 cup", Toast.LENGTH_LONG).show();
            return;
        }
        quantity++;
        display(quantity);
    }
    public void decrement(View view){
        if(quantity==1) {
            Toast.makeText(this, "You cannot have coffee less than 1 cup", Toast.LENGTH_LONG).show();
            return;
        }
        quantity--;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void createOrderSummary(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
