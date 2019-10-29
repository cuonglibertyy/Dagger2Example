package com.example.dagger2example.ui.bookcar.dialog;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseDialog;
import com.example.dagger2example.model.historydetail.Results;
import com.example.dagger2example.model.historydetail.Trip;
import com.example.dagger2example.model.historydetail.User;
import com.example.dagger2example.model.historydetail.Vehicle;
import com.example.dagger2example.ui.notification.NotificationFragment;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DialogDriver extends BaseDialog {
    private static final String EXTRA_RESULTS = "EXTRA_RESULTS";
    String namedriver;

    public static DialogDriver newInstance(Results results) {
        Bundle args = new Bundle();
        DialogDriver fragment = new DialogDriver();
        args.putParcelable(EXTRA_RESULTS, results);
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.img_avatar_driver)
    CircleImageView img_avatar_driver;

    @BindView(R.id.tv_name_driver)
    TextView tv_name_driver;

    @BindView(R.id.tv_name_car)
    TextView tv_name_car;

    @BindView(R.id.tv_number_car)
    TextView tv_number_car;
    @BindView(R.id.btn_close)
    Button btn_close;
    @Override
    protected int getLayoutId() {
        return R.layout.dialog_driver;
    }

    @Override
    protected void initDatas() {
        if (getArguments() == null) {
            return;
        }
        Results results = getArguments().getParcelable(EXTRA_RESULTS);
        if (results == null) {
            return;
        }
        User user = results.getUser();
        if (user == null){
            return;
        }
        String nameDriver = user.getFullName();
        tv_name_driver.setText(nameDriver);
        String linkImage = user.getAvatar();
        loadAvatar(linkImage,img_avatar_driver);
                Vehicle vehicle = results.getVehicle();
        if (vehicle == null){
            return;
        }

        String nameCar = vehicle.getManufacturerName();
        tv_name_car.setText(nameCar);
        String typeCar = vehicle.getVehicleTypeName();
        tv_number_car.setText(typeCar);
        btn_close.setOnClickListener(ok ->{
            dismiss();
        });

        Log.d("dasdassda", "initDatas: "+nameCar+nameDriver+typeCar);


    }

    @Override
    protected void configViews() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void addEvents() {

    }
}
