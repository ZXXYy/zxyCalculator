package com.example.zxycalculator;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExprViewModel extends AndroidViewModel {

    private ExprRepository mRepository;

    private LiveData<List<Expression>> mAllWords;

    public ExprViewModel (Application application) {
        super(application);
        mRepository = new ExprRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Expression>> getAllWords() { return mAllWords; }

    public void insert(Expression expr) { mRepository.insert(expr); }
}
