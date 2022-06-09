package com.example.surgegym;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class TrainersFragment extends Fragment {

    private ImageView imgs;
    private TextView trainerDescText;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trainers, container, false)
                ;
        Spinner trainerSpinner = view.findViewById(R.id.trainerSpinner);
        context = getContext();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        trainerDescText = (TextView) view.findViewById(R.id.trainerDesc);
        imgs = (ImageView) view.findViewById(R.id.spinnerImgs);

        ArrayAdapter<CharSequence> trainerAdapter= ArrayAdapter.createFromResource(this.getActivity(), R.array.Trainers, android.R.layout.simple_spinner_item);
        trainerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        trainerSpinner.setAdapter(trainerAdapter);

        Spinner trainerDateSpinner = view.findViewById(R.id.trainersDateSpinner);
        Button bookBtn = (Button) view.findViewById(R.id.bookBtn);
        trainerDateSpinner.setVisibility(View.INVISIBLE);
        bookBtn.setVisibility(View.INVISIBLE);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "test test test", Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "My Notification");
                builder.setContentTitle("Successfully Booked");
                builder.setContentText(trainerSpinner.getSelectedItem().toString() + " @ " + trainerDateSpinner.getSelectedItem().toString());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
                managerCompat.notify(1, builder.build());
            }
        });

//        bookBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "My Notification");
//                builder.setContentTitle("Successfully Booked");
//                builder.setContentText(trainerSpinner.getSelectedItem().toString() + " @ " + trainerDateSpinner.getSelectedItem().toString());
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                builder.setAutoCancel(true);
//                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
//                managerCompat.notify(1, builder.build());
//            }
//        });

        //changing the picture and text based on selection
        trainerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = trainerSpinner.getSelectedItemPosition();
                switch (position){
                    case 1:
                        imgs.setImageResource(R.drawable.trainer_joey);
                        trainerDescText.setText("Specializing in cardio training. \n15 years experience\nDDO Branch");

                        bookBtn.setVisibility(View.VISIBLE);
                        trainerDateSpinner.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> trainerDateSpinnerAdapterJoey = ArrayAdapter.createFromResource(getActivity(), R.array.Joey, android.R.layout.simple_spinner_item);
                        trainerDateSpinnerAdapterJoey.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        trainerDateSpinner.setAdapter(trainerDateSpinnerAdapterJoey);
                        break;
                    case 2:
                        imgs.setImageResource(R.drawable.trainer_mel);
                        trainerDescText.setText("Specializing in strength training. \n5 years experience\nSt.Catherine Branch");

                        bookBtn.setVisibility(View.VISIBLE);
                        trainerDateSpinner.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> trainerDateSpinnerAdapterMelissa = ArrayAdapter.createFromResource(getActivity(), R.array.Melissa, android.R.layout.simple_spinner_item);
                        trainerDateSpinnerAdapterMelissa.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        trainerDateSpinner.setAdapter(trainerDateSpinnerAdapterMelissa);
                        break;
                    case 3:
                        imgs.setImageResource(R.drawable.trainer_adin);
                        trainerDescText.setText("Specializing in endurance and strength training.\n10 years experience\nVerdun Branch");

                        bookBtn.setVisibility(View.VISIBLE);
                        trainerDateSpinner.setVisibility(View.VISIBLE);
                        ArrayAdapter<CharSequence> trainerDateSpinnerAdapterAdin = ArrayAdapter.createFromResource(getActivity(), R.array.Adin, android.R.layout.simple_spinner_item);
                        trainerDateSpinnerAdapterAdin.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        trainerDateSpinner.setAdapter(trainerDateSpinnerAdapterAdin);
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