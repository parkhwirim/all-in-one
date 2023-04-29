package com.group.libraryapp.service.book;

import org.springframework.stereotype.Service;

import com.group.libraryapp.repository.book.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void saveBook() {
		bookRepository.saveBook();
	}
}
