package com.example.lenovo.safecabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
    public void doSignUp(View v){
        EditText editText = (EditText) findViewById(R.id.signUp_email);
        EditText editText1 = (EditText) findViewById(R.id.signUp_pswd);
        EditText editText2 = (EditText) findViewById(R.id.signUp_confirmpswd);
        EditText editText3 = (EditText) findViewById(R.id.signUp_name);
        String email = editText.getText().toString();
        String pass = editText1.getText().toString();
        String name = editText3.getText().toString();
        String cpass = editText2.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(name) || TextUtils.isEmpty(cpass) ) {

            if (TextUtils.isEmpty(email)) {
                editText.setError("Please Enter Email");
            }
            if (TextUtils.isEmpty(pass)) {
                editText1.setError("Please Enter Password");
            }
            if(TextUtils.isEmpty(name)){
                editText3.setError("Please Enter Name");
            }
            if (TextUtils.isEmpty(cpass)){
                editText2.setError("Please Confirm Password");
            }
        }
        else {
            if (TextUtils.equals(pass, cpass)) {
                Contact c = new Contact();
                c.setName(name);
                c.setEmail(email);
                c.setPass(pass);
                helper.insertContact(c);

                Toast.makeText(SignUp.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
            else{
                Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_LONG).show();
            }
        }

    }
}
