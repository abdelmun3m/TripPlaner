package com.android.abdelmun3m.tripplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Cards> cardsList;
    RecyclerView recyclerView;



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
                        4.000,
                        5.000,
                        "10-3-2018",
                        "done",
                        "one direction"
                ));
        cardsList.add(
                new Cards(2,
                        "My Trip",
                        4.000,
                        5.000,
                        "10-3-2018",
                        "done",
                        "one direction"
                ));

        CardsAdapter cardsAdapter = new CardsAdapter(this,cardsList);
        recyclerView.setAdapter(cardsAdapter);
    }
}
