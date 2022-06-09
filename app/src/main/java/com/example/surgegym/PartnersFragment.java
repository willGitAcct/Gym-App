package com.example.surgegym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PartnersFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String[] foodNames = {"COPPER BRANCH", "DECATHLON", "MARCHE TAU", "SPORTS EXPERTS","GNC", "ATMOSPHERE", };
    int[] images = {R.drawable.partner_copper, R.drawable.partner_decathlon, R.drawable.partner_marche_tau, R.drawable.partner_sports_experts, R.drawable.partner_gnc, R.drawable.partner_atmos, };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_partners, container, false);


        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerAdapter = new RecyclerAdapter(getContext(), foodNames, images);
        recyclerView.setAdapter(recyclerAdapter);
       return view;
    }
}