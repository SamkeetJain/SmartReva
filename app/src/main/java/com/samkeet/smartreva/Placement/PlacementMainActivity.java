package com.samkeet.smartreva.Placement;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.samkeet.smartreva.R;

public class PlacementMainActivity extends AppCompatActivity {

    public static final String[] navItems={"Upcoming Drives","Grades","Profile","About Us"};
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MiniDrawer miniResult = null;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
//                .withHeaderBackground(R.drawable.header)
                /// TODO: 19-Oct-16
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDrawerLayout(R.layout.crossfade_councling_material_drawer)
                .withHasStableIds(true)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(navItems[0]).withIcon(FontAwesome.Icon.faw_inbox).withIdentifier(1),
                        new PrimaryDrawerItem().withName(navItems[1]).withIcon(MaterialDesignIconic.Icon.gmi_open_in_new).withIdentifier(2),
                        new PrimaryDrawerItem().withName(navItems[2]).withIcon(FontAwesome.Icon.faw_user).withIdentifier(3),
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_fourth).withIcon(FontAwesome.Icon.faw_eye).withIdentifier(4),
                        new DividerDrawerItem(),
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_fifth).withIcon(FontAwesome.Icon.faw_android).withIdentifier(5),
                        new PrimaryDrawerItem().withName(navItems[3]).withIcon(FontAwesome.Icon.faw_wikipedia_w).withIdentifier(4)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2) {

                        }
                        if (drawerItem.getIdentifier() == 3) {
                            Intent intent = new Intent(getApplicationContext(), Profile.class);
                            startActivity(intent);
                        }
                        if (drawerItem.getIdentifier() == 4) {

                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();
        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));
        //add second view (which is the miniDrawer)
        MiniDrawer miniResult = result.getMiniDrawer();
        //build the view for the MiniDrawer
        View view = miniResult.build(this);
        //set the background of the MiniDrawer as this would be transparent
//        view.setBackgroundColor(Color.BLACK);
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));
        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });

        //hook to the crossfade event
        crossfadeDrawerLayout.withCrossfadeListener(new CrossfadeDrawerLayout.CrossfadeListener() {
            @Override
            public void onCrossfade(View containerView, float currentSlidePercentage, int slideOffset) {
                //Log.e("CrossfadeDrawerLayout", "crossfade: " + currentSlidePercentage + " - " + slideOffset);
            }
        });


    }

    public void BackButton (View v){
        finish();
    }

    public void Item1(View v){
        Intent intent = new Intent(this,DriveManagement.class);
        intent.putExtra("TYPE","ONE");
        startActivity(intent);

    }

    public void Item2(View v){
        Intent intent = new Intent(this,DriveManagement.class);
        intent.putExtra("TYPE","TWO");
        startActivity(intent);

    }

}
