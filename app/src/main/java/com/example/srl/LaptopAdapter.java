package com.example.srl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.srl.entidades.Laptop;

import org.w3c.dom.Text;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {

    private Laptop[] laptops;

    public LaptopAdapter() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;

        public ViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView(){
            return textView;
        }
    }

    public LaptopAdapter(Laptop[] laptops){
        this.laptops = laptops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(laptops[0].getModelo());
    }

    @Override
    public int getItemCount() {
        return laptops.length;
    }


}
