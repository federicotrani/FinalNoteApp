package com.example.finalnoteapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.finalnoteapp.db.NotaRepository;
import com.example.finalnoteapp.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application){
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();
    }

    public LiveData<List<NotaEntity>> getAllNotas(){ return allNotas; }

    public void insertarNota(NotaEntity notaInsertar){ notaRepository.insert(notaInsertar);}

    public void updateNota(NotaEntity notaModificar){ notaRepository.update(notaModificar);}
}
