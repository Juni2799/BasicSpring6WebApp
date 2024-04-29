package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.models.Author;
import guru.springframework.spring6webapp.models.Book;
import guru.springframework.spring6webapp.models.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Publisher uniPub = new Publisher();
        uniPub.setPublisherName("Universal Publications Ltd.");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);
        Publisher uniPubSaved = publisherRepository.save(uniPub);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EEE Development without EJB");
        noEJB.setIsbn("567890");

        Publisher ecoPub = new Publisher();
        ecoPub.setPublisherName("Economic Publications Ltd.");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);
        Publisher ecoPubSaved = publisherRepository.save(ecoPub);

        //Setting association between author and books
        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);

        uniPubSaved.getBooks().add(dddSaved);
        uniPubSaved.getBooks().add(noEJBSaved);
        dddSaved.setPublisher(uniPubSaved);
        noEJBSaved.setPublisher(uniPubSaved);

        //Persisting the data as well
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        publisherRepository.save(uniPubSaved);

        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap...");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
