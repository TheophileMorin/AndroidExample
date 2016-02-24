package org.emn.tmorin.tmorin.services;

import org.emn.tmorin.tmorin.model.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by theop on 14/02/2016.
 */
public interface HenriPotierService {

    @GET("books")
    Call<List<Book>> listBooks();
}
