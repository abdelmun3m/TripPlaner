package com.android.abdelmun3m.tripplaner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lamiaa on 24/03/18.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsViewHolder>{
//usedto inflate layout
    private Context myCtx;
    private List<Cards> CardsList;

    public CardsAdapter(Context myCtx, List<Cards> cardsList) {
        this.myCtx = myCtx;
        this.CardsList = cardsList;
    }

    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(myCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
        Cards card = CardsList.get(position);

        holder.tripName.setText(card.getTripName());
        holder.tripstrPoint.setText(String.valueOf(card.getSrtpoint()));
        holder.tripendPoint.setText(String.valueOf(card.getEndpoint()));
        holder.tripDateTime.setText(card.getDateTime());
        holder.tripStatus.setText(card.getTripstatus());
        holder.tripDirStatus.setText(card.getRootStatus());



    }

    @Override
    public int getItemCount() {
        return CardsList.size();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder{

        TextView tripName , tripstrPoint , tripendPoint , tripDateTime , tripStatus , tripDirStatus;
        public CardsViewHolder(View itemView) {
            super(itemView);

            tripName = itemView.findViewById(R.id.tripName);
            tripstrPoint = itemView.findViewById(R.id.tripstrPoint);
            tripendPoint = itemView.findViewById(R.id.tripendPoint);
            tripDateTime = itemView.findViewById(R.id.tripDateTime);
            tripStatus = itemView.findViewById(R.id.tripStatus);
            tripDirStatus = itemView.findViewById(R.id.tripDirStatus);


        }
    }
}
