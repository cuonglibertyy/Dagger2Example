package com.example.dagger2example.ui.bookcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2example.R;
import com.example.dagger2example.model.typebike.Result;
import com.example.dagger2example.model.typebike.Results;
import com.example.dagger2example.ui.bookcar.activity.BookCarActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TypeBikeAdapter extends RecyclerView.Adapter<TypeBikeAdapter.ViewHolder> {

    private Context context;
    private List<Result> resultsList;

    public TypeBikeAdapter(Context context, List<Result> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }



    @NonNull
    @Override
    public TypeBikeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_bike,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeBikeAdapter.ViewHolder holder, int position) {
        Result results = resultsList.get(position);
//        holder.tvVehicleId.setText(results.getVehicleTypeId());
//        holder.estimatedDistance.setText(results.getEstimatedDistance());
//        holder.estimatedDuration.setText(results.getEstimatedDuration());
        holder.estimatedPrice.setText(results.getCarTypeName());

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVehicleId;
        private TextView estimatedPrice;
        private TextView estimatedDuration;
        private TextView estimatedDistance;
        private CircleImageView img_typeBike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_typeBike = itemView.findViewById(R.id.img_tyoeBike);
            tvVehicleId = (TextView) itemView.findViewById(R.id.tv_vehicleId);
            estimatedPrice = (TextView) itemView.findViewById(R.id.estimatedPrice);
            estimatedDuration = (TextView) itemView.findViewById(R.id.estimatedDuration);
            estimatedDistance = (TextView) itemView.findViewById(R.id.estimatedDistance);

        }
    }
}
