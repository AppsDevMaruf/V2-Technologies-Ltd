package com.marufalam.v2technologiesltd.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SurveyServices {

    public static SurveyServicesApi getServices(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://example-response.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SurveyServicesApi.class);
    }

}
