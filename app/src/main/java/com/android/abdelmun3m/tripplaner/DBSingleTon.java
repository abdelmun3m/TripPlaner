package com.android.abdelmun3m.tripplaner; /**
 * Created by abdelmun3m on 23/03/18.
 */

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public final class DBSingleTon {

        private static final String TAG = DBSingleTon.class.getSimpleName();
        private static final String TRIP_KEY = "trips";
        private static FirebaseDatabase DBinsatnce;
        private static DatabaseReference DBreference;
        private static ValueEventListener ValueListener;
        public FirebaseAuth myAuth;
        public FirebaseAuth.AuthStateListener myAuthListener;
        public FirebaseUser User ;

        private  DBSingleTon(){
            //DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
            //DBreference = DBinsatnce.getReference() ; // getting the reference to the root of the data base
            //myAuth = FirebaseAuth.getInstance();
        }

        public static DatabaseReference GET_ROOT_DATABASE_REFERENCE(){

            if(DBreference == null){
                DBinsatnce = FirebaseDatabase.getInstance(); // get an instance from the database .
                DBreference = DBinsatnce.getReference();
            }
            return DBreference;
        }

        public static DatabaseReference GET_TRIP_REFERENCE(){
            return GET_ROOT_DATABASE_REFERENCE().child(TRIP_KEY);
        }


        public static String addTrip(Object trip){
            String id = GET_TRIP_REFERENCE().push().getKey();
            GET_TRIP_REFERENCE().push().setValue(trip);
            return id;
        }

        public static FirebaseUser GET_LOGGED_USER(Context context){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user == null){
                Intent i = new Intent(context,LoginActivty.class);
                context.startActivity(i);
            }
            return user;
        }

}
