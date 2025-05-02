package com.example.notes_app.ui.notes_details;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.notes_app.R;
import com.example.notes_app.data.dao.NoteDao;
import com.example.notes_app.data.model.Note;
import com.example.notes_app.data.repository.NotesDetailsRepository;

import java.util.Date;
import java.util.List;

public class NotesDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<Note> createNoteResult = new MutableLiveData<>();
    private final MutableLiveData<List<Note>> NoteResult = new MutableLiveData<>();

    private  final NotesDetailsRepository notesDetailsRepository;
    public NotesDetailsViewModel(@NonNull Application application) {
        super(application);
        notesDetailsRepository = new NotesDetailsRepository(application);
    }

    LiveData<Note> getcreateNoteResult(){
        return createNoteResult;
    };

    LiveData<List<Note>> getNoteResult(){
      return NoteResult;
    };

    public void createNote(String title, String content, Date time, Long userId){
        Note mynote = notesDetailsRepository.createNote(title,content,time,userId);
        if(mynote==null){
            createNoteResult.setValue(null);
        }
        else{
            createNoteResult.setValue(mynote);
        }

    }
    public void getAllNotes(){
        List<Note> myNotes = notesDetailsRepository.getNotes();
        if(myNotes.isEmpty()){
            NoteResult.setValue(null);
        }
        else {
            NoteResult.setValue(myNotes);
        }
    }
}
