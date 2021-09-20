package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity {

    EditText categorytitle;
    Button addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        categorytitle=findViewById(R.id.categorytitle);
        addNote=findViewById(R.id.addNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(categorytitle.getText().toString()))
                {
                    DatabaseClass db=new DatabaseClass( AddNotesActivity.this);
                    db.addNotes(categorytitle.getText().toString());

                    Intent intent = new Intent(AddNotesActivity.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                }

                else
                {
                    Toast.makeText(AddNotesActivity.this, "", Toast.LENGTH_SHORT).show();
                }





            }
        });

    }
}