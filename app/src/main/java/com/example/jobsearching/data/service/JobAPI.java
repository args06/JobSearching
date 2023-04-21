package com.example.jobsearching.data.service;

import com.example.jobsearching.data.model.JobResponseItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobAPI {

    @GET("/api/recruitment/positions.json?")
    Call<ArrayList<JobResponseItem>> getJobs(
            @Query("page") int page,
            @Query("description") String desc,
            @Query("location") String loc,
            @Query("full_time") Boolean status
    );
}
