package com.example.quanlycuahangtrasua.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlycuahangtrasua.Interface.ItemClickListener;
import com.example.quanlycuahangtrasua.R;

public class ProductForCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView productNameCart, productIngreCart, productPriceCart;
    public ImageView productImageCart;
    public ItemClickListener listener;

    public ProductForCartViewHolder(@NonNull View itemView) {
        super(itemView);

        productImageCart = (ImageView) itemView.findViewById(R.id.productImageCart);
        productNameCart = (TextView) itemView.findViewById(R.id.productNameCart);
        productIngreCart = (TextView) itemView.findViewById(R.id.productIngreCart);
        productPriceCart = (TextView) itemView.findViewById(R.id.productPriceCart);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(), false);
    }
}
