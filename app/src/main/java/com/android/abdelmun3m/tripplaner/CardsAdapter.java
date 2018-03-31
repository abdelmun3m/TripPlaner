package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by lamiaa on 24/03/18.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{

    private Context myCtx;
    private List<Cards> CardsList;

    public CardsAdapter(Context myCtx, List<Cards> cardsList) {
        this.myCtx = myCtx;
        this.CardsList = cardsList;
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {

        final Cards card = CardsList.get(position);
        holder.tripName.setText(card.getTripName());
        holder.tripstrPoint.setText(String.valueOf(card.getSrtpoint()));
        holder.tripendPoint.setText(String.valueOf(card.getEndpoint()));
        holder.tripDateTime.setText(card.getDateTime());
        holder.tripStatus.setText(card.getTripstatus());
        holder.tripDirStatus.setText(card.getRootStatus());


        //Start trip Button
        holder.startTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String srtpoint = String.valueOf(card.getSrtpoint());
                String endpoint = String.valueOf(card.getEndpoint());

                String ur="";
                final String uri = "http://maps.google.com/maps?daddr=";
                ur= uri.concat(srtpoint + "+to:"+endpoint);
                final Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(ur));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                myCtx.startActivity(intent);

                Toast.makeText(myCtx, ""+ur, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return CardsList.size();
    }

    public class CardsViewHolder extends RecyclerView.ViewHolder{

        TextView tripName , tripstrPoint , tripendPoint , tripDateTime , tripStatus , tripDirStatus;
        //ImageView deleteCard;
        public Button startTripBtn;

        public CardsViewHolder(View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.tripName);
            tripstrPoint = itemView.findViewById(R.id.tripstrPoint);
            tripendPoint = itemView.findViewById(R.id.tripendPoint);
            tripDateTime = itemView.findViewById(R.id.tripDateTime);
            tripStatus = itemView.findViewById(R.id.tripStatus);
            tripDirStatus = itemView.findViewById(R.id.tripDirStatus);

            startTripBtn = itemView.findViewById(R.id.launchTrip);

        }
    }
    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new CardsViewHolder(view);

    }

}
