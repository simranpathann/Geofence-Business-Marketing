package my.food.business_marketing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    TextView t1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        t1=findViewById(R.id.t1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent is used to switch from one activity to another.


                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                // User is signed in, now using intent naviage user to home screen
                    Intent i = new Intent(Splash.this, BottomBarActivity.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.
                } else
                {
                 // No user is signed in , now using intent navigate user to login screen
                    Intent i = new Intent(Splash.this, SignInDemoActivity.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.
                }

            }
        }, 5000);
    }
}
