package com.example.jobsearching.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jobsearching.data.model.JobResponseItem;
import com.example.jobsearching.data.service.JobServiceAPI;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<JobResponseItem>> _jobs = new MutableLiveData<>();

    public LiveData<ArrayList<JobResponseItem>> getJobs() {
        return _jobs;
    }

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public MainViewModel() {
        getJobsItem(null, null, null);
    }

    public void getJobsItem(String desc, String loc, Boolean status) {
        _isLoading.setValue(true);
        Call<ArrayList<JobResponseItem>> client = JobServiceAPI.getApiService().getJobs(1, desc, loc, status);
        client.enqueue(new Callback<ArrayList<JobResponseItem>>() {

            @Override
            public void onResponse(@NotNull Call<ArrayList<JobResponseItem>> call,
                                   @NotNull Response<ArrayList<JobResponseItem>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _jobs.setValue(response.body());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("Response", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<JobResponseItem>> call, @NotNull Throwable t) {
                _isLoading.setValue(false);
                Log.e("Failure", "onFailure: " + t.getMessage());
            }
        });
    }
}
