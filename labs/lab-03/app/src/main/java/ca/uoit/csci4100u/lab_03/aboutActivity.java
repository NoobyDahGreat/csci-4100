package ca.uoit.csci4100u.lab_03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by brad on 10/18/17.
 */

public class aboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
    public void finish(View Current){
        finish();
    }
}
