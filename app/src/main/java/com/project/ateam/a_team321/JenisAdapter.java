package com.project.ateam.a_team321;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class JenisAdapter extends RecyclerView.Adapter<JenisAdapter.JenisViewHolder> {

    ArrayList<Jenis> list;

    public JenisAdapter(ArrayList<Jenis> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public JenisAdapter.JenisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jenis, parent, false);
        return new JenisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JenisAdapter.JenisViewHolder holder, int position) {
        Jenis jenis = list.get(position);
        Glide.with(holder.itemView.getContext())
                .load(jenis.getGambar())
                .into(holder.imgPhoto);
        holder.tv_jenis.setText(jenis.getJenis());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JenisViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tv_jenis;
        public JenisViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_jenis);
            tv_jenis = itemView.findViewById(R.id.tv_jenis);
        }
    }
}
