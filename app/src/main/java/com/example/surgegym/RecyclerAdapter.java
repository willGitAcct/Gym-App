package com.example.surgegym;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    String[] data;
    //ImageView[] images;
    int[] images; // infact the easiest way is strangely with an int array

    //this supports opening a different activity when clicking a picture
//    public interface ClickedActivityFunction {
//        void onClickedPic(int index);
//    }
//    ClickedActivityFunction clicked;


    public RecyclerAdapter(Context context, String[] data, int[] images){
        this.context = context;
        this.data = data;
        this.images = images;
        //this.clicked = clicked;
        // this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_partners, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(data[position]);
        holder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newPosition = getAdapterPosition();
                    //clicked.onClickedPic(newPosition);
                    //Toast.makeText(context, "clicked on this "+data[newPosition], Toast.LENGTH_SHORT).show();


                }
            });

            textView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageViews);
        }
    }



}
