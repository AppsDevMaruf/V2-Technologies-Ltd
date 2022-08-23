package com.marufalam.v2technologiesltd;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marufalam.v2technologiesltd.databinding.FragmentHomeBinding;
import com.marufalam.v2technologiesltd.models.SurveyResponse;
import com.marufalam.v2technologiesltd.networks.SurveyServices;
import com.marufalam.v2technologiesltd.viewmodel.SurveyViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private SurveyViewModel viewModel;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        /*viewModel = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Toast.makeText(requireContext(), "Thanks", Toast.LENGTH_SHORT).show();
        viewModel.getSurveyLiveData().observe(getViewLifecycleOwner(),response -> {
            Log.e("TAG", "onCreateView: "+response.toString());
            binding.textView.setText(response.toString());
            Toast.makeText(requireContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
        });
        viewModel.loadData();*/
        SurveyServices.getServices().getSurveyData("getSurvey").enqueue(new Callback<List<SurveyResponse>>() {
            @Override
            public void onResponse(Call<List<SurveyResponse>> call, Response<List<SurveyResponse>> response) {
                if (response.code() == 200){

                    Log.e(TAG, "OK data get"+response.body().toString()+"\n \n");
                    binding.textView.setText(""+response.body().toString());
                    Toast.makeText(requireActivity(), ""+response, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<SurveyResponse>> call, Throwable t) {

                    Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    Log.e(TAG, "error"+t.getMessage());
            }
        });


        return binding.getRoot();
    }
}