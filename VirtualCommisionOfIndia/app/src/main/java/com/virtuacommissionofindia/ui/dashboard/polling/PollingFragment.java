package com.virtuacommissionofindia.ui.dashboard.polling;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PollingFragment extends BaseFragment {


    /*
        @BindView(R.id.tv_text)
        AppCompatTextView tvText;
        @BindView(R.id.thumbs_up)
        LottieAnimationView thumbsUp;
        @BindView(R.id.thumbs_down)
        LottieAnimationView thumbsDown;
        // private IVoteHost mVoteHost;*/
    private Unbinder unbinder;

    public static PollingFragment getInstance() {
        return new PollingFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof IVoteHost) {
            mVoteHost = (IVoteHost) context;
        } else
            throw new IllegalStateException("Host must implement IVoteHost");*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_polling, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /*@OnClick({R.id.thumbs_up, R.id.thumbs_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.thumbs_up:
                thumbsDown.cancelAnimation();
                thumbsUp.playAnimation();
                break;
            case R.id.thumbs_down:
                thumbsDown.playAnimation();
                thumbsUp.cancelAnimation();
                break;
        }
    }*/
}