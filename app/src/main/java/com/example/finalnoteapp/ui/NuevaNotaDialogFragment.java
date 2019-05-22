package com.example.finalnoteapp.ui;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.finalnoteapp.R;
import com.example.finalnoteapp.db.entity.NotaEntity;
import com.example.finalnoteapp.viewmodel.NuevaNotaDialogViewModel;

public class NuevaNotaDialogFragment extends DialogFragment {

    private View view;
    private EditText editTextTitulo, editTextContenido;
    private Switch switchFavorita;
    private RadioGroup radioGroupColor;

    public static NuevaNotaDialogFragment newInstance() {
        return new NuevaNotaDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nueva Nota");
        builder.setMessage("Ingresar Datos de la Nota")
                .setPositiveButton("Grabar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String titulo = editTextTitulo.getText().toString();
                        String contenido = editTextContenido.getText().toString();
                        String color = "blanco";
                        switch(radioGroupColor.getCheckedRadioButtonId()){
                            case R.id.radioButtonAzul:
                                color = "azul";
                                break;
                            case R.id.radioButtonRojo:
                                color = "rojo";
                                break;
                            case R.id.radioButtonVerde:
                                color = "verde";
                                break;
                        }
                        boolean favorita = switchFavorita.isChecked();

                        NuevaNotaDialogViewModel mViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);
                        mViewModel.insertarNota(new NotaEntity(titulo, contenido, favorita, color));

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.nueva_nota_dialog_fragment, null);

        editTextTitulo = view.findViewById(R.id.editTextTitulo);
        editTextContenido = view.findViewById(R.id.editTextContenido);

        switchFavorita = view.findViewById(R.id.switchFavorita);
        radioGroupColor = view.findViewById(R.id.radioGroupColor);

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
