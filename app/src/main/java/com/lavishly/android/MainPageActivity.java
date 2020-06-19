package com.lavishly.android;

import android.app.ActionBar;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;


import android.os.Build;
import android.os.Bundle;



import android.view.View;

import android.widget.ImageView;
import android.widget.ListView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import com.facebook.AccessToken;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lavishly.android.SubsidiaryActivities.FancyActivity;
import com.lavishly.android.SubsidiaryActivities.FragmentOneFancy;
import com.lavishly.android.SubsidiaryActivities.FragmentTwoPersonage;
import com.lavishly.android.SubsidiaryActivities.PersonageActivity;
import com.lavishly.android.SubsidiaryActivities.SectionsPagerAdapter;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


import java.util.Calendar;


public class MainPageActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    public String PhoneNo;
    public String Password;
    public ImageView ProfilePic;


    private FirebaseAuth mAuth;
    private String ShareOnFacebookString;
    private String ShareOnTwitterString;
    private String ShareOnGooglePlusString;


    private ViewPager viewPagerMainPage;
    private TabLayout tabLayoutMainPage;


    private AccessToken accessToken;


    private Toolbar toolbar;
    private ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();


        setupUIViews();
        initToolbar();
        setupViewPager();
        setupNavigationDrawer();
        DailyFancyNotification();


    }

    @Override
    public void onBackPressed() {
        showExitAlertDialogueBox();

    }


    private void setupUIViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbarMainPage);
        tabLayoutMainPage = (TabLayout) findViewById(R.id.tbLMainPage);
        viewPagerMainPage = (ViewPager) findViewById(R.id.vpMainPage);

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lavishly");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupViewPager() {


        addPages(viewPagerMainPage);


        tabLayoutMainPage.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayoutMainPage.setupWithViewPager(viewPagerMainPage);
        tabLayoutMainPage.addOnTabSelectedListener(this);


        tabLayoutMainPage.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.md_black_1000));


        //set The Tab index and set Text,icon etc
        tabLayoutMainPage.getTabAt(0).setIcon(R.drawable.fancy_main);
        tabLayoutMainPage.getTabAt(1).setIcon(R.drawable.personage_main);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(3);

    }

    private void addPages(ViewPager viewPagerMainPage) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addPage(new FragmentOneFancy(), "Fancy");
        sectionsPagerAdapter.addPage(new FragmentTwoPersonage(), "Personage");
        viewPagerMainPage.setAdapter(sectionsPagerAdapter);


    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPagerMainPage.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    //Start Notification
    private void FancyNotification() {

        NotificationManager FancyNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.BigPictureStyle FancyNotificationStyle = new NotificationCompat.BigPictureStyle();
        FancyNotificationStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                R.drawable.fancy_main)).build();


        Intent FancyNotificationResultIntent = new Intent(this, FancyActivity.class);
        FancyNotificationResultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent FancyNotificationPendingResult = PendingIntent.getActivity(this, (int) Calendar.getInstance().getTimeInMillis(), FancyNotificationResultIntent, 0);


        //build Notification
        NotificationCompat.Builder FancyNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Fancy")
                .setContentText("Zoom Into The Depths Of Your Life.")
                .setStyle(FancyNotificationStyle)
                .addAction(R.drawable.upward_right_arrow, "Open App", FancyNotificationPendingResult);

        FancyNotificationManager.notify(0, FancyNotificationBuilder.build());


    }


    private void DailyFancyNotification() {
        AlarmManager DailyFancyNotificationAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar DailyFancyCalender = Calendar.getInstance();
        DailyFancyCalender.set(Calendar.HOUR_OF_DAY, 11);
        DailyFancyCalender.set(Calendar.MINUTE, 11);
        DailyFancyCalender.set(Calendar.SECOND, 00);

        Intent DailyFancyNotificationIntent = new Intent(getApplicationContext(), NotificationReceiverActivity.class);

        PendingIntent DailyFancyNotificationPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, DailyFancyNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        DailyFancyNotificationAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, DailyFancyCalender.getTimeInMillis()
                , AlarmManager.INTERVAL_DAY,
                DailyFancyNotificationPendingIntent);
    }

    private void PersonageNotification() {

        NotificationCompat.BigPictureStyle PersonageNotificationStyle = new NotificationCompat.BigPictureStyle();
        PersonageNotificationStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                R.drawable.personage_main)).build();
        NotificationManager PersonageNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent PersonageNotificationResultIntent = new Intent(this, PersonageActivity.class);
        PersonageNotificationResultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent PersonageNotificationPendingResult = PendingIntent.getActivity(this, (int) Calendar.getInstance().getTimeInMillis(), PersonageNotificationResultIntent, 0);


        //build Notification
        NotificationCompat.Builder PersonageNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Personage")
                .setContentText("Become Your Best Version.")
                .setStyle(PersonageNotificationStyle)
                .addAction(R.drawable.upward_right_arrow, "Open App", PersonageNotificationPendingResult);

        PersonageNotificationManager.notify(0, PersonageNotificationBuilder.build());


    }
    //End Notification


    //Start NavigationDrawer
    private void setupNavigationDrawer() {
        // Create the AccountHeader

        FirebaseUser user = mAuth.getCurrentUser();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        String displayName;
        String displayEmail;
        if (user != null) {
            displayName = user.getDisplayName().toString().trim();
            displayEmail = user.getEmail().toString().trim();
        } else {
            displayName = "Lavishly";
            displayEmail = "Version 1.0";
        }



        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(displayName).withEmail(displayEmail).withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withIdentifier(1).withName("Share on Facebook");
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Share on Twitter");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(1).withName("Share on Google+");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withName("Share");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(1).withName("Like Us On Facebook");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(1).withName("Favourites");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(1).withName("SignOut");
        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(2).withName("Contact Us");
        SecondaryDrawerItem item8 = new SecondaryDrawerItem().withIdentifier(2).withName("About Us");


//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item0,
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        new DividerDrawerItem(),
                        item7,
                        item8

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position) {
                            case 1:
                                ShareOnFacebook();
                                break;
                            case 2:
                                ShareOnTwitter();
                                break;
                            case 3:
                                ShareOnGooglePlus();
                                break;
                            case 4:
                                GeneralShareMain();
                                break;
                            case 5:
                                LikeFacebookPage();
                                break;
                            case 6:
                                break;
                            case 7:
                                SignOut();
                                break;
                            case 8:
                                openDeveloperInfo();
                                break;
                            case 9:
                                openAboutUs();
                                break;


                        }
                        return true;
                    }
                })
                .build();
    }


    private void ShareOnGooglePlus() {
        try {
            Intent shareOnGooglePlusIntent = new Intent(Intent.ACTION_SEND);
            shareOnGooglePlusIntent.setType("text/plain");
            final String appPackageName = getApplicationContext().getPackageName();
            ShareOnGooglePlusString = "https://play.google.com/store/apps/details?id=" + appPackageName;
            shareOnGooglePlusIntent.putExtra(Intent.EXTRA_TEXT, "Lavishly:Built For The Rich  " +
                    "" + ShareOnGooglePlusString);
            shareOnGooglePlusIntent.setPackage("com.google.android.apps.plus");
            startActivity(shareOnGooglePlusIntent);
        } catch (Exception GooglePlusException) {
            Snackbar mainPageShareOnGooglePlusFailureSnackBar = Snackbar.make(findViewById(R.id.activity_main_page), "Install the Google+ App", Snackbar.LENGTH_LONG);
            mainPageShareOnGooglePlusFailureSnackBar.show();
        }
    }

    private Intent LikeFacebookPage() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/Lavishly-App-190884858288784/"));

        } catch (Exception LikeFacebookException) {

            return new Intent(Intent.ACTION_SEND,
                    Uri.parse("https://www.facebook.com/Lavishly-App-190884858288784/"));
        }

    }


    private void ShareOnTwitter() {
        try {
            Intent shareOnTwitterIntent = new Intent(Intent.ACTION_SEND);
            shareOnTwitterIntent.setType("text/plain");
            final String appPackageName = getApplicationContext().getPackageName();
            ShareOnTwitterString = "https://play.google.com/store/apps/details?id=" + appPackageName;
            shareOnTwitterIntent.putExtra(Intent.EXTRA_TEXT, "Lavishly:Built For The Rich   " +
                    "" + ShareOnTwitterString);
            shareOnTwitterIntent.setPackage("com.twitter.android");
            startActivity(shareOnTwitterIntent);
        } catch (Exception TwitterException) {
            Snackbar mainPageShareOnTwitterFailureSnackBar = Snackbar.make(findViewById(R.id.activity_main_page), "Install the Twitter App", Snackbar.LENGTH_LONG);
            mainPageShareOnTwitterFailureSnackBar.show();
        }
    }

    private void ShareOnFacebook() {
        try {
            Intent shareOnFacebookIntent = new Intent(Intent.ACTION_SEND);
            shareOnFacebookIntent.setType("text/plain");
            final String appPackageName = getApplicationContext().getPackageName();
            ShareOnFacebookString = "https://play.google.com/store/apps/details?id=  " +
                    "" + appPackageName;
            shareOnFacebookIntent.putExtra(Intent.EXTRA_TEXT, ShareOnFacebookString);
            shareOnFacebookIntent.setPackage("com.facebook.katana");
            startActivity(shareOnFacebookIntent);
        } catch (Exception FacebookException) {
            Snackbar mainPageShareOnFacebookFailureSnackBar = Snackbar.make(findViewById(R.id.activity_main_page), "Install the Facebook App", Snackbar.LENGTH_LONG);
            mainPageShareOnFacebookFailureSnackBar.show();
        }
    }


    private void GeneralShareMain() {

        Intent GeneralShareMainIntent = new Intent(Intent.ACTION_SEND);
        final String appPackageName = getApplicationContext().getPackageName();
        String appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;


        GeneralShareMainIntent.setType("Text/plain");
        String generalMainShareBody = "Lavishly:Built For The Rich   " +
                "" +
                appLink;
        String generalMainShareSub = "Lavishly:A Status Symbol";
        GeneralShareMainIntent.putExtra(Intent.EXTRA_SUBJECT, generalMainShareSub);
        GeneralShareMainIntent.putExtra(Intent.EXTRA_TEXT, generalMainShareBody);
        startActivity(Intent.createChooser(GeneralShareMainIntent, "Share Via"));


    }

    private void SignOut() {
        mAuth.signOut();
        finish();
        Intent openLoginActivityFromMainPage = new Intent(MainPageActivity.this, LoginActivity.class);
        startActivity(openLoginActivityFromMainPage);
    }


    private void openDeveloperInfo() {
        Intent openDevelopersActivityFromMainPage = new Intent(MainPageActivity.this, Developers.class);
        startActivity(openDevelopersActivityFromMainPage);
    }

    private void openAboutUs() {

    }

    //End Navigation Drawer
    private void showExitAlertDialogueBox() {
        final AlertDialog.Builder exitBuilder = new AlertDialog.Builder(MainPageActivity.this);
        exitBuilder.setCancelable(false);
        exitBuilder.setTitle("Exit");
        exitBuilder.setMessage("Are You Sure You Want To Leave?");
        exitBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface exitDialog, int which) {
                finish();
            }
        });
        exitBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface exitDialog, int which) {
                exitDialog.dismiss();
            }
        });
        exitBuilder.create().show();
    }


}



