package com.example.zxycalculator;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface ExprDao {

    @Insert
    void insert(Expression expr);

    @Query("DELETE FROM expr_table")
    void deleteAll();

    @Query("SELECT * from expr_table")
    LiveData<List<Expression>> getAllWords();
}
