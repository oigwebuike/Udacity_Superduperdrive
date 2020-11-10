package com.onyeka.project1.controller;

import com.onyeka.project1.model.Credential;
import com.onyeka.project1.model.File;
import com.onyeka.project1.model.Note;
import com.onyeka.project1.model.User;
import com.onyeka.project1.services.CredentialService;
import com.onyeka.project1.services.FileService;
import com.onyeka.project1.services.NoteService;
import com.onyeka.project1.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private static List<Note> notes = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<File> files = new ArrayList<>();
    private static List<Credential> credentials = new ArrayList<>();

    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    /*@GetMapping("home")
   public String homeView(Authentication auth, Note note){
        User user = (User) auth.getPrincipal();
        System.out.println(user.getUserId());
        return "home"; }

     */





    @GetMapping(value = {"home"})
    public String homeView(){

        return "home";
    }
/*

      @PostMapping(value = {"home"})
      public String Note(User user, Model model, String noteTitle){


          note.setNoteDescription("");
          note.setUserId(user.getUserId());
          note.setNoteId(note.getNoteId());
          noteService.addNote(note);

           model.addAttribute("userId", user.getUserId());
          model.addAttribute("noteId", note.getNoteId());
          model.addAttribute("noteDescription", note.getNoteDescription());
          model.addAttribute("noteTitle", note.getNoteTitle());


        Note note = noteService.getNote(noteTitle);
        System.out.println(note);
        model.addAttribute( "note", note);
        model.addAttribute("notes", noteService.getNotes());
        return "home";
    }



    @PostMapping("users")
    public List<User> getUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return users;
    }

     */



/*
    @PostMapping("addNote")
    public String addNote(@RequestParam Note note, Model model){
        notes.add(note);

        noteService.addNote(note);
        model.addAttribute("note", note);
        note.setNoteTitle("");
        note.setNoteDescription("");




        return "home";
    }

 */





/*
    @PostMapping
    public String addCredential(Model model,@ModelAttribute("credential") Credential credential){
        //user.setUsername(authentication.getName());
        //credentialService.addCredential(credential);
        //model.addAttribute("credential", credentialService.getCredentials());

        return "home";
    }

 */




}
