package com.example.contacttracing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.contacttracing.databinding.ActivityContactDetailsBinding;
import com.example.contacttracing.model.AddContactActivity;
import com.example.contacttracing.model.Contact;

public class ContactDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public ActivityContactDetailsBinding binding;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Details");

        //get email as an intent to help in getting the contact to display
        Intent intent = getIntent();
        email = intent.getStringExtra("email");


        Contact contact = Contact.findByEmail(email);

        binding.contactNameDet.setText(contact.getName());
        binding.phoneNoDet.setText(contact.getPhoneNo());
        binding.emailDet.setText(contact.getEmail());

        binding.editBtn.setOnClickListener(this);
        binding.deleteBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == binding.editBtn){

            Intent intent = new Intent(ContactDetailsActivity.this, AddContactActivity.class);
            intent.putExtra("email", email);
            ContactDetailsActivity.this.startActivity(intent);

        }
        if (v == binding.deleteBtn){

            deleteContact();

        }
    }

    public  void deleteContact (){

        AlertDialog.Builder builder = new AlertDialog.Builder(ContactDetailsActivity.this);

        builder.setMessage("Are you sure you want to delete this contact? Press Ok if you would like to proceed.");

        builder.setPositiveButton("OK", (dialog, id) -> {

            Contact.delete(email);
            Intent intent = new Intent(ContactDetailsActivity.this, MainActivity.class);
            ContactDetailsActivity.this.startActivity(intent);

        });
        builder.setNegativeButton("CANCEL", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

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