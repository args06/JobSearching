package com.example.jobsearching.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class JobResponseItem implements Parcelable {

	@SerializedName("company_logo")
	private String companyLogo;

	@SerializedName("how_to_apply")
	private String howToApply;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("company")
	private String company;

	@SerializedName("company_url")
	private String companyUrl;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	protected JobResponseItem(Parcel in) {
		companyLogo = in.readString();
		howToApply = in.readString();
		createdAt = in.readString();
		description = in.readString();
		company = in.readString();
		companyUrl = in.readString();
		location = in.readString();
		id = in.readString();
		type = in.readString();
		title = in.readString();
		url = in.readString();
	}

	public static final Creator<JobResponseItem> CREATOR = new Creator<JobResponseItem>() {
		@Override
		public JobResponseItem createFromParcel(Parcel in) {
			return new JobResponseItem(in);
		}

		@Override
		public JobResponseItem[] newArray(int size) {
			return new JobResponseItem[size];
		}
	};

	public String getCompanyLogo(){
		return companyLogo;
	}

	public String getHowToApply(){
		return howToApply;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getDescription(){
		return description;
	}

	public String getCompany(){
		return company;
	}

	public String getCompanyUrl(){
		return companyUrl;
	}

	public String getLocation(){
		return location;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"JobResponseItem{" + 
			"company_logo = '" + companyLogo + '\'' + 
			",how_to_apply = '" + howToApply + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",company = '" + company + '\'' + 
			",company_url = '" + companyUrl + '\'' + 
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(@NonNull Parcel dest, int flags) {
		dest.writeString(companyLogo);
		dest.writeString(howToApply);
		dest.writeString(createdAt);
		dest.writeString(description);
		dest.writeString(company);
		dest.writeString(companyUrl);
		dest.writeString(location);
		dest.writeString(id);
		dest.writeString(type);
		dest.writeString(title);
		dest.writeString(url);
	}
}