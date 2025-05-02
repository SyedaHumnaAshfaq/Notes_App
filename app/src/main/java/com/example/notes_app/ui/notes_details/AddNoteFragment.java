package com.example.notes_app.ui.notes_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes_app.R;

import java.util.Date;


public class AddNoteFragment extends DialogFragment {

    EditText et;
    EditText et1;

    Button Btn;
    private NotesDetailsViewModel notesDetailsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.add_note_fragment,container,false);
        notesDetailsViewModel = new ViewModelProvider(requireActivity()).get(NotesDetailsViewModel.class);
        et =  view.findViewById(R.id.TitleEditText);
        et1 = view.findViewById(R.id.ContentEditText);
        Btn = view.findViewById(R.id.AddNoteBtn);



        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et.getText().toString().trim();
                String Content = et1.getText().toString().trim();
                Date time = new Date();
                Long userId = 15L;
                notesDetailsViewModel.createNote(title,Content,time,userId);
                dismiss();
            }
        });
        return view;
    }
}
