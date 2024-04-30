package guru.springframework.spring6webapp.services;

import guru.springframework.spring6webapp.models.Book;

public interface BookService {
    Iterable<Book> findAll();
}
