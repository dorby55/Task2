package com.example.task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private HashMap<String, Double> cart = new HashMap<>();
    private double totalCost = 0.0;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productName.setText(MyData.nameArray[position]);
        holder.productPrice.setText("$" + MyData.priceArray[position]);
        holder.quantityText.setText(String.valueOf(MyData.quantityArray[position]));
        holder.productImage.setImageResource(MyData.imageArray[position]);

        holder.incrementButton.setOnClickListener(v -> {
            MyData.quantityArray[position]++;
            holder.quantityText.setText(String.valueOf(MyData.quantityArray[position]));
        });

        holder.decrementButton.setOnClickListener(v -> {
            if (MyData.quantityArray[position] > 0) {
                MyData.quantityArray[position]--;
                holder.quantityText.setText(String.valueOf(MyData.quantityArray[position]));
            }
        });

        holder.addToCartButton.setOnClickListener(v -> {
            int quantity = MyData.quantityArray[position];
            if (quantity > 0) {
                double price = MyData.priceArray[position];
                cart.put(MyData.nameArray[position], quantity * price);
                calculateTotalCost();

                Toast.makeText(holder.itemView.getContext(),
                        MyData.nameArray[position] + " added to cart.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(holder.itemView.getContext(),
                        "Quantity must be greater than 0.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return MyData.nameArray.length;
    }

    private void calculateTotalCost() {
        totalCost = 0.0;
        for (double price : cart.values()) {
            totalCost += price;
        }
    }

    public double getTotalCost() {
        return totalCost;
    }

    public HashMap<String, Double> getCart() {
        return cart;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, quantityText;
        Button incrementButton, decrementButton, addToCartButton;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            quantityText = itemView.findViewById(R.id.quantity_text);
            incrementButton = itemView.findViewById(R.id.increment_button);
            decrementButton = itemView.findViewById(R.id.decrement_button);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}


