package my.food.business_marketing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends Fragment {



    // Declare your UI elements
    private TextView welcome;
    private TextView userName;
    private TextView userEmail;
    private TextView phone;
    private TextView te;
    private TextView tc;

    Button btnlogout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.profile,container,false);

        btnlogout=rootview.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth=FirebaseAuth.getInstance();
                auth.signOut();
                Intent i=new Intent(getActivity(),Splash.class);
                startActivity(i);
            }
        });

        welcome=rootview.findViewById(R.id.Welcome);
        userName=rootview.findViewById(R.id.user_name);
        userEmail=rootview.findViewById(R.id.user_email);
        phone=rootview.findViewById(R.id.phone);
        te=rootview.findViewById(R.id.te);
        tc=rootview.findViewById(R.id.tc);

        // Retrieve user data from Firestore
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String urlf="User/"+user.getUid();
        GetData(urlf);

        return rootview;
    }


    //To get data from table to home
    public void GetData(String url) {
        try {
            System.out.println("called---------------");

            ArrayList<Business> requestlist = new ArrayList<>();
            FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();//This code creates an ArrayList of Business objects called requestlist. It then sets up a FirebaseDatabase instance and retrieves a DatabaseReference object for the specified URL.
            DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference(url);
            mFirebaseDatabase.keepSynced(true);

            mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                      System.out.println("is userdata----------------" + dataSnapshot.toString());



                    HashMap<String, Object> yourData = (HashMap<String, Object>)dataSnapshot.getValue();
                    System.out.println("business name="+yourData.get("phone"));
                    userName.setText(yourData.get("fullname").toString());
                    userEmail.setText(yourData.get("email").toString());
                    phone.setText(yourData.get("phone").toString());

//                    business.setBusinessName(yourData.get("businessName").toString());
//                    business.setBusinessContact(yourData.get("businessContact").toString());
//                    business.setBusinessTime(yourData.get("businessTime").toString());
//                    business.setBusinessDescription(yourData.get("businessDescription").toString());
//                    business.setBusinessLattitude(yourData.get("businessLattitude").toString());
//                    business.setBusinessLongitude(yourData.get("businessLongitude").toString());
//                    business.setBusinessWebsite(yourData.get("businessWebsite").toString());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("error---------------");

                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

  }
