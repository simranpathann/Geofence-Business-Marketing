package my.food.business_marketing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Business_Details extends AppCompatActivity {

    TextView name;
    TextView description;
    TextView contact;
    TextView timing;



    @Nullable

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.business_details,container,false);  // This means that the XML home file is converted into a corresponding set of view objects in memory.


        name=findViewById(R.id.name);
        description = findViewById(R.id.description);
        contact=findViewById(R.id.contact);
        timing=findViewById(R.id.timing);

        String businesname=getIntent().getStringExtra("name");
        String businesdescription=getIntent().getStringExtra("description");
        String businescontact=getIntent().getStringExtra("contact");
        String businestiming=getIntent().getStringExtra("timing");

        name.setText(businesname);
        description.setText(businesdescription);
        contact.setText(businescontact);
        timing.setText(businestiming);

        return rootview;
    }



}