package com.onyeka.project1.mapper;

import com.onyeka.project1.model.Note;
import com.onyeka.project1.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> findAllNotes();

    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle}")
    Note getNote(String noteTitle);
    @Insert("INSERT INTO NOTES (userid, notetitle, notedescription) VALUES(#{userId}, #{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer addNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}")
    Integer update(Note note);





}
