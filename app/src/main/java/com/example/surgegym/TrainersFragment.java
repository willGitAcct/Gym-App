package com.example.surgegym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class TrainersFragment extends Fragment {

    private ImageView imgs;
    private TextView trainerDescText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainers, container, false);
        Spinner trainerSpinner = view.findViewById(R.id.trainerSpinner);
        //Spinner bookPackage = view.findViewById(R.id.bookSpinner);
        trainerDescText = (TextView) view.findViewById(R.id.trainerDesc);
        imgs = (ImageView) view.findViewById(R.id.spinnerImgs);
        ArrayAdapter<CharSequence> trainerAdapter= ArrayAdapter.createFromResource(this.getActivity(), R.array.Trainers, android.R.layout.simple_spinner_item);
        trainerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        trainerSpinner.setAdapter(trainerAdapter);

        //changing the picture and text based on selection
        trainerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int position = trainerSpinner.getSelectedItemPosition();
                    switch (position){
                        case 1:
                            imgs.setImageResource(R.drawable.trainer_joey);
                            trainerDescText.setText("Specializing in cardio training\n15 years experience\n");
                            break;
                        case 2:
                            imgs.setImageResource(R.drawable.trainer_mel);
                            trainerDescText.setText("Specializing in strength training.\n5 years experience\n");
                            break;
                        case 3:
                            imgs.setImageResource(R.drawable.trainer_adin);
                            trainerDescText.setText("Specializing in endurance and strength training.\n10 years experience\n");
                            break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }); // end listener


        return view;
    }
}