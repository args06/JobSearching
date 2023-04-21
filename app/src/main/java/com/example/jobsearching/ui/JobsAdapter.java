package com.example.jobsearching.ui;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jobsearching.R;
import com.example.jobsearching.data.model.JobResponseItem;
import com.example.jobsearching.databinding.JobItemBinding;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private final ArrayList<JobResponseItem> items;

    public JobsAdapter(ArrayList<JobResponseItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public JobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        JobItemBinding binding = JobItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvJobName.setText(items.get(position).getTitle());
        holder.tvCompanyName.setText(items.get(position).getCompany());
        holder.tvLocation.setText(items.get(position).getLocation());
        Glide.with(holder.itemView.getContext()).load(items.get(position).getCompanyLogo())
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivCompanyImage);
        holder.cvJob.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.JOB_EXTRA, items.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJobName, tvCompanyName, tvLocation;
        private ImageView ivCompanyImage;
        private CardView cvJob;
        public ViewHolder(@NonNull JobItemBinding binding) {
            super(binding.getRoot());

            tvJobName = binding.tvJobName;
            tvCompanyName = binding.tvCompanyName;
            tvLocation = binding.tvLocation;
            ivCompanyImage = binding.ivProfileImage;
            cvJob = binding.cvJob;
        }
    }
}
