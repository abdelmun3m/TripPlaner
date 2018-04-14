package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lamiaa on 24/03/18.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{

    private Context myCtx;
    private ArrayList<trips> CardsList;
    private TripActionListener myTripListener ;

    public CardsAdapter(Context myCtx, ArrayList<trips> cardsList) {
        this.myCtx = myCtx;
        this.CardsList = cardsList;
    }

    public void setTripListenr(TripActionListener delegate ){
        this.myTripListener = delegate;
    }
    @Override
    public void onBindViewHolder(CardsViewHolder holder, final int position) {

        final trips currentTrip = CardsList.get(position);
        holder.tripName.setText(currentTrip.getTripName());
        holder.tripstrPoint.setText(currentTrip.getStartPlace().sName);
        holder.tripendPoint.setText(currentTrip.getEndPlace().sName);
        holder.tripDateTime.setText(currentTrip.getTripDate());
        holder.tripStatus.setText(currentTrip.getTripStatus());
        holder.tripDirStatus.setText(currentTrip.getRootStatus());

        //Start trip Button
        holder.startTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myTripListener.onTripStart(currentTrip);

            }
        });

        //edit card
       holder.editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myTripListener.onTripEdit(currentTrip);
            }
        });


       //delete card
        holder.deletCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTripListener.onTripDelete(currentTrip);
            }
        });


    }

    @Override
    public int getItemCount() {

        return CardsList!=null? CardsList.size():0;
    }

    public class CardsViewHolder extends RecyclerView.ViewHolder{

        TextView tripName , tripstrPoint , tripendPoint , tripDateTime , tripStatus , tripDirStatus;
        //ImageView deleteCard;
        public Button startTripBtn;
        public ImageView startImage , deletCard , editCard;
        public CardsViewHolder(View itemView) {
            super(itemView);
            tripName = itemView.findViewById(R.id.tripName);
            tripstrPoint = itemView.findViewById(R.id.tripstrPoint);
            tripendPoint = itemView.findViewById(R.id.tripendPoint);
            tripDateTime = itemView.findViewById(R.id.tripDateTime);
            tripStatus = itemView.findViewById(R.id.tripStatus);
            tripDirStatus = itemView.findViewById(R.id.tripDirStatus);
            startTripBtn = itemView.findViewById(R.id.launchTrip);
            editCard = itemView.findViewById(R.id.editCard);
            deletCard = itemView.findViewById(R.id.deletCard);



        }

    }
    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new CardsViewHolder(view);

    }

    public void updateCArdList(ArrayList mylist){
        this.CardsList = mylist;
        notifyDataSetChanged();
    }



    public interface TripActionListener{

        void onTripDelete(trips trip);
        void onTripEdit(trips trip);
        void onTripStart(trips trip);

    }
}
