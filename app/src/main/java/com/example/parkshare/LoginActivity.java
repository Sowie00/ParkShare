package com.example.parkshare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button createButton;
    private EditText editloginText;
    private EditText editPassText;
    private DBHelper dbh;
    private SQLiteDatabase database;
    public static Integer ActiveID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbh = new DBHelper(this);
        loginButton = (Button)findViewById(R.id.LoginButton);
        editloginText = (EditText)findViewById(R.id.LoginEditText);
        editPassText = (EditText)findViewById(R.id.PasswordEditText);
        createButton = (Button)findViewById(R.id.createbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                
                database = dbh.getWritableDatabase();
                String user = editloginText.getText().toString();
                String pass = editPassText.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please don't leave any fields blank", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkpass = checkpass(user, pass);
                    if (checkpass==true) {
                        Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Cursor cursor = database.rawQuery("Select _id from user_table where Email = ?", new String[]{user});
                        cursor.moveToFirst();
                        ActiveID = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                        Log.i("ActiveID", String.valueOf(ActiveID));
                        Intent intent;
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect password or username!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent;
                intent = new Intent(LoginActivity.this, AccountCreationActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    public Boolean checkpass(String username, String password) {
        database = dbh.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from user_table where Email = ? and Password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int mi_id = mi.getItemId();
        switch (mi_id) {

            case R.id.settings:
                Log.d("Toolbar", "About Selected");
                //need to display the author’s name, Activity version number, and instructions for how to use the interface
                AlertDialog.Builder custom = new AlertDialog.Builder(LoginActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_help, null);
                custom.setView(view);

                custom.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                AlertDialog customdialog = custom.create();
                custom.show();
                break;

        }
        return true;
    }
}