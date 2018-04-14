package projetos.joao.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

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

        int price = calculatePrice();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        String orderSummary = createOrderSummary(price,hasWhippedCream);

        displayMessage(orderSummary);
    }

    /**
     *
     * Creates a summary of the order.
     *
     * @return order summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream){
        String orderSummary = "Name: João" + "\nAdd whipped cream? " + hasWhippedCream + "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you!";

        return orderSummary;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price of the order
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view){
        quantity--;
        displayQuantity(quantity);
    }
}