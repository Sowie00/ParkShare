package com.example.parkshare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int mi_id = mi.getItemId();
        switch (mi_id) {

            case R.id.settings:
                Log.d("Toolbar", "About Selected");
                //need to display the author’s name, Activity version number, and instructions for how to use the interface
                AlertDialog.Builder custom = new AlertDialog.Builder(MenuActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MenuActivity.this.getLayoutInflater();
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
    public void MyAccountClicked(View v) {
        Intent intent = new Intent(MenuActivity.this, AccountActivity.class);
        startActivity(intent);
    }
    public void MyPostingsClicked(View v) {
        Intent intent = new Intent(MenuActivity.this, MyPostingsActivity.class);
        startActivity(intent);
    }
    public void MyRentsClicked(View v) {
        Intent intent = new Intent(MenuActivity.this, CurrentPostingsActivity.class);
        startActivity(intent);
    }
    public void LogoutClicked(View v) {
        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}