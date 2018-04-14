package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements CardsAdapter.TripActionListener{

    ArrayList<trips> cardsList;
    RecyclerView recyclerView;
    CardsAdapter cardsAdapter;
    Button startTripBtn;
    ImageView addTripIcon;
    ValueEventListener tripsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(DBSingleTon.GET_LOGGED_USER(this) == null){
            finish();
        }
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardsAdapter = new CardsAdapter(MainActivity.this,cardsList);
        cardsAdapter.setTripListenr(this);
        recyclerView.setAdapter(cardsAdapter);
        addTripIcon = findViewById(R.id.addBtn);
        addTripIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , AddTripActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
           tripsListener = DBSingleTon.GET_TRIP_REFERENCE().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    cardsList = new ArrayList<>();
                    Toast.makeText(MainActivity.this, ""+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        trips temp = ds.getValue(trips.class);
                        temp.id = ds.getKey();
                        cardsList.add(temp);
                      //Log.i("test1",ds.getValue(trips.class).getStartPlace().latitude+"");
                    }
                    cardsAdapter.updateCArdList(cardsList);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onTripDelete(trips trip) {
        DBSingleTon.GET_TRIP_REFERENCE().child(trip.id).removeValue();
    }

    @Override
    public void onTripEdit(trips trip) {
        Intent intent = new Intent(this , AddTripActivity.class);
        intent.putExtra(AddTripActivity.EDIT_TRIP_KEY,trip);
        startActivity(intent);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(tripsListener != null){
            DBSingleTon.GET_TRIP_REFERENCE().removeEventListener(tripsListener);
        }

    }

    @Override
    public void onTripStart(trips trip) {
        String ur="";
        Toast.makeText(this,
                trip.getStartPlace().latitude+","+
                        trip.getStartPlace().longitude +"&"
                        +trip.getEndPlace().latitude+","
                        +trip.getEndPlace().longitude , Toast.LENGTH_LONG).show();
        final String uri = "https://www.google.com/maps/dir/?api=1&map_action=pano&origin=";
        ur= uri.concat(
                trip.getStartPlace().latitude+"%2C"+trip.getStartPlace().longitude+
                        "&destination="+
                        trip.getEndPlace().latitude+"%2C"+trip.getEndPlace().longitude
        );
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ur));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");
        startActivity(intent);
    }
}
