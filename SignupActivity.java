package my.food.business_marketing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    //Declaring variables
    EditText edtfullname,edtemail,edtpassword,edtphone;
    CheckBox female,male;
    Button btnsignup;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    TextView t1;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.signupdemoo);

        //Initializing variables
        edtfullname=findViewById(R.id.fullname);
        edtemail=findViewById(R.id.email);
        edtpassword=findViewById(R.id.email);
        edtphone=findViewById(R.id.phone);
        female=findViewById(R.id.female);
        male=findViewById(R.id.male);

        btnsignup=findViewById(R.id.btnsignup);

        t1=findViewById(R.id.t1);
        textView3=findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                mAuth.signOut();
                Intent i=new Intent(getApplicationContext(),SignInDemoActivity.class);
                startActivity(i);
            }
        });



        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                String usernames=edtfullname.getText().toString().trim();
                String emails=edtemail.getText().toString().trim();
                String passwords=edtpassword.getText().toString().trim();
                String phoneNo=edtphone.getText().toString().trim();

                signup(emails,passwords);


            }
        });


    }

    public void signup(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                               // Signup successful, get user ID
                            FirebaseUser firebaseUser=mAuth.getCurrentUser();

                            String userId = mAuth.getCurrentUser().getUid();
                            Toast.makeText(SignupActivity.this,"User Created-"+userId,Toast.LENGTH_LONG).show();
                              // Do something with the user ID

                            String stringfullname=edtfullname.getText().toString();
                            String stringemail=edtemail.getText().toString();
                            String stringpassword=edtpassword.getText().toString();
                            String stringphone=edtphone.getText().toString();

                            User user=new User();
                            user.setFullname(stringfullname);
                            user.setEmail(stringemail);
                            user.setPassword(stringpassword);
                            user.setPhone(stringphone);
                            DatabaseReference mdatabase= FirebaseDatabase.getInstance().getReference();
                            mdatabase.child("User").child(userId).setValue(user);

                            if(firebaseUser !=null)
                            {
                               Intent i=new Intent(SignupActivity.this,BottomBarActivity.class);
                                startActivity(i);
                                finish();

                            }
                            else{

                            }


                        } else {
                            // Signup failed
                            Log.e("TAG", "Signup failed.", task.getException());
                            Toast.makeText(SignupActivity.this, "Error-" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        }});


    }}