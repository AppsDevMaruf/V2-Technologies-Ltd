package com.marufalam.v2technologiesltd.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marufalam.v2technologiesltd.models.MainResponse;

public class SurveyViewModel extends ViewModel {

    private String TAG = SurveyViewModel.class.getSimpleName();

    private final MutableLiveData<MainResponse> surveyLiveData = new MutableLiveData<>();

    public LiveData<MainResponse> getSurveyLiveData() {
        return surveyLiveData;
    }

    public void loadData(){
        loadSurveyData();
    }

    private void loadSurveyData() {
        //final String endUrl = "getSurvey";

        /*SurveyServices.getServices().getSurveyData("getSurvey").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, retrofit2.Response<MainResponse> response) {
                if (response.code() == 200){
                    surveyLiveData.postValue(response.body());
                    Log.e(TAG, "OK data get"+response.body());

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                Log.e(TAG, "error"+t.getMessage());

            }
        });*/
    }
}
