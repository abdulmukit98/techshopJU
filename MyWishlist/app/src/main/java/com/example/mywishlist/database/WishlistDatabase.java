package com.example.mywishlist.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={Wishlist.class},version = 1)

public abstract class WishlistDatabase extends RoomDatabase{

    public abstract wishListDao wishListDao();

}
