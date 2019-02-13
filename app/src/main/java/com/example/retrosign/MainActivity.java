package com.example.retrosign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  private EditText editTextname;
  private EditText editUsername;
  private EditText editpassword;
  private EditText editemail;
  private Button buttonRegister;

  public static final String ROOT_URL="http://www.delaroystudios.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         editTextname=(EditText)findViewById(R.id.editTextname);
        editUsername=(EditText)findViewById(R.id.editUsername);
        editpassword=(EditText)findViewById(R.id.editpassword);
        editemail=(EditText)findViewById(R.id.editemail);
    }
private void insertUser(){
        buttonRegister=(Button)findViewById(R.id.register);
        RestAdapter adapter=new RestAdapter.Builder().setEndpoint(ROOT_URL).build();
        RegisterAPI api=adapter.create(RegisterAPI.class);
        api.insertUser(

                editTextname.getText().toString(),
                editUsername.getText().toString(),
    editpassword.getText().toString(),
    editemail.getText().toString(),




    new Callback<Response>(){
             @Override
             public void success(Response result, Response response){
                 BufferedReader reader=null;
                 String output="";

                 try{
                     reader=new BufferedReader(new InputStreamReader((result.getBody().in())));
                     output=reader.readLine();

                 }
                 catch (IOException e){
                     e.printStackTrace();
                 }
                 Toast.makeText(MainActivity.this,output,Toast.LENGTH_LONG).show();

             }
@Override
             public  void failure(RetrofitError error){
                 Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
}


    });
    }

    @Override
    public void onClick(View view) {
    insertUser();
    }
}
