package com.project.ateam.a_team321.adapterPilihKeluarga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.project.ateam.a_team321.R;
import com.project.ateam.a_team321.daftarAgendesa.KepalaKeluarga;

import com.squareup.picasso.Picasso;

public class adapterPilihKeluarga extends FirestoreRecyclerAdapter<KepalaKeluarga, adapterPilihKeluarga.FoodHolder> {
    private adapterPilihKeluarga.OnItemClickListener listener;
    private Context context;
    private static final String TAG = "DetailFoodAct";

    public adapterPilihKeluarga(@NonNull FirestoreRecyclerOptions<KepalaKeluarga> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adapterPilihKeluarga.FoodHolder holder, int position, @NonNull KepalaKeluarga model) {
        holder.textViewTitle.setText(model.getNama_warga());
        holder.tv_rating.setText(String.valueOf(model.getDeskripsi_warga()));
        Picasso.get()
                .load(model.getImg_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img_photo);
    }



    @NonNull
    @Override
    public adapterPilihKeluarga.FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tuan_rumah,
                parent, false);
        return new adapterPilihKeluarga.FoodHolder(v);
    }


    class FoodHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView tv_rating;
        ImageView img_photo,img_check;

        public FoodHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_tuan_rumah);
            img_photo = itemView.findViewById(R.id.img_keluarga);
            tv_rating = itemView.findViewById(R.id.tv_deskripsi_keluarga);
            img_check = itemView.findViewById(R.id.img_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                        img_check.setVisibility(View.VISIBLE);

                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(adapterPilihKeluarga.OnItemClickListener listener) {
        this.listener = listener;
    }
}