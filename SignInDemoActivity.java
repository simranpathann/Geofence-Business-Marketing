package my.food.business_marketing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInDemoActivity extends AppCompatActivity {
    EditText edtusername;
    EditText edtpassword;
    Button btnlogin;
    TextView textView;
    TextView textView2;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signindemo);

        edtusername=findViewById(R.id.edtusername);
        edtpassword=findViewById(R.id.edtpassword);
        btnlogin=findViewById(R.id.btnlogin);

        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email=edtusername.getText().toString();
               String password=edtpassword.getText().toString();
               singInWithEmail(email,password);

//                Intent i1=new Intent(getApplicationContext(), BottomBarActivity.class);
//                startActivity(i1);
            }
        });
    }
    public void singInWithEmail(String email,String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();
                            Toast.makeText(SignInDemoActivity.this, "Login Successfully ."+uid, Toast.LENGTH_SHORT).show();
                                            Intent i1=new Intent(getApplicationContext(), BottomBarActivity.class);
                                            startActivity(i1);
                        }
                        else {
                            Toast.makeText(SignInDemoActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                    }


                    }
    });
}


    }


