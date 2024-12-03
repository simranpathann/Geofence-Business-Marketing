package my.food.business_marketing;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Home extends Fragment {
RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
             View rootview=inflater.inflate(R.layout.home,container,false);  // This means that the XML home file is converted into a corresponding set of view objects in memory.
             recyclerView=rootview.findViewById(R.id.businesslist);                       //finds a view with an ID of "businesslist" within the inflated layout. This view is then assigned to a variable called recyclerView.So essentially, the purpose of this code is to find a specific view within an inflated layout so that it can be accessed and manipulated in code. In this case, the view being found is a RecyclerView with an ID of "businesslist".
             GetData("Business");
Diffbtnlatlong(18.23144,75.67499,18.23401,75.68744);


        return rootview;
    }
  //To get data from table to home
    public void GetData(String url) {
        try {
               ArrayList<Business> requestlist = new ArrayList<>();
               FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();//This code creates an ArrayList of Business objects called requestlist. It then sets up a FirebaseDatabase instance and retrieves a DatabaseReference object for the specified URL.
               DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference(url);
               mFirebaseDatabase.keepSynced(true);

               mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                  //  System.out.println("is req----------------" + dataSnapshot.toString());
                    for (DataSnapshot alert : dataSnapshot.getChildren()) {
                        Business business=new Business();
                             //  System.out.println("is key key----------------" + alert.getKey());
                            HashMap<String, Object> yourData = (HashMap<String, Object>)alert.getValue();
                            System.out.println("business name="+yourData.get("businessName"));
                            business.setBusinessName(yourData.get("businessName").toString());
                            business.setBusinessContact(yourData.get("businessContact").toString());
                            business.setBusinessContact(yourData.get("businessContact").toString());
                            business.setBusinessDescription(yourData.get("businessDescription").toString());
                            business.setBusinessLattitude(yourData.get("businessLattitude").toString());
                            business.setBusinessLongitude(yourData.get("businessLongitude").toString());
                            business.setBusinessWebsite(yourData.get("businessWebsite").toString());

                            requestlist.add(business);
                          // ShareItem sendSportReq = new ShareItem();
                          // sendSportReq.setItemName("" + yourData.get("itemname"));
                         // requestlist.add(sendSportReq);




                    }
                    System.out.println("array size===="+requestlist.size());
                      if(requestlist!=null && !requestlist.isEmpty()){
                        MyListAdapter adapter = new MyListAdapter(requestlist);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(adapter);
                                                                     }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }


            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    //For displaying the data to home page using RecyclerView using Adapter
    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
        private ArrayList<Business> arrayList;
        // RecyclerView recyclerView;


        public MyListAdapter(ArrayList<Business> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.business_list_row, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @SuppressLint("RecyclerView")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final Business myListData = arrayList.get(position);

            holder.business_name.setText(arrayList.get(position).getBusinessName());
            holder.business_description.setText(arrayList.get(position).getBusinessDescription());
            holder.contact_mail.setText(arrayList.get(position).getBusinessWebsite());
            holder.start_time.setText(arrayList.get(position).getBusinessTime());
            holder.end_time.setText(arrayList.get(position).getBusinessTime());


            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "click on item: " + myListData.getBusinessName(), Toast.LENGTH_LONG).show();
          //          Toast.makeText(view.getContext(), "difference:" + Diffbtnlatlong,Toast.LENGTH_SHORT).show();
                }
            });
            holder.navigate_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        double latitude = 37.7749;
                        double longitude = -122.4194;
                        String businessname = arrayList.get(position).getBusinessName();

                        latitude = Double.parseDouble(arrayList.get(position).getBusinessLattitude());
                        longitude = Double.parseDouble(arrayList.get(position).getBusinessLongitude());

                        String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + businessname + ")";
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        @Override
        public int getItemCount() {

            return arrayList.size();

        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            //Declaring the component in businesslistrow.xml
            public TextView business_name;
            TextView business_description;
            TextView contact_mail;
            public CardView card_view;
            TextView start_time;
            TextView end_time;
            Button navigate_button;

            public ViewHolder(View itemView) {     //constructor

                super(itemView);
                //Initialising the component in businesslistrow.xml

                navigate_button = itemView.findViewById(R.id.navigate_button);

                this.business_description = (TextView) itemView.findViewById(R.id.business_description);
                this.business_name = (TextView) itemView.findViewById(R.id.business_name);
                this.start_time = (TextView) itemView.findViewById(R.id.start_time);
                this.end_time = (TextView) itemView.findViewById(R.id.end_time);
                this.contact_mail = (TextView) itemView.findViewById(R.id.contact_mail);
                card_view = (CardView) itemView.findViewById(R.id.card_view);
            }
        }



    }


    public void  Diffbtnlatlong(double startLat, double startLang, double endLat, double endLang) {

        Location locStart = new Location("");
        locStart.setLatitude(startLat);
        locStart.setLongitude(startLang);

        Location locEnd = new Location("");
        locEnd.setLatitude(endLat);
        locEnd.setLongitude(endLang);
        System.out.println("distance======"+locStart.distanceTo(locEnd));
        String distance=""+locStart.distanceTo(locEnd);
       // Toast.makeText(getActivity(),distance,Toast.LENGTH_LONG).show();

    }

}
