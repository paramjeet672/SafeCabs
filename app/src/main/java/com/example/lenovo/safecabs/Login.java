package com.example.lenovo.safecabs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void doLogin(View v) {
        EditText editText = (EditText) findViewById(R.id.login_email);
        EditText editText1 = (EditText) findViewById(R.id.login_pswd);
        String email = editText.getText().toString();
        String pass = editText1.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {

            if (TextUtils.isEmpty(email)) {
                editText.setError("Please Enter Email");
            }
            if (TextUtils.isEmpty(pass)) {
                editText1.setError("Please Enter Password");
            }
        } else {
            String password = helper.searchPass(email);
            if (pass.equals(password)) {
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_LONG).show();

            }

        }
    }
}
