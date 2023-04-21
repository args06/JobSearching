package com.example.jobsearching.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jobsearching.R;
import com.example.jobsearching.data.model.JobResponseItem;
import com.example.jobsearching.databinding.FragmentHomeBinding;
import com.example.jobsearching.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class HomeFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    FragmentHomeBinding binding;
    Boolean isFilterOpen = false;
    String searchedvalue;
    MainViewModel mainViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getJobs().observe(getViewLifecycleOwner(), this::setJobsData);
        mainViewModel.isLoading().observe(getViewLifecycleOwner(), this::showLoading);

        binding.ivFilter.setOnClickListener(this);
        binding.btnFilter.setOnClickListener(this);
        binding.etSearch.setOnTouchListener(this);
    }

    private void setJobsData(ArrayList<JobResponseItem> jobs) {
        jobs.removeIf(Objects::isNull);
        JobsAdapter adapter = new JobsAdapter(jobs);
        binding.rvJobs.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.rvJobs.setLayoutManager(layoutManager);
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void showFilter() {
        if (isFilterOpen) {
            binding.rlFilter.setVisibility(View.GONE);
            binding.ivFilter.setImageResource(R.drawable.baseline_keyboard_arrow_up_24);
            isFilterOpen = false;
        } else {
            binding.rlFilter.setVisibility(View.VISIBLE);
            binding.ivFilter.setImageResource(R.drawable.baseline_keyboard_arrow_down_24);
            isFilterOpen = true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_filter:
                showFilter();
                break;
            case R.id.btn_filter:
                Boolean fulltimeIsChecked = binding.swFulltime.isChecked();
                String location = binding.etLocation.getText().toString();
                if (searchedvalue != null) {
                    mainViewModel.getJobsItem(searchedvalue, location, fulltimeIsChecked);
                } else {
                    mainViewModel.getJobsItem(null, location, fulltimeIsChecked);
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.et_search:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        binding.etSearch.setFocusable(true);
                        binding.etSearch.requestFocus();
                        binding.etSearch.setSelection(binding.etSearch.getText().length());
                        InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                        if(event.getRawX() >= binding.etSearch.getRight() - binding.etSearch.getTotalPaddingRight()) {
                            searchedvalue = binding.etSearch.getText().toString();
                            mainViewModel.getJobsItem(searchedvalue, null, null);
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        break;
                }
        }
        return true;
    }
}