package com.example.zxycalculator;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExprRepository {
    private ExprDao mExprDao;
    private LiveData<List<Expression>> mAllExprs;

    private static class insertAsyncTask extends AsyncTask<Expression, Void, Void> {

        private ExprDao mAsyncTaskDao;

        insertAsyncTask(ExprDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expression... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    ExprRepository(Application application) {
        ExprRoomDatabase db = ExprRoomDatabase.getDatabase(application);
        mExprDao = db.exprDao();
        mAllExprs = mExprDao.getAllWords();
    }

    LiveData<List<Expression>> getAllWords() {
        return mAllExprs;
    }

    public void insert (Expression word) {
        new insertAsyncTask(mExprDao).execute(word);
    }
}
