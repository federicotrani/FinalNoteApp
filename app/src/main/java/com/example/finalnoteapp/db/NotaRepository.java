package com.example.finalnoteapp.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.finalnoteapp.db.NotaRoomDatabase;
import com.example.finalnoteapp.db.dao.NotaDao;
import com.example.finalnoteapp.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application){
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();

        // obtenemos todas las notas
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    public LiveData<List<NotaEntity>> getAll(){ return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavoritas(){ return allNotasFavoritas; }

    public void insert (NotaEntity nota){
        new insertAsyncTask(notaDao).execute(nota);
    }

    public void update (NotaEntity nota){
        new updateAsyncTask(notaDao).execute(nota);
    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void>{

        private NotaDao notaDaoAsyncTask;

        insertAsyncTask(NotaDao notaDao){
            this.notaDaoAsyncTask = notaDao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.insert(notaEntities[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<NotaEntity, Void, Void>{

        private NotaDao notaDaoAsyncTask;

        updateAsyncTask(NotaDao notaDao){
            this.notaDaoAsyncTask = notaDao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.update(notaEntities[0]);
            return null;
        }
    }

}
