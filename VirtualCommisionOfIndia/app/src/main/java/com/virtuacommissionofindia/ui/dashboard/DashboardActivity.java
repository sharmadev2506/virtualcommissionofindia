package com.virtuacommissionofindia.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mindinventory.midrawer.MIDrawerView;
import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.BaseActivity;
import com.virtuacommissionofindia.ui.dashboard.polling.PollingFragment;
import com.virtuacommissionofindia.ui.dashboard.voting.VotingFragment;
import com.virtuacommissionofindia.ui.dashboard.votingresult.VotingResultFragment;

import org.jetbrains.annotations.NotNull;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends BaseActivity implements View.OnClickListener/*VotingFragment.IVoteHost*/ {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    MIDrawerView drawerLayout;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.nav_voting)
    TextView navVoting;
    @BindView(R.id.nav_voting_results)
    TextView navVotingResults;
    @BindView(R.id.nav_polling)
    TextView navPolling;
    @BindView(R.id.nav_polling_results)
    TextView navPollingResults;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };
    @BindView(R.id.tv_headline)
    TextView tvHeadline;
    private int slideType = 0;
    private ActionBar actionbar;
    private Handler handler;
    private Web3j web3;

    @Override
    protected int getResourceId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        navPolling.setOnClickListener(this);
        navPollingResults.setOnClickListener(this);
        navVoting.setOnClickListener(this);
        navVotingResults.setOnClickListener(this);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_action_name);
        drawerLayout.setMIDrawerListener(new MIDrawerView.MIDrawerEvents() {
            @Override
            public void onDrawerOpened(@NotNull View view) {

            }

            @Override
            public void onDrawerClosed(@NotNull View view) {

            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                setUpWeb3jCode();
            }
        });



    }

    private void setUpWeb3jCode() {
        web3 = Web3j.build(new HttpService("http://172.20.10.6/"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                showToastLong("true");
                //Connected
            }
            else {
                showToastLong("false");
            }
        }
        catch (Exception e) {
            showToastLong(e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.nav_polling:
                tvHeadline.setVisibility(View.GONE);
                replaceFragment(R.id.frame, PollingFragment.getInstance(), PollingFragment.class.getSimpleName());
                avoidDoubleClicks(navPolling);
                actionbar.setTitle("Polling");
                updateSliderTypeEvents();
                break;
            case R.id.nav_polling_results:
                tvHeadline.setVisibility(View.GONE);
                replaceFragment(R.id.frame, VotingResultFragment.getInstance(), VotingResultFragment.class.getSimpleName());
                avoidDoubleClicks(navPollingResults);
                actionbar.setTitle("Polling Results");
                updateSliderTypeEvents();
                break;
            case R.id.nav_voting:
                tvHeadline.setVisibility(View.GONE);
                replaceFragment(R.id.frame, VotingFragment.getInstance(), VotingFragment.class.getSimpleName());
                avoidDoubleClicks(navVoting);
                actionbar.setTitle("Voting");
                updateSliderTypeEvents();
                break;
            case R.id.nav_voting_results:
                tvHeadline.setVisibility(View.GONE);
                replaceFragment(R.id.frame, VotingResultFragment.getInstance(), VotingResultFragment.class.getSimpleName());
                avoidDoubleClicks(navVotingResults);
                actionbar.setTitle("Voting Results");
                updateSliderTypeEvents();
                break;

        }
    }

    private void updateSliderTypeEvents() {
        drawerLayout.closeDrawer(GravityCompat.START);
       /* if (handler == null) {
            handler = new Handler();

            handler.postDelayed(runnable, 500);
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Avoid double click.
     */
    private void avoidDoubleClicks(final View view) {
        long DELAY_IN_MS = 900;
        if (!view.isClickable()) {
            return;
        }
        view.setClickable(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, DELAY_IN_MS);
    }

    /*@Override
    public void steerToGraph(int id) {

    }*/
}
