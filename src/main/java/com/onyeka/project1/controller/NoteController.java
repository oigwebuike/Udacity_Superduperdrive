package com.onyeka.project1.controller;


import com.onyeka.project1.model.Note;
import com.onyeka.project1.model.User;
import com.onyeka.project1.services.AuthenticationService;
import com.onyeka.project1.services.NoteService;
import com.onyeka.project1.services.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteController {

    private NoteService noteService;
    private UserService userService;
    private AuthenticationService authenticationService;

    public NoteController(NoteService noteService, UserService userService, AuthenticationService authenticationService) {
        this.noteService = noteService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    /*

    private static List<Note> notes = new ArrayList<>();

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

     */


    @GetMapping("result")
    public String result(Model model) {

        return "result";
    }

    @GetMapping("note")
    public String homeView(Model model, String noteTitle){

        noteService.getNote(noteTitle);

        model.addAttribute("notes", noteService.getNotes());


        return "note";
    }

    //@RequestMapping(value = "home", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("note")
    public String addNote(Authentication authentication, String noteTitle, String noteDescription, Model model, Note note){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();



        this.noteService.addNote(new Note(userId, note.getNoteTitle(), note.getNoteDescription()));

        //List<Note> notes = noteService.getNotes();


        model.addAttribute("notes", noteService.getNotes());
        //System.out.println(userId);
        //System.out.println(note.getNoteId());


        return "note";
    }
    @RequestMapping(value = "delete/{noteId}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteNote(@PathVariable Integer noteId, Model model, String noteTitle){

        //this.noteService.getNote(noteTitle);
        Note note = this.noteService.getNote(noteTitle);

        model.addAttribute("note", note);
        this.noteService.deleteNote(noteId);

        return "result";
    }





    @RequestMapping(value = "edit", method = {RequestMethod.GET, RequestMethod.PUT})
    public String editNote(Authentication auth, String noteTitle, String noteDescription, Model model){


       Note note = this.noteService.getNote(noteTitle);
       Integer noteId = note.getNoteId();

       if(noteId == null){

           String username = auth.getName();
           User user = userService.getUser(username);
           Integer userId = user.getUserId();
           this.noteService.addNote(note);
       }
       else{
           this.noteService.editNote(note);


       }


        model.addAttribute("note", note);

        return "note";
    }




        /*
    @PostMapping("home/Note")
    public String createNote(Model model){
        model.addAttribute("notes",  notes);
        return "result";
    }

    @PostMapping("home")
    public String getNotes(@RequestParam String noteTitle){
        Note note = noteService.getNote(noteTitle);
        //noteService.addNote(note);
        notes.add(note);
        return "redirect:";
    }



    @PostMapping("delete")
    public String deleteNote(int noteId){

        noteService.deleteNote(noteId);
        return "home";
    }

         */
}
