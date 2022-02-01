package com.example.mywishlist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface wishListDao {

    @Insert
    public void addTowishdata(Wishlist wishlist);

    @Query("SELECT * FROM wish")
    public List<Wishlist>getWishListData();

    @Query("SELECT EXISTS (SELECT 1 FROM wish WHERE id=:id)")
    public int isWish(int id);

    @Delete
    public  void delete(Wishlist Wishlist);



}
