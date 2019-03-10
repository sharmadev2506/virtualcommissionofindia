package com.virtuacommissionofindia.ui.dashboard.voting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VotingFragment extends BaseFragment {


    // private IVoteHost mVoteHost;
    private Unbinder unbinder;

    public static VotingFragment getInstance() {
        return new VotingFragment();
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
        View view = inflater.inflate(R.layout.fragment_voting, container, false);
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


}
