package com.lavishly.android.SubsidiaryActivities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> firstTitles = new ArrayList<>();
    private final ArrayList<Fragment> pages = new ArrayList<>();
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        FragmentOneFancy fragmentOneFancy = new FragmentOneFancy();
        FragmentTwoPersonage fragmentTwoPersonage = new FragmentTwoPersonage();
        return pages.get(position);
    }

    @Override
    public int getCount() {

        return pages.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).toString();
    }

    public void addPage(Fragment fragment,String title){

        pages.add(fragment);
        firstTitles.add(title);
    }





}
