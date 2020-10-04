package com.example.diliverymanage;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button GG,Apply_btn,Adminbtn,DiliveryBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GG = (Button) findViewById(R.id.btn_gg) ;

        GG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, driverlogin.class);
                startActivity(intent);

            }
        });
        Apply_btn = (Button) findViewById(R.id.apply_dirver);

        Apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, driverApplication.class);
                startActivity(intent);

            }
        });
        Adminbtn = (Button) findViewById(R.id.adminbtn);

        Adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Manage_drivers.class);
                startActivity(intent);

            }
        });

        DiliveryBtn = (Button) findViewById(R.id.dili_btn) ;

        DiliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dilivery_details.class);
                startActivity(intent);

            }
        });

    }

}