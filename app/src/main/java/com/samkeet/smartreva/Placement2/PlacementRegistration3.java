package com.samkeet.smartreva.Placement2;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.samkeet.smartreva.R;

public class PlacementRegistration3 extends AppCompatActivity {

    public LinearLayout mainlayout;
    public String type = "Btech";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_registration3);
        mainlayout = (LinearLayout) findViewById(R.id.placement_registration3);


        if (type.equals("Btech")) {
            setBtechView();
        }
    }

    public void setBtechView() {
        int Dp10 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int Dp15 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
        int Dp3 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());


        LinearLayout one = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams one_params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        one.setLayoutParams(one_params);
        one.setOrientation(LinearLayout.VERTICAL);

        CardView one_card = new CardView((getApplicationContext()));
        CardView.LayoutParams one_card_params = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT,CardView.LayoutParams.WRAP_CONTENT);
        one_card.setLayoutParams(one_card_params);
        one_card.setBackgroundColor(Color.parseColor("#ffffff"));
        one_card.setUseCompatPadding(true);

        LinearLayout one_card_lay = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams one_card_lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        one_card_lay.setLayoutParams(one_card_lay_params);
        one_card_lay.setOrientation(LinearLayout.VERTICAL);

        TextView one_card_lay_tv = new TextView(getApplicationContext());
        one_card_lay_tv.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
        one_card_lay_tv.setPadding(Dp15,Dp10,0,0);
        one_card_lay_tv.setTextSize(16);

//        View one_card_lay_v = new View(getApplicationContext());
//        one_card_lay_v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
//        //one_card_lay_v.setBackgroundColor(Integer.parseInt("#e0e0e0"));

        TextInputLayout one_card_lay_til1 = new TextInputLayout(getApplicationContext());
        one_card_lay_til1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        one_card_lay_til1.setPadding(Dp3,Dp3,Dp3,Dp3);

        EditText one_card_lay_til1_editt = new EditText((getApplicationContext()));
        one_card_lay_til1_editt.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        one_card_lay_til1_editt.setTextSize(12);
        one_card_lay_til1_editt.setHint("School Name");

//        TextInputLayout one_card_lay_til2 = new TextInputLayout(getApplicationContext());
//        one_card_lay_til2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//        one_card_lay_til2.setPadding(Dp3,Dp3,Dp3,Dp3);
//
//        EditText one_card_lay_til2_editt = new EditText((getApplicationContext()));
//        one_card_lay_til2_editt.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        one_card_lay_til2_editt.setTextSize(12);
//        one_card_lay_til2_editt.setHint("Percentsge");
//
//        TextInputLayout one_card_lay_til3 = new TextInputLayout(getApplicationContext());
//        one_card_lay_til3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
//        one_card_lay_til3.setPadding(Dp3,Dp3,Dp3,Dp3);
//
//        EditText one_card_lay_til3_editt = new EditText((getApplicationContext()));
//        one_card_lay_til3_editt.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        one_card_lay_til3_editt.setTextSize(12);
//        one_card_lay_til3_editt.setHint("Year of Passing");

        one_card_lay_til1.addView(one_card_lay_til1_editt);
        //one_card_lay_til2.addView(one_card_lay_til2_editt);
        //one_card_lay_til3.addView(one_card_lay_til3_editt);
        one_card_lay.addView(one_card_lay_til1);
        //one_card_lay.addView(one_card_lay_til2);
        //one_card_lay.addView(one_card_lay_til3);
        one_card_lay.addView(one_card_lay_tv);
        //one_card_lay.addView(one_card_lay_v);
        one_card.addView(one_card_lay);
        one.addView(one_card);


    }

    public void BackButton(View v) {
        finish();
    }

}
