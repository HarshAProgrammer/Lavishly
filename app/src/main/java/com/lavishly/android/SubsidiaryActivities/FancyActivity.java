package com.lavishly.android.SubsidiaryActivities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lavishly.android.R;

public class FancyActivity extends AppCompatActivity {


    private ListView fancyListView;
    public static SharedPreferences sharedFancyPreferences;
    public static String selectedFancyItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy);

        setupFancyUIViews();
        setupListViewFancy();
    }

    private void setupFancyUIViews() {

        fancyListView = (ListView) findViewById(R.id.lvFancy);
        sharedFancyPreferences = getSharedPreferences("FancyItem", MODE_PRIVATE);

    }

    private void setupListViewFancy() {
        String[] titleFancy = getResources().getStringArray(R.array.TitleArrayFancy);
        String[] descriptionFancy = getResources().getStringArray(R.array.DescriptionArrayFancy);

        FancyAdapter fancyAdapter = new FancyAdapter(FancyActivity.this, titleFancy, descriptionFancy);
        fancyListView.setAdapter(fancyAdapter);

        fancyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> fancyParent, View view, int fancyPositionItemClick, long id) {
                switch (fancyPositionItemClick) {
                    case 0:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Motivation").apply();
                        break;

                    case 1:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Energy").apply();
                        break;
                    case 2:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Courage").apply();
                        break;
                    case 3:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Cheerfulness").apply();
                        break;
                    case 4:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Excitement").apply();
                        break;
                    case 5:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Confidence").apply();
                        break;
                    case 6:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Positivity").apply();
                        break;
                    case 7:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Happiness").apply();
                        break;
                    case 8:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Love").apply();
                        break;
                    case 9:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Prosperity").apply();
                        break;
                    case 10:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Charm").apply();
                        break;
                    case 11:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Peace").apply();
                        break;
                    case 12:
                        sharedFancyPreferences.edit().putString(selectedFancyItem, "Relaxation").apply();
                        break;
                    default:
                        Snackbar errorSnackBar = Snackbar.make(findViewById(R.id.activity_fancy), "Something went wrong.Please Try Again Later", Snackbar.LENGTH_LONG);
                        errorSnackBar.show();


                }


            }
        });


    }

    class FancyAdapter extends BaseAdapter {


        private Context mContext;
        private LayoutInflater fancyLayoutInflater;
        private TextView titleFancy, descriptionFancy;
        private String[] titleFancyArray;
        private String[] descriptionFancyArray;
        private ImageView imageViewFancy;

        public FancyAdapter(Context contextFancy, String[] titleFancy, String[] descriptionFancy) {
            mContext = contextFancy;
            titleFancyArray = titleFancy;
            descriptionFancyArray = descriptionFancy;
            fancyLayoutInflater = LayoutInflater.from(contextFancy);
        }

        @Override
        public int getCount() {
            return titleFancyArray.length;
        }

        @Override
        public Object getItem(int fancyPositionItem) {
            return titleFancyArray[fancyPositionItem];
        }

        @Override
        public long getItemId(int fancyPositionItemId) {
            return fancyPositionItemId;
        }

        @Override
        public View getView(int fancyPosition, View fancyConvertView, ViewGroup fancyParent) {
            if (fancyConvertView == null) {
                fancyConvertView = fancyLayoutInflater.inflate(R.layout.fancy_single_item, null);
            }

            titleFancy = (TextView) fancyConvertView.findViewById(R.id.tvTitleFancy);
            descriptionFancy = (TextView) fancyConvertView.findViewById(R.id.tvDescriptionFancy);
            imageViewFancy = (ImageView) fancyConvertView.findViewById(R.id.ivFancy);

            titleFancy.setText(titleFancyArray[fancyPosition]);
            descriptionFancy.setText(descriptionFancyArray[fancyPosition]);


            if (titleFancyArray[fancyPosition].equalsIgnoreCase("Motivation")) {
                imageViewFancy.setImageResource(R.drawable.motivation_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Energy")) {
                imageViewFancy.setImageResource(R.drawable.energy_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Courage")) {
                imageViewFancy.setImageResource(R.drawable.courage_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Cheerfulness")) {
                imageViewFancy.setImageResource(R.drawable.cheerfulness_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Excitement")) {
                imageViewFancy.setImageResource(R.drawable.excitement_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Confidence")) {
                imageViewFancy.setImageResource(R.drawable.confidence_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Positivity")) {
                imageViewFancy.setImageResource(R.drawable.positivity_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Happiness")) {
                imageViewFancy.setImageResource(R.drawable.happiness_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Love")) {
                imageViewFancy.setImageResource(R.drawable.love_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Prosperity")) {
                imageViewFancy.setImageResource(R.drawable.prosperity_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Charm")) {
                imageViewFancy.setImageResource(R.drawable.charm_fancy);

            } else if (titleFancyArray[fancyPosition].equalsIgnoreCase("Peace")) {
                imageViewFancy.setImageResource(R.drawable.peace_fancy);

            } else {
                imageViewFancy.setImageResource(R.drawable.relaxation_fancy);

            }
            return fancyConvertView;

        }

        @SuppressLint("ResourceType")
        private void FancyDialogueInit() {
            Dialog FancyDialogue = new Dialog(mContext);
            FancyDialogue.setContentView(R.id.diaBoxFancy);
            ShimmerFrameLayout containerShimmerFancyDialogue = (ShimmerFrameLayout) FancyDialogue.findViewById(R.id.shimmer_view_container_diaBoxFancy);
            TextView dialogueTitleFancy = (TextView) FancyDialogue.findViewById(R.id.tvTitleFancyDialogue);
            TextView dialogueDescriptionFancy = (TextView) FancyDialogue.findViewById(R.id.tvDescriptionFancyDialogue);
            ImageView dialogueImageFancy = (ImageView) FancyDialogue.findViewById(R.id.ivImageFancyDialogue);
            Button dialogueButtonPayFancy = (Button) FancyDialogue.findViewById(R.id.btnPayFancyDialogue);
            Button dialogueButtonFavouriteFancy = (Button) FancyDialogue.findViewById(R.id.btnFavouriteFancyDialogue);
            dialogueTitleFancy.setText(titleFancy.getText());
            dialogueDescriptionFancy.setText(descriptionFancy.getText());
            dialogueImageFancy.setImageDrawable(imageViewFancy.getDrawable());
            containerShimmerFancyDialogue.startShimmer();

        }


    }


}
