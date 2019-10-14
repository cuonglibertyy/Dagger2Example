package com.example.dagger2example.ui.history.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dagger2example.R;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.history.ListPickUpPoint;
import com.example.dagger2example.model.history.TripSection;
import com.example.dagger2example.untils.DateUtils;

import java.util.List;

public class HistoryAdapter extends BaseSectionQuickAdapter<TripSection, BaseViewHolder> {

    public HistoryAdapter() {
        super(R.layout.item_content_history, R.layout.item_header_history, null);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, TripSection tripSection) {
        prevenScrollChange(holder);

        loadCreateDateTrip(holder, tripSection.header);
    }

    private void loadCreateDateTrip(BaseViewHolder holder, String header) {
        if (header == null && header.length() < 0) {
            return;
        }
        holder.setText(R.id.item_history_header_tv_create_date, header);
    }


    @Override
    protected void convert(BaseViewHolder holder, TripSection tripSection) {
        prevenScrollChange(holder);
        List<ListPickUpPoint> listPickUpPoints = tripSection.t.getListPickUpPoint();
        int sizeListPickUpPoint = listPickUpPoints.size();

        if (sizeListPickUpPoint == 1) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPoint(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadCreateBackgroundTrip(holder, tripSection.t.getTripPackageStatus());

            gone(holder, R.id.item_history_content_iv_dot_one,
                    R.id.item_history_content_iv_dot_two,
                    R.id.item_history_content_iv_marker_drof_off_one,
                    R.id.item_history_content_iv_marker_drof_off_two,
                    R.id.item_history_content_tv_drof_off_one,
                    R.id.item_history_content_tv_drof_off_two,
                    R.id.item_history_content_view_divider_one,
                    R.id.item_history_content_view_divider_two);
        } else if (sizeListPickUpPoint == 2) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPoint(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadDrofOffOneAddress(holder, tripSection.t.getListPickUpPoint().get(1).getAddress());
            loadCreateBackgroundTrip(holder, tripSection.t.getTripPackageStatus());

            gone(holder, R.id.item_history_content_iv_dot_two,
                    R.id.item_history_content_iv_marker_drof_off_two,
                    R.id.item_history_content_tv_drof_off_two,
                    R.id.item_history_content_view_divider_two);
        } else if (sizeListPickUpPoint == 3) {
            loadCreateTimeTrip(holder, DateUtils.convertMiliToTime(tripSection.t.getCreatedDate()));
            loadPickUpPoint(holder, tripSection.t.getListPickUpPoint().get(0).getAddress());
            loadDrofOffOneAddress(holder, tripSection.t.getListPickUpPoint().get(1).getAddress());
            loadDrofOffTwoAddress(holder, tripSection.t.getListPickUpPoint().get(2).getAddress());
            loadCreateBackgroundTrip(holder, tripSection.t.getTripPackageStatus());
        }

    }

    private void loadDrofOffTwoAddress(BaseViewHolder holder, String address) {
        holder.setText(R.id.item_history_content_tv_drof_off_two, address);
    }

    private void loadDrofOffOneAddress(BaseViewHolder holder, String address) {
        holder.setText(R.id.item_history_content_tv_drof_off_one, address);
    }

    private void loadCreateBackgroundTrip(BaseViewHolder holder, Integer tripPackageStatus) {
        if (tripPackageStatus == Constans.TRIP_STATUS_69) {
            holder.setBackgroundRes(R.id.item_history_content_tv_trip_status, R.color.history_color_bg_trip_status_ok);
            holder.setText(R.id.item_history_content_tv_trip_status, R.string.history_label_trip_status_success);
        } else {
            holder.setBackgroundRes(R.id.item_history_content_tv_trip_status, R.color.history_color_bg_trip_status_cancel);
            holder.setText(R.id.item_history_content_tv_trip_status, R.string.history_label_trip_status_cancel);
        }
    }

    private void loadPickUpPoint(BaseViewHolder holder, String address) {

        holder.setText(R.id.item_history_content_tv_pickup_point, address);
    }

    private void loadCreateTimeTrip(BaseViewHolder holder, String convertMiliToTime) {
        if (convertMiliToTime == null) {
            return;
        } else {
            holder.setText(R.id.item_history_content_tv_create_time, convertMiliToTime);
        }
    }

    private void prevenScrollChange(BaseViewHolder holder) {
        holder.setIsRecyclable(false);
    }

    private void gone(BaseViewHolder holder, int... views) {
        if (views != null && views.length > 0) {
            for (int id : views) {
                holder.setGone(id, false);
            }
        }
    }
}
