package com.example.contacttracing.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contacttracing.Constant;
import com.example.contacttracing.ContactDetailsActivity;
import com.example.contacttracing.MainActivity;
import com.example.contacttracing.R;
import com.example.contacttracing.model.AddContactActivity;
import com.example.contacttracing.model.Contact;

import java.util.List;

public class ContactsRecyclerAdapter  extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {
    private List<Contact> mContacts;
    private Context mContext;

    public ContactsRecyclerAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mContacts = contacts;
    }

    @Override
    public ContactsRecyclerAdapter.ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsRecyclerAdapter.ContactsViewHolder holder, int position) {
        holder.bindContact(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    protected class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private Context mContext;
        private TextView mContactName, mPhoneNo, mEmail;
        View view;

        public ContactsViewHolder(View itemView){
            super(itemView);
            view = itemView;

            mContactName = view.findViewById(R.id.contactName);
            mPhoneNo = view.findViewById(R.id.phoneNo);
            mEmail = view.findViewById(R.id.email);

            mContext = view.getContext();

            view.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bindContact(Contact contact) {
            mContactName.setText(contact.getName());
            mPhoneNo.setText(contact.getPhoneNo());
            mEmail.setText(contact.getEmail());
        }

        @Override
        public void onClick(View v) {
            if (v == view){
                int itemPosition = getLayoutPosition();
                Contact contact = mContacts.get(itemPosition);

                Intent intent = new Intent(mContext, ContactDetailsActivity.class);
                intent.putExtra("email", contact.getEmail());
                mContext.startActivity(intent);
            }
        }
    }
}
