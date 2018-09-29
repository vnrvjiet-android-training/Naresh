package com.example.admin.realtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // Write a message to the database
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference("message");

               myRef.setValue(editText.getText().toString());
               // Read from the database
               myRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       // This method is called once with the initial value and again
                       // whenever data at this location is updated.
                       String value = dataSnapshot.getValue(String.class);
                       Log.d("TAG", "Value is: " + value);
                       textView.setText(value);
                   }

                   @Override
                   public void onCancelled(DatabaseError error) {
                       // Failed to read value
                       Log.w("tag", "Failed to read value.", error.toException());
                   }
               });
           }

       });
    }
}
