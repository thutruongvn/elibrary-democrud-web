package edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.impl;

import edu.mum.cs.cs425.demos.elibrarydemocrudweb.model.Book;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.repository.BookRepository;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

//@Service(value = "MainBookService")
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public Iterable<Book> getAllBooks() {
//        return ((List<Book>)repository.findAll())
//                .stream()
//                .sorted(Comparator.comparing(Book::getTitle))
//                .collect(Collectors.toList());
        return repository.findAll(Sort.by("title"));
    }

    @Override
    public Page<Book> getAllBooksPaged(int pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 3, Sort.by("title")));
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    public Page<Book> findBookByTitle(String bookTitle, int pageNo) {
        return (Page<Book>) repository.findBookByTitle(bookTitle, PageRequest.of(pageNo, 3, Sort.by("title")));
    }

    @Override
    public Page<Book> searchBookByTitle(String bookTitle, int pageNo) {
        List<Book> resultList = repository.findAll().stream()
                .filter(book ->  book.getTitle().toLowerCase().contains(bookTitle.toLowerCase()))
                .collect(Collectors.toList());
        return new PageImpl<>(resultList , PageRequest.of(pageNo, 10, Sort.by("title")), resultList.size());
    }


    @Override
    public void deleteBookById(Integer bookId) {
        repository.deleteById(bookId);
    }

    @Override
    public Optional<Book> findByISBN(String isbn) {
        return repository.findBookByIsbn(isbn);
    }

}
