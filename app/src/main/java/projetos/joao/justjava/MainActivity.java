package projetos.joao.justjava;

import android.content.Context;
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

        displayMessage(orderSummary);
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
        String orderSummary = "Name: " + name;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank you!";

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
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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