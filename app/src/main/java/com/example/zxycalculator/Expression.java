package com.example.zxycalculator;

import android.arch.persistence.room.*;

import androidx.annotation.NonNull;

@Entity(tableName = "expr_table")
public class Expression {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "expr")
    private String mExpr;

    public Expression(@NonNull String expr) {this.mExpr = expr;}

    public String getExpr(){return this.mExpr;}
}
