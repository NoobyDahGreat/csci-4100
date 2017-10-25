package ca.uoit.csci4100u.lab_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by brad on 10/4/17.
 */

public class loginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void check(View source){
        EditText user = (EditText) findViewById(R.id.txtUser1);
        EditText pass = (EditText) findViewById(R.id.txtPass1);
        if (user.getText().toString().equals("brad")){
            if(pass.getText().toString().equals("100559945")) {
                Intent ret = new Intent();
                setResult(Activity.RESULT_OK,ret);
                finish();
            }
        }
        Log.i("About", pass.getText().toString());
        Log.i("About", user.getText().toString());
        Intent ret = new Intent();
        setResult(Activity.RESULT_CANCELED,ret);
        finish();
    }

}

