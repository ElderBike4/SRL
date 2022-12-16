package com.example.srl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.srl.entidades.Laptop;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdapterLaptops extends RecyclerView.Adapter<AdapterLaptops.ViewHolderLaptops> {
    ArrayList<String> laptopArrayList;
    ArrayList<String> arrayLaptops;
    AdapterLaptops.OnItemClickListener listener ;



    public interface OnItemClickListener{
        void onItemClickListener(String item);
    }

    public AdapterLaptops(ArrayList<String> laptopArrayList, AdapterLaptops.OnItemClickListener listener) {
        this.laptopArrayList = laptopArrayList;
        arrayLaptops = new ArrayList<>();
        arrayLaptops.addAll(laptopArrayList);
        this.listener = listener;
    }

    /*public AdapterLaptops(ArrayList<String> laptopArrayList) {
        this.laptopArrayList = laptopArrayList;
        arrayLaptops = new ArrayList<>();
        arrayLaptops.addAll(laptopArrayList);
    }*/

    public void filtrado(String buscar){
        int longitud = buscar.length();
        if(longitud==0){
            laptopArrayList.clear();
            laptopArrayList.addAll(arrayLaptops);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<String> collection = laptopArrayList.stream().filter(i -> i.toLowerCase().contains(buscar.toLowerCase())).collect(Collectors.toList());
                laptopArrayList.clear();
                laptopArrayList.addAll(collection);
            }else{
                for(String modelo: arrayLaptops){
                    if(modelo.toLowerCase().contains(buscar.toLowerCase())){
                        laptopArrayList.add(modelo);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderLaptops onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist,null,false);
        return new ViewHolderLaptops(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLaptops holder, int position) {
        holder.asignarDatos(laptopArrayList.get(position));
        
    }

    @Override
    public int getItemCount() {
        return laptopArrayList.size();
    }



    public class ViewHolderLaptops extends RecyclerView.ViewHolder {
        TextView dato;
        public ViewHolderLaptops(@NonNull View itemView) {
            super(itemView);
            dato=(TextView) itemView.findViewById(R.id.idDato);
        }

        public void asignarDatos(String laptop) {
            dato.setText(laptop);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClickListener(laptop);
                }
            });
        }
    }


}
