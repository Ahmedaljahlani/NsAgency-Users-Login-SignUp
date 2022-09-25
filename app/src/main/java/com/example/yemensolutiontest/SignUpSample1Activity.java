package com.example.yemensolutiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpSample1Activity extends AppCompatActivity {


    TextInputLayout nameTxtInput, emailTxtInput, phoneNumberTxtInput, locationTxtInput;
    Button signUpBtn;
    TextInputEditText pickLocation;
    ProgressBar progressBar;

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    int LOCATION_REQUEST_CODE = 10001;
    private static final String TAG = "SignUp1Activity";
    LocationRequest locationRequest;

    JSONPlaceHolder jsonPlaceholder;

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                Log.d(TAG, "onLocationResult: " + location.toString());
                startLocationUpdates();
//                Objects.requireNonNull(locationTxtInput.getEditText()).setText("Lat: " + currentLocation.getLatitude() + " - Long: " + currentLocation.getLongitude());
                locationTxtInput.getEditText().setText("Lat: " + currentLocation.getLatitude() + " - Long: " + currentLocation.getLongitude());
//                Toast.makeText(MainActivity.this, "Locations"+location, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sample1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(1);
        locationRequest.setFastestInterval(5);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        nameTxtInput = findViewById(R.id.login_user_name_txtFld);
        emailTxtInput = findViewById(R.id.login_email_txtFld);
        phoneNumberTxtInput = findViewById(R.id.login_phone_txtFld);
        locationTxtInput = findViewById(R.id.login_location_txtFld);
        pickLocation = findViewById(R.id.location_picker);
        signUpBtn = findViewById(R.id.login_sign_up_btn);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        jsonPlaceholder = retrofit.create(JSONPlaceHolder.class);


//        pickLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SignUpSample1Activity.this, "location Picked", Toast.LENGTH_SHORT).show();
//            }
//        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFields();

            }
        });

    }

    private void validateFields() {

        CharSequence usrEmail = emailTxtInput.getEditText().getText().toString().trim();
        CharSequence usrPhone = phoneNumberTxtInput.getEditText().getText().toString();
        if (nameTxtInput.getEditText().getText().toString().trim().isEmpty()) {
            nameTxtInput.setErrorEnabled(true);
            nameTxtInput.setError("required user name");
        } else {
            nameTxtInput.setErrorEnabled(false);
//            signNewUser();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usrEmail).matches()) {
            emailTxtInput.setErrorEnabled(true);
            emailTxtInput.setError(" not valid email");
        } else if (emailTxtInput.getEditText().getText().toString().trim().isEmpty()) {
            emailTxtInput.setErrorEnabled(true);
            emailTxtInput.setError("required email");
        } else {
            emailTxtInput.setErrorEnabled(false);
//            signNewUser();
        }
        if (phoneNumberTxtInput.getEditText().getText().toString().isEmpty()) {
            phoneNumberTxtInput.setErrorEnabled(true);
            phoneNumberTxtInput.setError("required phone number");
        } else {
            phoneNumberTxtInput.setErrorEnabled(false);
//            signNewUser();
        }
        if(locationTxtInput.getEditText().getText().toString().trim().isEmpty()){
            startLocationUpdates();
        }

    }

    private void signNewUser() {

        Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();

        String username, userEmail, UserLocation;
        int UserPhone;
        username = nameTxtInput.getEditText().getText().toString();
        userEmail = emailTxtInput.getEditText().getText().toString();
        UserLocation = locationTxtInput.getEditText().getText().toString();
        UserPhone = Integer.parseInt(phoneNumberTxtInput.getEditText().getText().toString());

        Users user = new Users(username, userEmail, UserPhone, UserLocation);

        Call<Users> call = jsonPlaceholder.addUser(user);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(SignUpSample1Activity.this, "Successfully user added ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(SignUpSample1Activity.this, "user not added ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateUser() {

        String username, userEmail, userLocation;
        int userPhone, userId;
        username = nameTxtInput.getEditText().getText().toString();
        userEmail = emailTxtInput.getEditText().getText().toString();
        userPhone = Integer.parseInt(phoneNumberTxtInput.getEditText().getText().toString());
        userLocation = locationTxtInput.getEditText().getText().toString();
        userId = 1;

        Users user = new Users(username, userEmail, userPhone, userLocation, userId);

        Call<Users> call = jsonPlaceholder.patchPost(userId, user);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                //update target data

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }

    private void deleteUser() {
        int userId = 1;
        Call<Void> call = jsonPlaceholder.deleteUser(userId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(SignUpSample1Activity.this, "Deleted Successfully : " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
//        startLocationUpdates();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
            checkSettingsAndStartLocationUpdates();
        } else {
//            getLastLocation();
            askLocationPermission();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }

    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                //Settings of device are satisfied and we can start location updates
                startLocationUpdates();
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(SignUpSample1Activity.this, 1001);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void getLastLocation() {
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    //We have a location
                    Log.d(TAG, "onSuccess: " + location.toString());
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());
//                    Toast.makeText(SignUpSample1Activity.this, "Latitude: " + location.getLatitude() + "Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onSuccess: Location was null...");
                }
            }
        });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(SignUpSample1Activity.this, "fail", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d(TAG, "askLocationPermission: you should show an alert dialog...");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getLastLocation();
                checkSettingsAndStartLocationUpdates();
            } else {
                //Permission not granted
            }
        }
    }

}