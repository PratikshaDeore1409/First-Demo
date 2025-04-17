package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.bookRepository.BookRepository;
import com.example.demo.modal.Books;

import jakarta.servlet.http.HttpSession;


@Service
public class BookServiceNew implements BookService 
{
	@Autowired
	public BookRepository bookRepo;
	
	public Books saveBook(Books book) 
	{
		 Books newBook =bookRepo.save(book);
		 return newBook;
		 
		 
	}
	
	public List<Books> getAllBooks()
	{
		return bookRepo.findAll();
	}
	
	public Books getBookById(int bookId) 
	{
		return bookRepo.findById(bookId).get();
	}
	
	public boolean deleteBook(int bookId) 
	{
		Books book= bookRepo.findById(bookId).get();
		if(book != null) {
			bookRepo.delete(book);
			return true;
		}
		return false;
	}
	
	public void removeSessionMassage() 
	{
		HttpSession session=((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

}
