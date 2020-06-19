package com.lavishly.android.SubsidiaryActivities.ActivityAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lavishly.android.R;

public class RecyclerAdapterPersonage extends RecyclerView.Adapter<RecyclerAdapterPersonage.PersonageViewHolder> {

    private Context mContext;
    private String[] titlePersonageArray;
    private String[] descriptionPersonageArray;
    private TextView titlePersonage, descriptionPersonage;
    private ImageView imageViewPersonage;



    public LayoutInflater personageLayoutInflator;


    public RecyclerAdapterPersonage(Context mContext, TextView titlePersonage, TextView descriptionPersonage,ImageView imageViewPersonage) {
        this.mContext = mContext;
        this.titlePersonage = titlePersonage;
        this.descriptionPersonage = descriptionPersonage;
        this.imageViewPersonage = imageViewPersonage;
    }

    @NonNull
    @Override
    public PersonageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.personage_single_item, parent, false);
        PersonageViewHolder vHolder = new PersonageViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonageViewHolder holder, int position) {



        holder.titlePersonage.setText(titlePersonageArray[position]);
        holder.descriptionPersonage.setText(descriptionPersonageArray[position]);


        if (titlePersonageArray[position].equalsIgnoreCase("Powerful")) {
            imageViewPersonage.setImageResource(R.drawable.powerful_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Successful")) {
            imageViewPersonage.setImageResource(R.drawable.successful_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Rich")) {
            imageViewPersonage.setImageResource(R.drawable.rich_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Leader")) {
            imageViewPersonage.setImageResource(R.drawable.leader_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Valuable")) {
            imageViewPersonage.setImageResource(R.drawable.valuable_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Charismatic")) {
            imageViewPersonage.setImageResource(R.drawable.charismatic_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Wise")) {
            imageViewPersonage.setImageResource(R.drawable.wise_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Impressive")) {
            imageViewPersonage.setImageResource(R.drawable.impressive_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Fortunate")) {
            imageViewPersonage.setImageResource(R.drawable.fortunate_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Fulfilled")) {
            imageViewPersonage.setImageResource(R.drawable.fulfilled_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Healthy")) {
            imageViewPersonage.setImageResource(R.drawable.healthy_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Optimistic")) {
            imageViewPersonage.setImageResource(R.drawable.optimistic_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Blessed")) {
            imageViewPersonage.setImageResource(R.drawable.blessed_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Grateful")) {
            imageViewPersonage.setImageResource(R.drawable.grateful_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Generous")) {
            imageViewPersonage.setImageResource(R.drawable.generous_personage);

        } else if (titlePersonageArray[position].equalsIgnoreCase("Peaceful")) {
            imageViewPersonage.setImageResource(R.drawable.peaceful_personage);


        }


    }

    @Override
    public int getItemCount() {
        return titlePersonageArray.length;
    }

    public static class PersonageViewHolder extends RecyclerView.ViewHolder {
        private TextView titlePersonage, descriptionPersonage;
        private ImageView imageViewPersonage;

        public PersonageViewHolder(View itemView) {
            super(itemView);

            titlePersonage = (TextView) itemView.findViewById(R.id.tvTitlePersonage);
            descriptionPersonage = (TextView) itemView.findViewById(R.id.tvDescriptionPersonage);
            imageViewPersonage = (ImageView) itemView.findViewById(R.id.ivPersonage);

        }
    }


}
