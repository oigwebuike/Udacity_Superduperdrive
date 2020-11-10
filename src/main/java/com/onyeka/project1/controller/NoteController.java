package com.onyeka.project1.controller;


import com.onyeka.project1.model.Note;
import com.onyeka.project1.model.User;
import com.onyeka.project1.services.AuthenticationService;
import com.onyeka.project1.services.NoteService;
import com.onyeka.project1.services.UserService;
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


    @GetMapping("home/Note")
    public String createNote(Model model) {
        return "result";
    }

    //@RequestMapping(value = "home", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("home")
    public String addNote(String noteTitle, String noteDescription, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUser(username);
        user.setUserId(user.getUserId());
        Note note = new Note();
        note.setUserId(user.getUserId());
        note.setNoteTitle(noteTitle);
        note.setNoteDescription(noteDescription);

        noteService.addNote(note);

        List<Note> notes = noteService.getNotes();

        model.addAttribute("notes", notes);
        return "home";
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
