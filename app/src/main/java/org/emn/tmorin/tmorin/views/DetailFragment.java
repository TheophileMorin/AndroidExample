package org.emn.tmorin.tmorin.views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.emn.tmorin.tmorin.R;
import org.emn.tmorin.tmorin.model.Book;

public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";
    public static final String BOOK = "BOOK"; //TODO move it in Book.java ?
    private Book currentBook;

    private ImageView bookCoverImageView;
    private TextView titleTextView;
    private TextView bookIsbnTextView;
    private TextView bookPriceTextView;
    private TextView bookSumupTextView;

    public DetailFragment() {
        //To pass a book without specifically create a bundle.
        this.setArguments(new Bundle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookdetail, container, false);

        currentBook = this.getArguments().getParcelable(DetailFragment.BOOK);

        bookCoverImageView = (ImageView) view.findViewById(R.id.bookCoverIV);
        titleTextView = (TextView) view.findViewById(R.id.titleTV);
        bookIsbnTextView = (TextView) view.findViewById(R.id.bookIsbnTV);
        bookPriceTextView = (TextView) view.findViewById(R.id.bookPriceTV);
        bookSumupTextView = (TextView) view.findViewById(R.id.bookSumupTV);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
                " minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea" +
                " commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String isbn = "isbn: " +currentBook.getIsbn();
        String price = currentBook.getPrice() + "€";

        Picasso.with(this.getActivity().getApplicationContext())
                .load(currentBook.getCover())
                .into(bookCoverImageView);
        titleTextView.setText(currentBook.getTitle());
        bookIsbnTextView.setText(isbn);
        bookPriceTextView.setText(price);
        bookSumupTextView.setText(lorem);
        super.onViewCreated(view, savedInstanceState);
    }
}
