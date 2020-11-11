package com.onyeka.project1.model;

import java.util.Objects;

public class Note {

    private Integer noteId;
    private static Integer nextId = 1;

    private Integer userId;
    private String noteTitle;
    private String noteDescription;

    public Note(Integer userId, String noteTitle, String noteDescription) {
        this.noteId = nextId;
        this.userId = userId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        nextId++;
    }

    public Note() {

    }


    public Integer getNoteId() {
        return noteId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId.equals(note.noteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId);
    }
}
