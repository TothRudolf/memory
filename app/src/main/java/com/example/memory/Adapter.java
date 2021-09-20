package com.example.memory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    Activity activity;
    List<Model> noteslist;

    public Adapter(Context context, Activity activity, List<Model> noteslist) {
        this.context = context;
        this.activity = activity;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.categorytitle.setText(noteslist.get(position).getCategorytitle());
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categorytitle;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            categorytitle=itemView.findViewById(R.id.categorytitle);
            layout=itemView.findViewById(R.id.categorie_layout);
        }
    }
}
