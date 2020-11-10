package com.onyeka.project1.services;

import com.onyeka.project1.mapper.NoteMapper;
import com.onyeka.project1.model.Note;
import com.onyeka.project1.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    private List<Note> notes;

    @PostConstruct
    public void postConstruct() {
        this.notes = new ArrayList<>();
    }

    public Integer addNote(Note note){
        return noteMapper.addNote(new Note(null, note.getUserId(), note.getNoteTitle(), note.getNoteDescription()));
    }

    public Note getNote(String noteTitle){
        return noteMapper.getNote(noteTitle);
    }


    public List<Note> getNotes(){ return noteMapper.findAllNotes();}

    public void deleteNote(int noteId){
        noteMapper.deleteNote(noteId);
    }

}
