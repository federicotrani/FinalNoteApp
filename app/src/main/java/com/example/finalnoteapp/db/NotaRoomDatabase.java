package com.example.finalnoteapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.finalnoteapp.db.dao.NotaDao;
import com.example.finalnoteapp.db.entity.NotaEntity;

@Database(entities={NotaEntity.class}, version=1, exportSchema = false)
public abstract class NotaRoomDatabase extends RoomDatabase {
    public abstract NotaDao notaDao();
    private static volatile NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (NotaRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
