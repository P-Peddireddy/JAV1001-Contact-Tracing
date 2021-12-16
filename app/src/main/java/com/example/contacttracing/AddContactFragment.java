package com.example.contacttracing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.contacttracing.model.Contact;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class AddContactFragment extends Fragment implements View.OnClickListener {


    private static final String ARG_EMAIL = "email";


    private String mEmail;
    private TextInputEditText mContactNameEdit, mPhoneNoEdit, mEmailEdit;
    private TextInputLayout mEmailLayout;
    private Button mAddBtn;

    private static Context mContext;

    public AddContactFragment() {
        // Required empty public constructor
    }


    public static AddContactFragment newInstance(String email, Context context) {
        AddContactFragment fragment = new AddContactFragment();
        Bundle args = new Bundle();
        mContext = context;
        args.putString(ARG_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmail = getArguments().getString(ARG_EMAIL);
        }
        else {
            mEmail = Constant.CREATE_NEW;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_contact, container, false);

        mContactNameEdit = view.findViewById(R.id.contactName_edit);
        mPhoneNoEdit = view.findViewById(R.id.phoneNo_edit);
        mEmailEdit = view.findViewById(R.id.email_edit);
        mAddBtn = view.findViewById(R.id.add_btn);
        mEmailLayout = view.findViewById(R.id.email_layout);

        //checks if the user is editing or adding a new contact then displays respective views, that is prefill the forms if the user is editing
        if (!mEmail.equals(Constant.CREATE_NEW)){
            setEditView();
        }

        mAddBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mAddBtn){
            addContact();
        }
    }


    //prefill the form inputs for editing of contact and hide the email field to prevent the user from editing since it is used as unique ID.
    public void setEditView(){
        Contact contact = Contact.findByEmail(mEmail);

        mContactNameEdit.setText(contact.getName());
        mPhoneNoEdit.setText(contact.getPhoneNo());
        mEmailEdit.setText(contact.getEmail());

        mEmailEdit.setVisibility(View.GONE);
        mEmailLayout.setVisibility(View.GONE);
    }

    public boolean validateInputs(String name, String phone, String email){
        if (name==null||name.trim().isEmpty()) {
            mContactNameEdit.setError("This field is required");
            return false;
        }
        else  if (phone==null||phone.trim().isEmpty()) {
            mPhoneNoEdit.setError("This field is required");
            return false;
        }
        else  if (email==null||email.trim().isEmpty()) {
            mEmailEdit.setError("This field is required");
            return false;
        }
        else {
            return  true;
        }
    }

    public void addContact(){
        String email = mEmailEdit.getText().toString();
        String name = mContactNameEdit.getText().toString();
        String phone = mPhoneNoEdit.getText().toString();

        boolean isValidInputs = validateInputs(name, phone, email);

        if(!isValidInputs){
            validateInputs(name, phone, email);
        }
        else {

            if (!mEmail.equals(Constant.CREATE_NEW)){
                Contact contact = new Contact(name, mEmail, phone);
                Toast.makeText(getContext(), "Contact edited successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("email", contact.getEmail());
                getContext().startActivity(intent);
            }
            else {
                Contact contact = new Contact(name, email, phone);
                Toast.makeText(getContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("email", contact.getEmail());
                getContext().startActivity(intent);
            }

        }
    }
}