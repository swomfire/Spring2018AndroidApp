package day01.swomfire.restaurantapp;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    public final String ORDERED_TAB = "ORDERED_TAB";
    public final String ORDERING_TAB = "ORDERING_TAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTabHost tabHost = findViewById(R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        setNewTab(this,
                tabHost,
                ORDERING_TAB,
                getResources().getString(R.string.ordering_tab_title));

        setNewTab(this,
                tabHost,
                ORDERED_TAB,
                getResources().getString(R.string.ordered_tab_title));

        tabHost.setOnTabChangedListener(tabId -> {
            for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                TextView tv = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tvTabTitle);
                tv.setTextColor(getColor(R.color.colorComTabTextUnselected));
            }

            TextView tv = tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(R.id.tvTabTitle);
            tv.setTextColor(getColor(R.color.colorComTabTextSelected));
        });
    }

    private void setNewTab(Context ctx, FragmentTabHost tabHost, String tag, String title) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(ctx, title));
        tabHost.addTab(tabSpec, ComTabFragment.class, null);
    }

    private View getTabIndicator(Context ctx, String title) {
        View view = View.inflate(this, R.layout.tab_layout_order_detail, null);
        TextView tvTitle = view.findViewById(R.id.tvTabTitle);
        tvTitle.setText(title);
        return view;
    }

}
