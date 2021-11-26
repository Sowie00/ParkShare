package com.example.parkshare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

public class AccountCreationActivity extends AppCompatActivity {

    private Button loginButton;
    private Button createButton;
    private EditText editFirstNameText;
    private EditText editLastNameText;
    private EditText editloginText;
    private EditText editPhoneText;
    private EditText editPassText;
    private DBHelper dbh;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        dbh = new DBHelper(this);
        createButton = (Button)findViewById(R.id.LoginButton);
        editFirstNameText = (EditText)findViewById(R.id.FirstNameEditText);
        editLastNameText = (EditText)findViewById(R.id.LastNameEditText);
        editloginText = (EditText)findViewById(R.id.LoginEditText);
        editPassText = (EditText)findViewById(R.id.PasswordEditText);
        editPhoneText = (EditText)findViewById(R.id.PhoneEditText);



        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String user = editloginText.getText().toString();
                String pass = editPassText.getText().toString();
                String first = editFirstNameText.getText().toString();
                String last = editLastNameText.getText().toString();
                String phone = editPhoneText.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(AccountCreationActivity.this, "Username and password fields cannot be blank!",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = checkUser(user);
                    if (checkUser == false) {
                        Boolean insert = insertData(user, pass, first, last, phone);
                        if (insert == true) {
                            Toast.makeText(AccountCreationActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent intent;
                            intent = new Intent(AccountCreationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AccountCreationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(AccountCreationActivity.this, "Account with this username already exists!", Toast.LENGTH_SHORT).show();
                    }

               }
            }
        });
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    public boolean checkUser(String username) {
        database = dbh.getWritableDatabase();
        //Cursor cursor = database.rawQuery("Select * from ? where ? = ?", new String[]{DBHelper.TABLE_NAME, DBHelper.COLUMN_EMAIL, username});
        Cursor cursor = database.rawQuery("Select * from user_table where Email = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean insertData(String username, String password, String first, String last, String phone) {
        database = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Email", username);
        values.put("Password", password);
        values.put("First_Name", first);
        values.put("Last_Name", last);
        values.put("Phone_num", phone);
        long result = database.insert(DBHelper.TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int mi_id = mi.getItemId();
        switch (mi_id) {

            case R.id.settings:
                Log.d("Toolbar", "About Selected");
                //need to display the author’s name, Activity version number, and instructions for how to use the interface
                AlertDialog.Builder custom = new AlertDialog.Builder(AccountCreationActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = AccountCreationActivity.this.getLayoutInflater();
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
