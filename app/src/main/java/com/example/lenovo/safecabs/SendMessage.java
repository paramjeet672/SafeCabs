package com.example.lenovo.safecabs;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.katepratik.msg91api.MSG91;

import org.w3c.dom.Text;


public class SendMessage extends AppCompatActivity {
    String text3;
    MSG91 message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Intent i = getIntent();
        String text1 = i.getStringExtra("result");
        text3 = i.getStringExtra("add");

        TextView textView = (TextView) findViewById(R.id.scanresult);
        textView.setText(text1);
        TextView textView1 = (TextView) findViewById(R.id.location);
        textView1.setText(text3);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_message, menu);
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
    public void sendMessage(View v){
        EditText guardian = (EditText) findViewById(R.id.guardianNumber);
        Intent i = getIntent();
        String text1 = i.getStringExtra("result");
        EditText source1 = (EditText) findViewById(R.id.source);
        String source = source1.getText().toString();
        EditText dest1 = (EditText) findViewById(R.id.destination);
        String dest = dest1.getText().toString();
        EditText mess1 = (EditText) findViewById(R.id.message1);
        String mess = mess1.getText().toString();
        String text2 = "Travelling From: " + source + "\r\nTravelling To: " + dest + "\r\nMessage: " + mess +  "\r\nCab Details: " + text1 + "\r\n" + "Current Location: " + text3;
        message = new MSG91("155516AQ8nmQ5Lr8ab593929d7", true);
        String guardianNumber = guardian.getText().toString();
        message.setRoute("4");
        message.composeMessage("SFCABS",text2);
        message.to(guardianNumber);
        String status = message.send();
        Toast.makeText(SendMessage.this, "Message Sent", Toast.LENGTH_LONG).show();
        TextView textView = (TextView) findViewById(R.id.scanresult);
        textView.setText(text2);
        Intent intent1 = new Intent(SendMessage.this, MainActivity.class);
        startActivity(intent1);
    }
}
