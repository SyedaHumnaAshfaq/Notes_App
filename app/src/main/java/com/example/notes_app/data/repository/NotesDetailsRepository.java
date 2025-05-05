package com.example.notes_app.data.repository;


import android.app.Application;

import com.example.notes_app.App;
import com.example.notes_app.data.dao.NoteDao;
import com.example.notes_app.data.model.Note;

import java.util.Date;
import java.util.List;

public class NotesDetailsRepository {

    private final NoteDao noteDao;

    public NotesDetailsRepository(Application application){
        noteDao = App.getDaoSession().getNoteDao();
    }

    public Note createNote(String title, String content,Date time, Long userId){
        Note myNote = null;
        if(noteDao.queryBuilder().where(NoteDao.Properties.Title.eq(title)).unique()==null){
            myNote =new Note(null,userId,content, time,title);
           noteDao.insert(myNote);
        }
        return myNote;
    }

    public List<Note> getNotes(){
       List<Note> allNotes = noteDao.loadAll();
       return allNotes;
    }
    public Note deleteNote(String title){
        Note note = noteDao.queryBuilder().where(NoteDao.Properties.Title.eq(title)).unique();
        noteDao.delete(note);
        return note;
    }
    public Note editNote(Long id,String content,String title){
        Note Mynote = noteDao.queryBuilder().where(NoteDao.Properties.Id.eq(id)).unique();
        Mynote.setContent(content);
        Mynote.setTitle(title);
        noteDao.update(Mynote);
        return Mynote;

    }


}
