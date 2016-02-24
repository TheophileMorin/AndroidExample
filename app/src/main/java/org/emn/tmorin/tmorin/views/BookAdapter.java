package org.emn.tmorin.tmorin.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.emn.tmorin.tmorin.BooklistActivity;
import org.emn.tmorin.tmorin.R;
import org.emn.tmorin.tmorin.model.Book;

import java.util.List;

/**
 * Created by theop on 28/01/2016.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private static final String TAG = "BookAdapter";

    private Context activityContext;
    private List<Book> books;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public interface BookListener {
            void onClick(Book bookClicked);
        }

        private static final String TAG = "BookAdapter.ViewHolder";

        private Book reference;
        private TextView titleTextView;
        private TextView priceTextView;
        private ImageView coverTextView;

        private Context activityContext;
        private BookListener clickListener;

        public ViewHolder(View view, Context context) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.titleTV);
            priceTextView = (TextView) view.findViewById(R.id.priceTV);
            coverTextView = (ImageView) view.findViewById(R.id.coverIV);
            activityContext = context;
            view.setOnClickListener(this);
        }

        public void fillView(Book b) {
            reference = b;
            titleTextView.setText(b.getTitle());
            priceTextView.setText((b.getPrice()+ "€"));
            Picasso.with(activityContext)
                    .load(b.getCover())
                    .into(coverTextView);
        }

        public void setListener(BookListener listener) {
            clickListener = listener;
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null)
                clickListener.onClick(reference);
            else
                Log.e(TAG, titleTextView.getText() + " clicked but listener null");
        }

    } // --------------------------------------------------------------------



    public BookAdapter(Context context, List<Book> books) {
        this.activityContext = context;
        this.books = books;
    }

    public void updateBookList(List<Book> books) {
        this.books = books;
        this.notifyDataSetChanged();
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_cv, parent, false);

        return new ViewHolder(v, activityContext);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.fillView(books.get(position));
        holder.setListener((BooklistActivity) activityContext);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }



}
