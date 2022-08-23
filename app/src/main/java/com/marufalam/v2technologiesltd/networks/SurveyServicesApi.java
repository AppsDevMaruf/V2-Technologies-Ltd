package com.marufalam.v2technologiesltd.networks;


import com.marufalam.v2technologiesltd.models.SurveyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SurveyServicesApi {
    @GET
    Call<List<SurveyResponse>> getSurveyData(@Url String url);


}
