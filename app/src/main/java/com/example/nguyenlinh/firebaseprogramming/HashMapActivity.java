package com.example.nguyenlinh.firebaseprogramming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapActivity extends AppCompatActivity {

    private EditText mEdtName;
    private EditText mEdtAge;
    private TextView mTxtvValues;
    private TextView mTxtvCount;
    private Firebase root;
    private HashMap<String, Person> mPersonHashMap;
    private ArrayList<String> mkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_map);
        // Setup firebase on android
        Firebase.setAndroidContext(this);

        root = new Firebase("https://stringvalues.firebaseio.com/");

        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mTxtvValues = (TextView) findViewById(R.id.txtvStringValues);
        mTxtvCount = (TextView) findViewById(R.id.txtvcount);
        mPersonHashMap = new HashMap<String, Person>();
        mkey = new ArrayList<String>();
    }

    public void saveValues(View clickButton) {
        String name = mEdtName.getText().toString();
        int age = Integer.parseInt(mEdtAge.getText().toString());

        Person person = new Person(name, age);
        int no=mkey.size()+1;
        String key = "Person No." + no;
        mPersonHashMap.put(key, person);
        mkey.add(key);
        mEdtName.setText("");
        mEdtAge.setText("");
        mTxtvCount.setText("Count person = " + mPersonHashMap.size());
    }

    public void setValuesOnFirebaseHasmap(View clickButton) {
        root.child("data Hashmap").setValue(mPersonHashMap);

        root.child("data Hashmap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Person> personsList = new ArrayList<Person>();
                for (String key : mkey) {
                    String name = dataSnapshot.child(key).child("name").getValue().toString();
                    int age = Integer.parseInt(dataSnapshot.child(key).child("age").getValue().toString());

                    personsList.add(new Person(name, age));
                }
                for (Person person : personsList) {
                    String text = mTxtvValues.getText().toString();
                    mTxtvValues.setText(text + "\nName: " + person.getName() + "           Age: " + person.getAge());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mPersonHashMap.clear();
        mkey.clear();
        mTxtvCount.setText("");
    }
}
