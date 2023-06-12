package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class BookService {

	private final BookRepository bookRepository;
	private final UserLoanHistoryRepository userLoanHistoryRepository;
	private final UserRepository userRepository;

	public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userLoanHistoryRepository = userLoanHistoryRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveBook(BookCreateRequest request) {
		bookRepository.save(new Book(request.getName()));
	}

	@Transactional
	public void loanBook(BookLoanRequest request) {
		Book book = bookRepository.findByName(request.getBookName())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));

		if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
			throw new IllegalArgumentException("다른 회원이 대출 중인 도서입니다.");
		};

		User user = userRepository.findByName(request.getUserName())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		user.loanBook(book.getName());
	}

	@Transactional
	public void returnBook(BookReturnRequest request) {
		User user = userRepository.findByName(request.getUserName())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
		user.returnBook(request.getBookName());
	}
}
