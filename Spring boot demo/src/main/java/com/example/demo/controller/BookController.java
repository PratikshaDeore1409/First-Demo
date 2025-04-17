package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.modal.Books;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
	@Autowired
	public BookService bookService;
	
	@GetMapping("/GetBook")
	public String index(Model m, HttpSession session) {
	    List<Books> book = bookService.getAllBooks();
	    m.addAttribute("bookList", book);

	    // Get and remove session message
	    String msg = (String) session.getAttribute("msg");
	    if (msg != null) {
	        m.addAttribute("message", msg);
	        session.removeAttribute("msg");
	    }

	    return "index";
	}

	@GetMapping("/loadBookSave")
	public String loadBookSave(HttpSession session, Model model) {
	    String msg = (String) session.getAttribute("msg");
	    if (msg != null) {
	        model.addAttribute("message", msg);
	        session.removeAttribute("msg");
	    }
	    return "book_save";
	}

	 @GetMapping("/EditBook/{bookId}")
	 public String EditBook(@PathVariable int bookId, Model m) {
        Books book = bookService.getBookById(bookId);
        m.addAttribute("book", book);
        return "edit_book";
	 }
	 @PostMapping("/saveBook")
     public String saveBook(@ModelAttribute Books book, HttpSession session) {
        Books newBook = bookService.saveBook(book);

        if (newBook != null) {
            session.setAttribute("msg", "Register successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on the server");
        }

        return "redirect:/loadBookSave";
     }
	 @PostMapping("/updateBookDtls")
     public String updateBook(@ModelAttribute Books book, HttpSession session) {
        Books updateBook = bookService.saveBook(book);

        if (updateBook != null) {
            session.setAttribute("msg", "Update successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on the server");
        }

        return "redirect:/GetBook";
     }
	 @GetMapping("/deleteBook/{bookId}")
     public String deleteBook(@PathVariable int bookId, HttpSession session) {
        boolean f = bookService.deleteBook(bookId);
        if (f) {
            session.setAttribute("msg", "Delete successfully");
        } else {
            session.setAttribute("msg", "Something went wrong on the server");
        }
        return "redirect:/GetBook";
     }

}
