package com.example.urbs;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProviders;;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    RecyclerView uRecyclerview;

    FirebaseDatabase uFireDatabase;

    DatabaseReference uDataRef;

    EditText uSearchView;


    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uRecyclerview = getView().findViewById(R.id.recyclerView);
        uRecyclerview.setHasFixedSize(true);

        uRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        uSearchView = (EditText)getView().findViewById(R.id.editText);
        uSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = uSearchView.getText().toString();

                UrbsSearch(searchText);
            }
        });

        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    @Override
    public void onStart() {

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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        //sending query to database

        uFireDatabase = FirebaseDatabase.getInstance();
        uDataRef = uFireDatabase.getReference("Data");

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater Inflater)  {

        //inflate menu to add items
        Inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,Inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings_icon){

            return true ;
        }

        return super.onOptionsItemSelected(item);
    }
    // @Override
    //public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //super.onActivityCreated(savedInstanceState);
        // mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel }
    }


