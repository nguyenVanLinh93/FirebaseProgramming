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

public class ObjectValuesActivity extends AppCompatActivity {

    private EditText mEdtName;
    private EditText mEdtAge;
    private TextView mTxtvValues;
    private Firebase root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_values);
        // Setup firebase on android
        Firebase.setAndroidContext(this);

        root = new Firebase("https://stringvalues.firebaseio.com/");

        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mTxtvValues = (TextView) findViewById(R.id.txtvStringValues);
    }

    public void setValuesOnFirebase(View clickButton) {
        String name = mEdtName.getText().toString();
        int age = Integer.parseInt(mEdtAge.getText().toString());

        Person person = new Person(name, age);
        root.child("data Object Person").setValue(person);
        mEdtName.setText("");
        mEdtAge.setText("");

        // get values from firebase
        root.child("data Object Person").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                int age = Integer.parseInt(dataSnapshot.child("age").getValue().toString());

                mTxtvValues.setText(String.format("Name: %s           Age: %s", name, age));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
