package com.aimicai.ui.activity.chuangye;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimicai.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JujiaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JujiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JujiaFragment extends Fragment {

    public JujiaFragment() {
    }

    public static JujiaFragment newInstance() {
        JujiaFragment fragment = new JujiaFragment();
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
        return inflater.inflate(R.layout.fragment_jujia, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
