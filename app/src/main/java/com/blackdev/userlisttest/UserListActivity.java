package com.blackdev.userlisttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private static final String TAG = "UserListActivity";
    RecyclerView recyclerView;
    userListAdapter adapter;
    List<PojoUsers> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView = findViewById(R.id.userListRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        if (Utils.isNetworkAvailable(this)) {
            // get all data from rest api
            Log.e(TAG, "Network Available");
            doApiCall();
        } else {
            Log.e(TAG, "Network not Available");
            Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();

        }
    }

    private void doApiCall() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait..."); // set message
        progressDialog.show();
        Log.e(TAG,"Getting List");

        ApiCall.getClient().getUserList(new Callback<Example>() {
            @Override
            public void success(Example pojoUsers, Response response) {

                progressDialog.dismiss();
                list = pojoUsers.getData();
                Log.e(TAG,"Got the list"+list.size());
                adapter = new userListAdapter(UserListActivity.this, list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(UserListActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.e("RETROFIT",error.toString());
            }
        });
    }
}