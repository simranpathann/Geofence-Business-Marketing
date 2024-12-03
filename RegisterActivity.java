package my.food.business_marketing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    TextView Bwelcome;
    EditText BName;
    EditText BDescription;
    EditText BTime;
    EditText BContact;
    EditText BWebsite;
    EditText BLattitude;
    EditText BLongitude;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bwelcome=findViewById(R.id.Bwelcome);
        BName=findViewById(R.id.BName);
        BDescription=findViewById(R.id.BDescription);
        BTime=findViewById(R.id.BTime);
        BContact=findViewById(R.id.BContact);
        BWebsite=findViewById(R.id.BWebsite);
        BLattitude=findViewById(R.id.BLattitude);
        BLongitude=findViewById(R.id.BLongitude);
        btnadd=findViewById(R.id.btnadd);




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String BuisinessName=BName.getText().toString();
                String BuisinessDesc=BDescription.getText().toString();
                String BuisinessTime=BTime.getText().toString();
                String BuisinessContact=BContact.getText().toString();
                String BuisinessWebsite=BWebsite.getText().toString();
                String BuisinessLattitude=BLattitude.getText().toString();
                String BuisinessLongitude=BLongitude.getText().toString();

                Business business=new Business();
                business.setBusinessName(BuisinessName);
                business.setBusinessDescription(BuisinessDesc);
                business.setBusinessTime(BuisinessTime);
                business.setBusinessContact(BuisinessContact);
                business.setBusinessWebsite(BuisinessWebsite);
                business.setBusinessLattitude(BuisinessLattitude);
                business.setBusinessLongitude(BuisinessLongitude);

                DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
                String Bid=mDatabase.push().getKey();
                mDatabase.child("Business").child(Bid).setValue(business);

            }
        });

    }
}