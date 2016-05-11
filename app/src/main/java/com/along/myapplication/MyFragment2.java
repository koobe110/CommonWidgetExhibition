package com.along.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment2 extends Fragment {
    public Mylistener mylistener;

    public MyFragment2() {
        // Required empty public constructor
    }

    public interface Mylistener {
        public void thank(String code);
    }

    @Override
    public void onAttach(Activity activity) {
        mylistener = (Mylistener) getActivity();
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textview_fragment);
        String text = getArguments().get("name")+" ";
        textView.setText(text);
        mylistener.thank("Fragment向Activity发送数据");
        return view;
    }

}
