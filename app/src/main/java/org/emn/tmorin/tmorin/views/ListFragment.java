package org.emn.tmorin.tmorin.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.emn.tmorin.tmorin.R;
import org.emn.tmorin.tmorin.model.Book;
import org.emn.tmorin.tmorin.services.HenriPotierService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";

    private List<Book> books;
    private static final String SAVED_BOOK_LIST_KEY = "SAVED_BOOK_LIST_KEY";

    private BookAdapter bookAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.booklist, container, false);

        //Creating empty list before retrieving books.
        books = new ArrayList<>();

        RecyclerView recycled = (RecyclerView) view.findViewById(R.id.bookListView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recycled.setLayoutManager(layoutManager);

        //Creating adapter for the view.
        bookAdapter = new BookAdapter(getContext(), books);
        recycled.setAdapter(bookAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            Log.i(TAG, "onActivityCreated --> no saved instance");
            retrieveBooks();
        } else {
            Log.i(TAG, "onActivityCreated --> retrieve saved instance");
            books = savedInstanceState.getParcelableArrayList(SAVED_BOOK_LIST_KEY);
            bookAdapter.updateBookList(books);
        }
    }

    private void retrieveBooks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HenriPotierService service = retrofit.create(HenriPotierService.class);
        Call<List<Book>> call = service.listBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                Log.i(TAG, "Book list retrieved from internet.");
                books = response.body();
                bookAdapter.updateBookList(books);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Unable to get book list from internet.");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVED_BOOK_LIST_KEY, (ArrayList<? extends Parcelable>) books);
        /* Parcelable[] parcelableArray = new Parcelable[books.size()];
        int i=0;
        for(Book b : books) {
            parcelableArray[i] = b;
            ++i;
        }
        outState.putParcelableArray(SAVED_BOOK_LIST_KEY, parcelableArray);*/
        Log.i(TAG, "Book list saved.");
        super.onSaveInstanceState(outState);

    }
}