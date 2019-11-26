package com.example.urbs;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static java.lang.String.*;

public class ViewHolder extends RecyclerView.ViewHolder {

    View uHolder;

    public ViewHolder(@NonNull View itemView) {

        super(itemView);

        uHolder = itemView;

    }

        public void setuDetails(Context ctx, String Name , String Image , String Description  ){

        TextView uName = uHolder.findViewById(R.id.urbs_head);
        TextView uDescription = uHolder.findViewById(R.id.editText);
        ImageView uImage = uHolder.findViewById(R.id.uCardImage);

        uName.setText(Name);
        uDescription.setText(Description);
        Picasso.get().load(Image).into(uImage);

    }


}
