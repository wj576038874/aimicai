package com.aimicai.ui.activity.chuangye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FuzhuangFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FuzhuangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuzhuangFragment extends Fragment {

    public FuzhuangFragment() {
    }

    public static FuzhuangFragment newInstance() {
        FuzhuangFragment fragment = new FuzhuangFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fuzhuang, container, false);
    }
}
