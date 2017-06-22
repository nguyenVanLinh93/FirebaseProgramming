package com.example.nguyenlinh.firebaseprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class StringValuesActivity extends AppCompatActivity {

    private TextView mTxtvValues;
    private EditText mEdtValues;
    private Firebase root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_values);
        // Setup firebase on android
        Firebase.setAndroidContext(this);

        root = new Firebase("https://stringvalues.firebaseio.com/"); // root database

        mEdtValues = (EditText) findViewById(R.id.edtStringValues);
        mTxtvValues = (TextView) findViewById(R.id.txtvStringValues);
    }

    public void setValuesOnFirebase(View clickButton) {
        String values = mEdtValues.getText().toString();
        root.child("data String").setValue(values);
        mEdtValues.setText("");

        // get values from firebase
        root.child("data String").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String values = (String) dataSnapshot.getValue();
                mTxtvValues.setText(values);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
