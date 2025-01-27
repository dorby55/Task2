package com.example.task2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Map.Entry<String, Double>> cartItems;

    // Constructor to pass cart items
    public CartAdapter(List<Map.Entry<String, Double>> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Map.Entry<String, Double> item = cartItems.get(position);

        // Set product name
        holder.productName.setText(item.getKey());

        // Set total price for the product
        holder.productPrice.setText("Total: $" + item.getValue());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // ViewHolder class for cart items
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(android.R.id.text1); // First line of text
            productPrice = itemView.findViewById(android.R.id.text2); // Second line of text
        }
    }
}

