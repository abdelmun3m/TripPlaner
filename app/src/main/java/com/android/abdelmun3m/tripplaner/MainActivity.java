package com.android.abdelmun3m.tripplaner;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<Cards> cardsList;
    RecyclerView recyclerView;

    Button startTripBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardsList = new ArrayList<>();



      cardsList.add(
                new Cards(1,
                        "My Trip",
                        "Gachibowli",
                        "kothaguda",
                        "10-3-2018",
                        "done",
                        "one direction"
                ));
        cardsList.add(
                new Cards(2,
                        "My Trip",
                        "12.972442,77.580643",
                        "12.9103,77.645",
                        "10-3-2018",
                        "done",
                        "one direction"
                ));

        CardsAdapter cardsAdapter = new CardsAdapter(MainActivity.this,cardsList);
        recyclerView.setAdapter(cardsAdapter);

    }
}
