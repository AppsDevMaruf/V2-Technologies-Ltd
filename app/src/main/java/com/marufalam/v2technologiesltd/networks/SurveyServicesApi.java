package com.marufalam.v2technologiesltd.networks;


import com.marufalam.v2technologiesltd.models.Survey;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface SurveyServicesApi {
    @GET
    Call<List<Survey>> getSurveyData(@Header("timestamp") String timestamp, @Url String url);


}
