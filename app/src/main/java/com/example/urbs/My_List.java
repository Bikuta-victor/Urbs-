package com.example.urbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class My_List extends AppCompatActivity {

    RecyclerView uRecyclerview;

    FirebaseDatabase uFireDatabase;

    DatabaseReference uDataRef;

    EditText uSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Setting Actionbar
        ActionBar uActionbar = getSupportActionBar();
        uActionbar.setTitle("Urbs");

// Setting RecyclerView size
        uRecyclerview = findViewById(R.id.recyclerView);
        uRecyclerview.setHasFixedSize(true);

//Search view
        uSearchView = (EditText) findViewById(R.id.editText);
        uSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = uSearchView.getText().toString();

                UrbsSearch(searchText);
            }
        });


//setting linearlayout
        uRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        //sending query to database

        uFireDatabase = FirebaseDatabase.getInstance();
        uDataRef = uFireDatabase.getReference("Data");

    }



    //loading data into the onStart menu
    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Models, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Models, ViewHolder>(
                        Models.class,
                        R.layout.rows,
                        ViewHolder.class,
                        uDataRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Models models, int i) {

                        viewHolder.setuDetails(getApplicationContext(), models.getTitle(), models.getDescription(), models.getImage());

                    }
                };

        uRecyclerview.setAdapter(firebaseRecyclerAdapter);
    }


    private void UrbsSearch(String searchquery) {
        Query urbsSearchQuery = uDataRef.orderByChild("title").startAt(searchquery).endAt(searchquery + "//u8f88");

            FirebaseRecyclerAdapter<Models, ViewHolder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Models, ViewHolder>(
                            Models.class,
                            R.layout.rows,
                            ViewHolder.class,
                            urbsSearchQuery

                    ) {
                        @Override
                        protected void populateViewHolder(ViewHolder viewHolder, Models models, int i) {

                            viewHolder.setuDetails(getApplicationContext(), models.getTitle(), models.getDescription(), models.getImage());


                        }

                    };
        uRecyclerview.setAdapter(firebaseRecyclerAdapter);
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       //inflate menu to add items
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings_icon){

            return true ;
        }

        return super.onOptionsItemSelected(item);
    }
}
