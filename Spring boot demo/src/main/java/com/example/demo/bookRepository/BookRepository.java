package com.example.demo.bookRepository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.Books;

public interface BookRepository extends JpaRepository<Books,Integer> {

}
