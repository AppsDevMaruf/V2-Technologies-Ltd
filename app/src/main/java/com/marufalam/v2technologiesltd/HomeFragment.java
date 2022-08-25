package com.marufalam.v2technologiesltd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.marufalam.v2technologiesltd.databinding.FragmentHomeBinding;
import com.marufalam.v2technologiesltd.models.Option;
import com.marufalam.v2technologiesltd.models.Survey;
import com.marufalam.v2technologiesltd.utils.Type;
import com.marufalam.v2technologiesltd.viewmodel.SurveyViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private SurveyViewModel viewModel;
    Uri uri;
    List<Survey> surveyList;
    int referTo = 0;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SurveyViewModel.class);
        Toast.makeText(requireContext(), "Thanks", Toast.LENGTH_SHORT).show();
        viewModel.getSurveyLiveData().observe(getViewLifecycleOwner(), response -> {

            surveyList = new ArrayList<>();
           /* Log.e("TAG", "onCreateView: "+response.toString());
            //binding.textView.setText(response.toString());
            Toast.makeText(requireContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();*/
            for (int i = 0; i < Objects.requireNonNull(response).size(); i++) {

                Survey survey = response.get(i);

                Log.i("TAG", "onResponse: " + survey.toString() + "\n \n");

                surveyList.add(survey);

            }
            setSurvey(0);

        });

        viewModel.loadData();
        return binding.getRoot();
    }



    private void setSurvey(int index) {

        Survey survey = surveyList.get(index);

        Log.i("TAG", "setSurvey: " + referTo);


        switch (survey.getType()) {

            case Type.multipleChoice:
                manageMultipleChoice(survey);
                break;

            case Type.dropdown:
                dropdown(survey);
                break;

            case Type.textInput:
                textInput(survey);
                break;

            case Type.checkbox:
                checkBox(survey);
                break;

            case Type.numberInput:
                numberInput(survey);
                break;

            case Type.camera:
                Toast.makeText(requireContext(), "Welcome To Camera", Toast.LENGTH_SHORT).show();
                camera(survey);
                /*ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();*/



                break;
        }


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==Activity.RESULT_OK){
            Uri uri = data.getData();
            setAllAOptionDisable();
            binding.loadingGif.setVisibility(View.GONE);
            binding.displayImg.setVisibility(View.VISIBLE);
            binding.displayImg.setImageURI(uri);
        }
        else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }

    }


    //camera
    private void camera(Survey survey) {
        setAllAOptionDisable();
        AtomicBoolean isRequired = new AtomicBoolean(false);
        binding.camera.setVisibility(View.VISIBLE);
        binding.displayImg.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());
        binding.displayImg.setOnClickListener(view -> {
             ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

        });


        if (survey.getOptions() == null || survey.getOptions().size() == 0) {
            //   referTo = Integer.parseInt(survey.getReferTo());
        }


        if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
            binding.actionBtn.setText("submit");
        } else {
            binding.actionBtn.setText("Next");
        }


        binding.actionBtn.setOnClickListener(v -> {

            if (isRequired.get() && survey.getRequired()) {
                Toast.makeText(requireActivity(), "Options Check Required", Toast.LENGTH_SHORT).show();
                return;
            }


            if (survey.getReferTo().equals("submit")) {
                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();

            } else {
                setSurvey(referTo - 1);

            }


        });

    }

    //checkbox
    private void checkBox(Survey survey) {
        setAllAOptionDisable();
        AtomicBoolean isRequired = new AtomicBoolean(false);
        binding.checkbox.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());


        if (survey.getOptions() == null || survey.getOptions().size() == 0) {
            referTo = Integer.parseInt(survey.getReferTo());
        }


        if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
            binding.actionBtn.setText("submit");
        } else {
            binding.actionBtn.setText("Next");
        }

        binding.chk1.setText(survey.getOptions().get(0).getValue());
        binding.chk2.setText(survey.getOptions().get(1).getValue());
        binding.chk3.setText(survey.getOptions().get(2).getValue());


        binding.chk1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRequired.set(isChecked);
            referTo = Integer.parseInt(survey.getOptions().get(0).getReferTo());

        });

        binding.chk2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRequired.set(isChecked);
            referTo = Integer.parseInt(survey.getOptions().get(1).getReferTo());
        });

        binding.chk3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRequired.set(isChecked);
            referTo = Integer.parseInt(survey.getOptions().get(2).getReferTo());
        });


        binding.actionBtn.setOnClickListener(v -> {

            if (!isRequired.get() && survey.getRequired()) {
                Toast.makeText(requireActivity(), "Options Check Required", Toast.LENGTH_SHORT).show();
                return;
            }


            if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();

            } else {
                setSurvey(referTo - 1);

            }


        });


    }

    /* public void loadFragment(Fragment fragment, Boolean bool) {
         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
         transaction.replace(R.id.frameLayout, fragment);
         if (bool)
             transaction.addToBackStack(null);
         transaction.commit();
     }*/
    //numberInput
    private void numberInput(Survey survey) {
        setAllAOptionDisable();
        binding.numberInput.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());

        AtomicBoolean isRequired = new AtomicBoolean(false);


        if (survey.getOptions() == null || survey.getOptions().size() == 0) {
            //referTo = Integer.parseInt(survey.getReferTo());
        }

        if (binding.numberInput.getText().toString().equals("")) {
            isRequired.set(false);

        }


        if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
            binding.actionBtn.setText("submit");
        } else {
            binding.actionBtn.setText("Next");
        }


        binding.actionBtn.setOnClickListener(v -> {

            if (isRequired.get() && survey.getRequired()) {
                Toast.makeText(requireActivity(), "Options Check Required", Toast.LENGTH_SHORT).show();
                return;
            }


            if (survey.getReferTo().equals("submit")) {
                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();

            } else {
                setSurvey(referTo - 1);

            }


        });


    }

    //textInput
    private void textInput(Survey survey) {
        setAllAOptionDisable();
        binding.textInput.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());

        AtomicBoolean isRequired = new AtomicBoolean(false);


        if (survey.getOptions() == null || survey.getOptions().size() == 0) {
            referTo = Integer.parseInt(survey.getReferTo());//6
        }

        if (binding.textInput.getText().toString().equals("")) {
            isRequired.set(false);

        }


        if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
            binding.actionBtn.setText("submit");
        } else {
            binding.actionBtn.setText("Next");
        }


        binding.actionBtn.setOnClickListener(v -> {

//            if (!isRequired.get() && survey.getRequired()) {
//                Toast.makeText(this, "Options Check Required", Toast.LENGTH_SHORT).show();
//                return;
//            }


            if (survey.getReferTo().equals("submit")) {
                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();

            } else {
                setSurvey(referTo - 1);

            }


        });


    }

    //dropdown
    private void dropdown(Survey survey) {
        setAllAOptionDisable();
        binding.dropdown.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());

        if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
            binding.actionBtn.setText("submit");
        } else {
            binding.actionBtn.setText("Next");
        }


        String[] opt_value = new String[survey.getOptions().size()]; //for value
        Option[] options = new Option[survey.getOptions().size()];
        // Option[] options = (Option[]) survey.getOptions().toArray();

        for (int i = 0; i < survey.getOptions().size(); i++) {
            opt_value[i] = survey.getOptions().get(i).getValue();
            options[i] = survey.getOptions().get(i);
        }

        ArrayAdapter<String> dropDownAdapter = new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, opt_value);

        dropDownAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.dropdown.setAdapter(dropDownAdapter);


        binding.dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        referTo = Integer.parseInt(options[0].getReferTo());
                        break;

                    case 1:
                        referTo = Integer.parseInt(options[1].getReferTo());
                        break;

                    case 2:
                        referTo = Integer.parseInt(options[2].getReferTo());
                        break;

                    case 3:
                        referTo = Integer.parseInt(options[3].getReferTo());
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.actionBtn.setOnClickListener(v -> {

            if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {
                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();


            } else {

                setSurvey(referTo - 1);

            }


        });


    }

    //MultipleChoice
    private void manageMultipleChoice(Survey survey) {
        setAllAOptionDisable();
        binding.multipleChoice.setVisibility(View.VISIBLE);
        binding.question.setText(survey.getQuestion());
        binding.actionBtn.setText("Next");
        List<Option> options = survey.getOptions();


        binding.opt1.setText(options.get(0).getValue());
        binding.opt2.setText(options.get(1).getValue());
        binding.opt3.setText(options.get(2).getValue());
        binding.opt4.setText(options.get(3).getValue());
        binding.opt5.setText(options.get(4).getValue());

        binding.multipleChoice.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == binding.opt1.getId()) {
                referTo = Integer.parseInt(survey.getOptions().get(0).getReferTo());

            } else if (checkedId == binding.opt2.getId()) {
                referTo = Integer.parseInt(survey.getOptions().get(1).getReferTo());

            } else if (checkedId == binding.opt3.getId()) {
                referTo = Integer.parseInt(survey.getOptions().get(2).getReferTo());

            } else if (checkedId == binding.opt4.getId()) {
                referTo = Integer.parseInt(survey.getOptions().get(3).getReferTo());

            } else if (checkedId == binding.opt5.getId()) {
                referTo = Integer.parseInt(survey.getOptions().get(4).getReferTo());

            }


        });

        binding.actionBtn.setOnClickListener(v -> {

            if (survey.getReferTo() != null && survey.getReferTo().equals("submit")) {

                Toast.makeText(requireActivity(), "Survey Submitted", Toast.LENGTH_SHORT).show();


            } else {

                setSurvey(referTo - 1);

            }


        });


    }


    private void setAllAOptionDisable() {
        binding.multipleChoice.setVisibility(View.GONE);
        binding.textInput.setVisibility(View.GONE);
        binding.dropdown.setVisibility(View.GONE);
        binding.checkbox.setVisibility(View.GONE);
        binding.numberInput.setVisibility(View.GONE);
        binding.camera.setVisibility(View.GONE);
        binding.loadingGif.setVisibility(View.GONE);


    }


}