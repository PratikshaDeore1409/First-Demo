package com.example.demo.service;


import java.util.List;

import com.example.demo.modal.Books;

public interface BookService {
	
	Books saveBook(Books book);
	
	List<Books> getAllBooks();
	
	Books getBookById(int bookId);
	
	boolean deleteBook(int bookId);

}
