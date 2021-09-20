package com.example.memory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        MenuItem searchItem=menu.findItem(R.id.searchbar);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Notes Here");

        SearchView.OnQueryTextListener listener=new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        };

        searchView.setOnQueryTextListener(listener);

        return super.onCreateOptionsMenu(menu);
    }

    RecyclerView recyclerView;
    Button cbtn;
    Adapter adapter;
    List<Model> notesList;
    DatabaseClass databaseClass;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        cbtn = findViewById(R.id.cbtn);

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });

        Button b = (Button) findViewById(R.id.catbut);

       b.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,Pop.class));
           }
       });



        notesList = new ArrayList<>();

        databaseClass = new DatabaseClass(this);
        fetchAllNotesFromDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false );
        adapter = new Adapter(this, MainActivity.this, notesList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        {

        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.sidemenu:
                        startActivity(new Intent(getApplicationContext(), Sidemenu.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.challenges:
                        startActivity(new Intent(getApplicationContext(), challenges.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.fiveminread:
                        startActivity(new Intent(getApplicationContext(), fiveminread.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }

    void fetchAllNotesFromDatabase()
    {
        Cursor cursor= databaseClass.readAllData();

        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No Data to show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                notesList.add(new Model(cursor.getString(0),cursor.getString(1)));
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.delete_all_notes)
        {
            deleteAllNotes();
        }


            return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes()
    {
        DatabaseClass db=new DatabaseClass(MainActivity.this);
        db.deleteAllNotes();
        recreate();
    }

}
