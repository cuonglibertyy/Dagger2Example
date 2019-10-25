package com.example.dagger2example.ui.bookcar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dagger2example.R;
import com.example.dagger2example.model.typebike.Result;
import com.example.dagger2example.listenner.Listenner;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.dagger2example.R.color.black_press;

public class TypeBikeAdapter extends RecyclerView.Adapter<TypeBikeAdapter.ViewHolder> {

    private Context context;
    private List<Result> resultsList;
    private Listenner listenner;

    public TypeBikeAdapter(Context context, List<Result> resultsList, Listenner listenner) {
        this.context = context;
        this.resultsList = resultsList;
        this.listenner = listenner;
    }


    @NonNull
    @Override
    public TypeBikeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_bike, parent, false);
        return new ViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TypeBikeAdapter.ViewHolder holder, int position) {
        Result result = resultsList.get(position);

        holder.tvVehicleId.setText(result.getCarTypeName());

        holder.itemView.setOnClickListener(view -> {
            listenner.onClickListener(result);
            holder.itemView.setBackgroundColor(R.color.blue);

        });


    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVehicleId;
        private LinearLayout background_item;

        private CircleImageView img_typeBike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_typeBike = itemView.findViewById(R.id.img_tyoeBike);
            tvVehicleId = (TextView) itemView.findViewById(R.id.tv_vehicleId);
            background_item = itemView.findViewById(R.id.background_item);


            Glide.with(context)
                    .load(R.drawable.car)
                    .placeholder(R.drawable.car)
                    .into(img_typeBike);

        }
    }
}
