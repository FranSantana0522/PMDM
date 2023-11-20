package com.example.fragmentcomunicacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnControlesFragmentListener mListener;

    public ControlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnControlesFragmentListener){
            mListener=(OnControlesFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlesFragment newInstance(String param1, String param2) {
        ControlesFragment fragment = new ControlesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_controles, container, false);
        Button color=(Button) view.findViewById(R.id.buttonColor);
        Button texto=(Button) view.findViewById(R.id.buttonTexto);

        color.setOnClickListener(View->{
            mListener.botonColorCliked("Rojo");
        });
        texto.setOnClickListener(View->{
            mListener.botonTextoClicked("Hola usuario");
        });


        return  view;
    }

}