package com.example.dagger2example.ui.bookcar.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.dagger2example.R;
import com.example.dagger2example.base.BaseActivity;
import com.example.dagger2example.model.CancelTripEvent;
import com.example.dagger2example.model.NewTripEvent;
import com.example.dagger2example.model.body.DropOffOne;
import com.example.dagger2example.model.body.DropOffTwo;
import com.example.dagger2example.model.body.LocationBody;
import com.example.dagger2example.model.body.StartLocation;
import com.example.dagger2example.model.error.Error;
import com.example.dagger2example.model.historydetail.User;
import com.example.dagger2example.model.login.Results;
import com.example.dagger2example.model.typebike.Result;
import com.example.dagger2example.ui.bookcar.adapter.TypeBikeAdapter;
import com.example.dagger2example.ui.bookcar.contract.BookCarContract;
import com.example.dagger2example.listenner.Listenner;
import com.example.dagger2example.ui.bookcar.presenter.BookCarPresenter;
import com.example.dagger2example.widget.LoadingDialog;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
import com.novoda.merlin.Merlin;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

public class BookCarActivity extends BaseActivity implements  BookCarContract.View, OnMapReadyCallback, Listenner {


    @Inject
    BookCarPresenter presenter;
    private GoogleMap mMap;
// google place
    private PlacesClient placesClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private List<AutocompletePrediction> predictionList;
//    @BindView(R.id.layoutBottomSheet)
//    LinearLayout layoutBottomSheet;
    // RecycleView
@BindView(R.id.recyclerView_typeBike)
RecyclerView recyclerView_typeBike;
    @BindView(R.id.searchBar)
    MaterialSearchBar materialSearchBar;
    @BindView(R.id.btn_direction)
    Button btn_direction;
    @BindView(R.id.viewTypeBike)
    LinearLayout viewTypeBike;
    @BindView(R.id.tv_distance)
    TextView tv_distance;
    @BindView(R.id.tv_duration)
    TextView tv_duration;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.edt_mylocation)
    TextView edt_mylocation;

    private String nameMylocation;

    private Location mLastKnowLocation;
    private LocationCallback locationCallback;
    private TypeBikeAdapter typeBikeAdapter;
    private View mapView;
    public double latstart;
    public double longstart;
    public double latdropoffone;
    public double longdropoffone;
    public double latdropofftwo = 0.0;
    public double longdropofftwo = 0.0;
    public Long estimatedDistance  ;
    public Long estimatedDuration ;
    public Double estimatedPrice;
    public Long vehicleTypeId ;
    public Long vehicleTypeLuxury;

    List<com.example.dagger2example.model.typebike.Result> resultList = new ArrayList<>();
    private StartLocation startlocation;
    private DropOffOne dropOffOne;
    private DropOffTwo dropOffTwo;
    String jsonString;
    List<LocationBody> locationBodyList = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, BookCarActivity.class));
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachView() {
        presenter.detachView();
    }



    @Override
    protected void configViews() {

    }

    @Override
    protected void addEvens() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapView = mapFragment.getView();

        String apikey = "AIzaSyA9rTnxqBiap6yioOqcGREcyrcZno2ShGU";

        Places.initialize(context, apikey);
        placesClient = Places.createClient(context);
        initView();
        materialsearchbar();
        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (locationBodyList.size() > 1){
                    startlocation = new StartLocation(locationBodyList.get(0).getStartlocation().getLatitude(),
                            locationBodyList.get(0).getStartlocation().getLongitude());

                    dropOffOne = new DropOffOne(locationBodyList.get(1).getDropOffOne().getLatitude(),
                            locationBodyList.get(1).getDropOffOne().getLongitude());

                }
                if (locationBodyList.size() > 2){
                    dropOffTwo = new DropOffTwo(locationBodyList.get(2).getDropOffTwo().getLatitude(),
                            locationBodyList.get(2).getDropOffTwo().getLongitude());
                }

                LocationBody locationBody = new LocationBody(startlocation,dropOffOne,dropOffTwo,estimatedDistance,estimatedDuration,estimatedPrice,vehicleTypeLuxury,vehicleTypeId);
               String jsonString = JSON.toJSONString(locationBody);
                presenter.PostBookBike(jsonString);
                Log.d("sadasdasdsa", "json: "+jsonString);
            }
        });

    }

    @Override
    protected Merlin initMerlin() {
        return new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .withBindableCallbacks()
                .build(this);
    }


    @Override
    protected void setupComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_book_car;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCancelTripNotificationEvent(CancelTripEvent cancelTripEvent) {
        String tripCode = cancelTripEvent.getTripCode();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewTripNotification(NewTripEvent newTripEvent) {
        String newTrip = newTripEvent.getTripId();
        presenter.getLastStatus();
        Toast.makeText(context, "tripID"+newTrip, Toast.LENGTH_SHORT).show();

    }

    private void materialsearchbar() {

        final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();


        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
               startSearch(text.toString(), true, null, true);
            }


            @SuppressLint("MissingPermission")
            @Override
            public void onButtonClicked(int buttonCode) {
                if (buttonCode == MaterialSearchBar.BUTTON_NAVIGATION) {

                } else if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
                    materialSearchBar.disableSearch();
                    viewTypeBike.setVisibility(View.GONE);
                    getDeviceLocation();
                    mMap.clear();
                    return;


                }
            }
        });
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
                        .setCountry("CN")
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setSessionToken(token)
                        .setQuery(s.toString())
                        .build();

                placesClient.findAutocompletePredictions(predictionsRequest).addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                        if (task.isSuccessful()) {
                            FindAutocompletePredictionsResponse predictionsResponse = task.getResult();
                            if (predictionsResponse != null) {
                                predictionList = predictionsResponse.getAutocompletePredictions();
                                List<String> suggestionList = new ArrayList<>();
                                for (int i = 0; i < predictionList.size(); i++) {
                                    AutocompletePrediction prediction = predictionList.get(i);
                                    Log.d("ádasd", "onComplete: "+prediction.getPrimaryText(null).toString());
                                    suggestionList.add(prediction.getFullText(null).toString());

                                }
                                materialSearchBar.updateLastSuggestions(suggestionList);
                                if (!materialSearchBar.isSuggestionsVisible()) {
                                    materialSearchBar.clearSuggestions();
                                }
                            }
                        } else {
                            Log.d("mytag", "prediction fetching");
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setSuggestionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
                if (position >= predictionList.
                        size()) {
                    return;
                }
                AutocompletePrediction selectionPrediction = predictionList.get(position);
                String sugguestion = materialSearchBar.getLastSuggestions().get(position).toString();
                materialSearchBar.setText(sugguestion);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialSearchBar.clearSuggestions();
                    }
                }, 1000);
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(materialSearchBar.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                    String placeId = selectionPrediction.getPlaceId();
                    List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME);

                    FetchPlaceRequest fetchPlaceRequest = FetchPlaceRequest.builder(placeId, fields).build();
                    placesClient.fetchPlace(fetchPlaceRequest).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                        @Override
                        public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {

                            mMap.clear();
                            Place place = fetchPlaceResponse.getPlace();
                            LatLng latLngofPlace = place.getLatLng();
                            String place_ID = place.getId();
                            Log.d("dsasda", "onSuccess: "+place_ID);
                            latdropoffone = latLngofPlace.latitude;
                            longdropoffone = latLngofPlace.longitude;


                            Log.d("ádadadadsda", "lat: "+latdropoffone+"long"+longdropoffone);
                            mMap.addMarker(new MarkerOptions().position(latLngofPlace).title(place.getName()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngofPlace, 15));
//                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            if (latLngofPlace != null) {

                                presenter.postTypeBike(String.valueOf(latstart),String.valueOf(longstart),String.valueOf(latdropoffone),String.valueOf(longdropoffone),String.valueOf(latdropofftwo),String.valueOf(longdropofftwo));
                                startlocation = new StartLocation(mLastKnowLocation.getLatitude(),mLastKnowLocation.getLongitude());
                                dropOffOne = new DropOffOne(latdropoffone,longdropoffone);
                                dropOffTwo = new DropOffTwo(0.0,0.0);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof ApiException) {
                                ApiException apiException = (ApiException) e;
                                apiException.printStackTrace();
                                int statuscode = apiException.getStatusCode();
                                Log.d("error", "onFailure: " + e.getMessage());
                                Log.d("error", "status" + statuscode);
                            }
                        }
                    });
                }
            }

            @Override
            public void OnItemDeleteListener(int position, View v) {

            }
        });
    }


    private void initView() {

        recyclerView_typeBike.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recyclerView_typeBike.setHasFixedSize(true);



    }



    @Override
    public void showProgress(boolean show) {
        if (show) {
            LoadingDialog.getInstance().showLoading(this);
        } else {
            LoadingDialog.getInstance().hideLoading();
        }
    }

    @Override
    public void showError(int stringResId) {

    }

    @Override
    public void showError() {
        showProgress(false);
        Toasty.error(context,"Không tìm thấy tài xế",Toasty.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessful() {

        showProgress(false);
        Toasty.success(context,"Tìm tài xế thành công",Toasty.LENGTH_SHORT).show();
        viewTypeBike.setVisibility(View.GONE);
    }

    @Override
    public void onComplete(Results results) {

    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (mapView != null && mapView.findViewById(Integer.parseInt("1")) != null) {
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 40, 180);
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                getDeviceLocation();
            }
        });
        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException resolvable = (ResolvableApiException) e;

                    try {
                        resolvable.startResolutionForResult((Activity) context, 51);
                    } catch (IntentSender.SendIntentException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        mFusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            mLastKnowLocation = task.getResult();
                            if (mLastKnowLocation != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnowLocation.getLatitude(), mLastKnowLocation.getLongitude()), 14));
                            latstart =mLastKnowLocation.getLatitude();
                               longstart = mLastKnowLocation.getLongitude();
                               String latlng = String.valueOf(latstart)+","+String.valueOf(longstart);
                               presenter.getMyLocationName(latlng);
                                Log.d("sadasdas2", "latlng: "+latlng);
                            } else {
                                final LocationRequest locationRequest = LocationRequest.create();
                                locationRequest.setInterval(10000);
                                locationRequest.setFastestInterval(5000);
                                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                                locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult) {
                                        super.onLocationResult(locationResult);
                                        if (locationResult == null) {
                                            return;

                                        }
                                        mLastKnowLocation = locationResult.getLastLocation();
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnowLocation.getLatitude(), mLastKnowLocation.getLongitude()), 15));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnowLocation.getLatitude(), mLastKnowLocation.getLongitude()), 15));
                                        mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
                                    }
                                };
                                mFusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

                            }
                        } else {
                            Toast.makeText(context, "unable Location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void getResult(Error error) {
        if (error.getResults().getMessage()!= null){
            showProgress(false);
            Toasty.warning(context,error.getResults().getMessage(),Toasty.LENGTH_SHORT).show();
            return;
        }
        else {
            showProgress(false);
            Log.d("sadasdadasasdádas", "error: "+error);
            showError();
            return;
        }
    }

    @Override
    public void getTypeBike(List<Result> results) {
        if (results!=null){
            viewTypeBike.setVisibility(View.VISIBLE);
            resultList = results;
            typeBikeAdapter = new TypeBikeAdapter(this,resultList,this);
            recyclerView_typeBike.setAdapter(typeBikeAdapter);

            loadEstimatedPrice(results.get(0).getEstimatedPrice(),tv_price);
            loadEstimatedDuration(results.get(0).getEstimatedDuration(),tv_duration);
            loadEstimatedDistance(results.get(0).getEstimatedDistance(),tv_distance);

            estimatedDistance = results.get(0).getEstimatedDistance();
            estimatedDuration = results.get(0).getEstimatedDuration();
            estimatedPrice = results.get(0).getEstimatedPrice();
            vehicleTypeId = results.get(0).getVehicleTypeId();
            vehicleTypeLuxury = results.get(0).getVehicleTypeLuxury();
        }else {
            showError();
        }




    }

    @Override
    public void showName(List<com.example.dagger2example.model.geocode.Result> result) {
        if (result !=null){
            nameMylocation = result.get(0).getFormattedAddress();
            edt_mylocation.setText(nameMylocation);
            Log.d("sdassdaadssadasdsadas", "showName: "+nameMylocation);

        }

    }

    @Override
    public void showInformation(com.example.dagger2example.model.historydetail.Results results) {

        Toast.makeText(context, "Thông tin tài xế" +results.getUser().getFullName()+"Tên xe"+results.getUser().getLicence(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClickListener(Result result) {
            loadEstimatedDistance(result.getEstimatedDistance(),tv_distance);
            loadEstimatedDuration(result.getEstimatedDuration(),tv_duration);
            loadEstimatedPrice(result.getEstimatedPrice(),tv_price);


            estimatedDistance = result.getEstimatedDistance();
            estimatedDuration = result.getEstimatedDuration();
            estimatedPrice = result.getEstimatedPrice();
            vehicleTypeId = result.getVehicleTypeId();
            vehicleTypeLuxury = result.getVehicleTypeLuxury();


    }
}
