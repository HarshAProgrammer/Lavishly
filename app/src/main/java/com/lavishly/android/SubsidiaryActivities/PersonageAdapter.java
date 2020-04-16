package com.lavishly.android.SubsidiaryActivities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lavishly.android.R;

public class PersonageAdapter extends BaseAdapter {


    public Context mContext;
    public LayoutInflater personageLayoutInflator;
    private TextView titlePersonageTextView, descriptionPersonageTextView;
    public String[] titlePersonageArray;
    public String[] descriptionPersonageArray;
    private ImageView imageViewPersonage;


    @Override
    public int getCount() {
        return titlePersonageArray.length;
    }

    @Override
    public Object getItem(int personagePositionItem) {
        return titlePersonageArray[personagePositionItem];
    }

    @Override
    public long getItemId(int personagePositionItemId) {
        return personagePositionItemId;
    }

    @Override
    public View getView(int personagePosition, View personageConvertView, ViewGroup personageParent) {
        if (personageConvertView == null) {
            personageConvertView = personageLayoutInflator.inflate(R.layout.personage_single_item, null);
        }


        titlePersonageTextView = (TextView) personageConvertView.findViewById(R.id.tvTitlePersonage);
        descriptionPersonageTextView = (TextView) personageConvertView.findViewById(R.id.tvDescriptionPersonage);
        imageViewPersonage = (ImageView) personageConvertView.findViewById(R.id.ivPersonage);

        titlePersonageTextView.setText(titlePersonageArray[personagePosition]);
        descriptionPersonageTextView.setText(descriptionPersonageArray[personagePosition]);


        if (titlePersonageArray[personagePosition].equalsIgnoreCase("Powerful")) {
            imageViewPersonage.setImageResource(R.drawable.powerful_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Successful")) {
            imageViewPersonage.setImageResource(R.drawable.successful_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Rich")) {
            imageViewPersonage.setImageResource(R.drawable.rich_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Leader")) {
            imageViewPersonage.setImageResource(R.drawable.leader_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Valuable")) {
            imageViewPersonage.setImageResource(R.drawable.valuable_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Charismatic")) {
            imageViewPersonage.setImageResource(R.drawable.charismatic_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Wise")) {
            imageViewPersonage.setImageResource(R.drawable.wise_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Impressive")) {
            imageViewPersonage.setImageResource(R.drawable.impressive_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Fortunate")) {
            imageViewPersonage.setImageResource(R.drawable.fortunate_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Fulfilled")) {
            imageViewPersonage.setImageResource(R.drawable.fulfilled_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Healthy")) {
            imageViewPersonage.setImageResource(R.drawable.healthy_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Optimistic")) {
            imageViewPersonage.setImageResource(R.drawable.optimistic_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Blessed")) {
            imageViewPersonage.setImageResource(R.drawable.blessed_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Grateful")) {
            imageViewPersonage.setImageResource(R.drawable.grateful_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Generous")) {
            imageViewPersonage.setImageResource(R.drawable.generous_personage);

        } else if (titlePersonageArray[personagePosition].equalsIgnoreCase("Peaceful")) {
            imageViewPersonage.setImageResource(R.drawable.peaceful_personage);
            return personageConvertView;

        }

        return personageConvertView;

    }

    public PersonageAdapter(){

    }

    public PersonageAdapter(Context contextPersonage, String[] titlePersonage, String[] descriptionPersonage) {
        mContext = contextPersonage;
        titlePersonageArray = titlePersonage;
        descriptionPersonageArray = descriptionPersonage;
        personageLayoutInflator = LayoutInflater.from(contextPersonage);

    }





}
