package ca.uoit.csci4100u.lab_03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class lab03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab03);
    }

    public void onAbout(View view){
        Intent aboutInt = new Intent(this, aboutActivity.class);
        startActivity(aboutInt);
    }

    public void onLogin(View view){
        Intent loginInt = new Intent(this, loginActivity.class);
        startActivityForResult(loginInt,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(getBaseContext(),"CORRECT",
                        Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(),"WRONG",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


}
