package com.example.contacttracing.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.contacttracing.AddContactFragment;
import com.example.contacttracing.R;
import com.example.contacttracing.databinding.ActivityAddContactBinding;
import com.example.contacttracing.databinding.ActivityMainBinding;

public class AddContactActivity extends AppCompatActivity {

    //Activity used for only launching the AddContactFragment
    private ActivityAddContactBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Add Contact");

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        Fragment fragment = AddContactFragment.newInstance(email, AddContactActivity.this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_container, fragment,"AddContactFragment");
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return  super.onOptionsItemSelected(item);
    }
}