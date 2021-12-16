package com.example.contacttracing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.contacttracing.adapters.ContactsRecyclerAdapter;
import com.example.contacttracing.databinding.ActivityContactDetailsBinding;
import com.example.contacttracing.databinding.ActivityMainBinding;
import com.example.contacttracing.model.AddContactActivity;
import com.example.contacttracing.model.Contact;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Home");

        setRecyclerView();
    }

    //set the recycler view and fill it with contacts views if saved contacts are available
    public void setRecyclerView(){
        if (Contact.contacts.isEmpty()){
            binding.emptyContacts.setVisibility(View.VISIBLE);
        }
        else {
            binding.emptyContacts.setVisibility(View.GONE);

            ContactsRecyclerAdapter adapter = new ContactsRecyclerAdapter(MainActivity.this, Contact.getContactsList());
            binding.contactsList.setAdapter(adapter);
            binding.contactsList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }

    //call setRecyclerview in the below methods to ensure that the list is updated each an every time an update is made
    @Override
    protected void onStart() {
        super.onStart();

        setRecyclerView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        setRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addContact) {
            //Start AddContact activity to add a new contact
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            intent.putExtra("email", Constant.CREATE_NEW);
            MainActivity.this.startActivity(intent);
            return true;
        }

        if (item.getItemId() == android.R.id.home){
                onBackPressed();
                return true;
        }

        return  super.onOptionsItemSelected(item);
    }

}