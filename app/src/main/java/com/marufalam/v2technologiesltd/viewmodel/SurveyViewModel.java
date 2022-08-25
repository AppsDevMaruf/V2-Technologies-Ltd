package com.marufalam.v2technologiesltd.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marufalam.v2technologiesltd.models.Survey;
import com.marufalam.v2technologiesltd.networks.SurveyServices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurveyViewModel extends ViewModel {

    private String TAG = SurveyViewModel.class.getSimpleName();
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    private final MutableLiveData<List<Survey>> surveyLiveData = new MutableLiveData<>();

    public LiveData<List<Survey>> getSurveyLiveData() {
        return surveyLiveData;
    }

    public void loadData() {
        loadSurveyData();
    }

    private void loadSurveyData() {
        final String endUrl = "getSurvey";

        SurveyServices.getServices().getSurveyData(timeStamp, "getSurvey").enqueue(new Callback<List<Survey>>() {
            @Override
            public void onResponse(Call<List<Survey>> call, Response<List<Survey>> response) {
                if (response.code() == 200) {
                    surveyLiveData.postValue(response.body());
                   /* for (int i = 0; i < Objects.requireNonNull(response.body()).size(); i++) {

                        //SurveyResponse survey = response.body().get(i);

                        //Log.i("TAG", "onResponse: " + survey.toString() + "\n \n");

                        Log.e(TAG, "OK data get" + response.body());
                        //surveyList.add(survey);

                    }*/


                }
            }

            @Override
            public void onFailure(Call<List<Survey>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "error" + t.getMessage());
            }
        });


    }
}
