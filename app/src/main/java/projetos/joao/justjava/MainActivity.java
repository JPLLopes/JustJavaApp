package projetos.joao.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     * This method takes all the user choices and creates an Intent to an e-mail application with the order
     * summary so an e-mail can be sent to place the order.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        EditText nameEditText = findViewById(R.id.name_edit_text_view);

        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String name = nameEditText.getText().toString();
        String orderSummary = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    /**
     * Creates a summary of the order.
     *
     * @param name            name of the user
     * @param price           total price of the order
     * @param hasWhippedCream did the user add whipped cream or not
     * @param hasChocolate    did the user add chocolate or not
     * @return order summary
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String orderSummary = getString(R.string.order_summary_name, name);
        orderSummary += "\n" + getString(R.string.order_summary_whipped_cream, hasWhippedCream);
        orderSummary += "\n" + getString(R.string.order_summary_chocolate, hasChocolate);
        orderSummary += "\n" + getString(R.string.order_summary_quantity, quantity);
        orderSummary += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        orderSummary += "\n" + getString(R.string.thank_you);

        return orderSummary;
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream did the user want whipped cream?
     * @param hasChocolate    did the user want chocolate?
     * @return total price of the order
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int whippedCreamPrice = 1;
        int chocolatePrice = 2;
        int basePriceOfCoffee = 5;

        if (hasWhippedCream) {
            basePriceOfCoffee += whippedCreamPrice;
        }

        if (hasChocolate) {
            basePriceOfCoffee += chocolatePrice;
        }

        return basePriceOfCoffee * quantity;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "You can't order more than 100 coffees!", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, "You have to order at least one coffee!", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity--;
        displayQuantity(quantity);
    }
}