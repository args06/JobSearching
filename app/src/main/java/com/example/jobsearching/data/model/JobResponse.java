package com.example.jobsearching.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JobResponse{

	@SerializedName("JobResponse")
	private List<JobResponseItem> jobResponse;

	public List<JobResponseItem> getJobResponse(){
		return jobResponse;
	}

	@Override
 	public String toString(){
		return 
			"JobResponse{" + 
			"jobResponse = '" + jobResponse + '\'' + 
			"}";
		}
}