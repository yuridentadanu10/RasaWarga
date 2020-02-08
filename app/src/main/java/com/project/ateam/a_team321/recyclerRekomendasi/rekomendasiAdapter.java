package com.project.ateam.a_team321.recyclerRekomendasi;

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
import com.project.ateam.a_team321.daftarAgendesa.ModelPaketWisata;
import com.squareup.picasso.Picasso;

public class rekomendasiAdapter extends FirestoreRecyclerAdapter<ModelPaketWisata, rekomendasiAdapter.FoodHolder> {
    private rekomendasiAdapter.OnItemClickListener listener;
    private Context context;
    private static final String TAG = "DetailFoodAct";

    public rekomendasiAdapter(@NonNull FirestoreRecyclerOptions<ModelPaketWisata> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull rekomendasiAdapter.FoodHolder holder, int position, @NonNull ModelPaketWisata model) {
        holder.textViewTitle.setText(model.getNama_desa());
        holder.tv_rating.setText(String.valueOf(model.getJenis()));
        Picasso.get()
                .load(model.getImg_url())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img_photo);
    }



    @NonNull
    @Override
    public rekomendasiAdapter.FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paket,
                parent, false);
        return new rekomendasiAdapter.FoodHolder(v);
    }


    class FoodHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView tv_rating;
        ImageView img_photo;

        public FoodHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_desa);
            img_photo = itemView.findViewById(R.id.img_list_photo);
            tv_rating = itemView.findViewById(R.id.tv_jenis);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(rekomendasiAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}