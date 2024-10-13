package com.example.starsproject.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starsproject.R;
import com.example.starsproject.beans.Star;
import com.example.starsproject.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {
    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;
    private NewFilter mfilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.stars = stars;
        this.context = context;
        this.starsFilter = new ArrayList<>(stars);
        this.mfilter = new NewFilter();
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.star_item, viewGroup, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.idss);

                Bitmap bitmap = ((BitmapDrawable)((ImageView)v.findViewById(R.id.img)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar)v.findViewById(R.id.stars)).getRating());
                idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Rate this Star")
                        .setMessage("Give a rating between 1 and 5:")
                        .setView(popup)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float newRating = bar.getRating();
                                int starId = Integer.parseInt(idss.getText().toString());

                                Star star = StarService.getInstance().findById(starId);
                                star.setStar(newRating);
                                StarService.getInstance().update(star);

                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();

                dialog.show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder starViewHolder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(starsFilter.get(position).getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(starViewHolder.img);
        starViewHolder.name.setText(starsFilter.get(position).getName().toUpperCase());
        starViewHolder.stars.setRating(starsFilter.get(position).getStar());
        starViewHolder.idss.setText(String.valueOf(starsFilter.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }

    public static class StarViewHolder extends RecyclerView.ViewHolder {
        TextView idss;
        ImageView img;
        TextView name;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    private class NewFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            starsFilter.clear();
            FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                starsFilter.addAll(stars);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Star star : stars) {
                    if (star.getName().toLowerCase().startsWith(filterPattern)) {
                        starsFilter.add(star);
                    }
                }
            }
            results.values = starsFilter;
            results.count = starsFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
