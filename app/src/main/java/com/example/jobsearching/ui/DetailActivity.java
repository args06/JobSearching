package com.example.jobsearching.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.jobsearching.R;
import com.example.jobsearching.data.model.JobResponseItem;
import com.example.jobsearching.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    static final String JOB_EXTRA = "job_extra";
    JobResponseItem job;

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        job = getIntent().getParcelableExtra(JOB_EXTRA);

        Glide.with(getApplicationContext()).load(job.getCompanyLogo())
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivCompanyImage);
        binding.tvCompanyName.setText(job.getCompany());
        binding.tvLocation.setText(job.getLocation());
        binding.tvWebsite.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(job.getCompanyUrl()));
            startActivity(i);
        });
        binding.tvJobTitle.setText(job.getTitle());
        binding.tvJobStatus.setText(job.getType());
        binding.tvJobDesc.setText(job.getDescription());

    }
}