package org.emn.tmorin.tmorin;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.emn.tmorin.tmorin.model.Book;
import org.emn.tmorin.tmorin.views.BookAdapter;
import org.emn.tmorin.tmorin.views.DetailFragment;
import org.emn.tmorin.tmorin.views.ListFragment;

public class BooklistActivity extends AppCompatActivity implements BookAdapter.ViewHolder.BookListener {

    private static final String TAG = "BooklistActivity";

    private boolean isLandscape = false;

    private View detailView;
    private View listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        isLandscape = this.getResources().getBoolean(R.bool.landscape);
        detailView = findViewById(R.id.fragment_book_detail_container);
        listView = findViewById(R.id.fragment_book_list);

        //If you change screen orientation
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_list, new ListFragment(), ListFragment.class.getSimpleName())
                    .commit();

        listView.setVisibility(View.VISIBLE);
        if(!isLandscape)
            detailView.setVisibility(View.GONE); //Eviter la rémanance
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            detailView.setVisibility(View.VISIBLE);
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            detailView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isLandscape) {
            detailView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(Book bookClicked) {

        //We add the clicked book to put correct data into the fragment.
        DetailFragment bookDetail =  new DetailFragment();
        bookDetail.getArguments().putParcelable(DetailFragment.BOOK, bookClicked);

        detailView.setVisibility(View.VISIBLE);
        if(isLandscape)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_detail, bookDetail, DetailFragment.class.getSimpleName())
                    .commit();
        else {
            listView.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_book_detail, bookDetail, DetailFragment.class.getSimpleName())
                    .addToBackStack(ListFragment.class.getSimpleName())
                    .commit();
        }

    }



}