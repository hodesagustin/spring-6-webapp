package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;

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
		Publisher publisher = new Publisher();
		publisher.setPublisherName("Dragon Texts");
		publisher.setAddress("Address");
		publisher.setCity("City");
		publisher.setState("State");
		publisher.setZip("ZipCode");

		Publisher publisherSaved = publisherRepository.save(publisher);
		System.out.println(publisherSaved);
		System.out.println("Publisher Count: " + publisherRepository.count());

		Author eric = new Author();
		eric.setFirstName("Eric");
		eric.setLastName("Evans");

		Book ddd = new Book();
		ddd.setTitle("Domain Driven Design");
		ddd.setIsbn("123456");
		ddd.setPublisher(publisherSaved);

		Author ericSaved = authorRepository.save(eric);
		Book dddSaved = bookRepository.save(ddd);

		Author rod = new Author();
		rod.setFirstName("Rod");
		rod.setLastName("Johnson");

		Book noEJB = new Book();
		noEJB.setTitle("J2EE Development without EJB");
		noEJB.setIsbn("54757585");
		noEJB.setPublisher(publisherSaved);

		Author rodSaved = authorRepository.save(rod);
		Book noEJBSaved = bookRepository.save(noEJB);

		ericSaved.getBooks().add(dddSaved);
		rodSaved.getBooks().add(noEJBSaved);
		dddSaved.getAuthors().add(ericSaved);
		noEJBSaved.getAuthors().add(rodSaved);

//		ericSaved = authorRepository.save(ericSaved);
//		rodSaved = authorRepository.save(rodSaved);
		bookRepository.save(dddSaved);
		bookRepository.save(noEJBSaved);

		System.out.println("In Bootstrap");
		System.out.println("Author Count: " + authorRepository.count());
		System.out.println("Book Count: " + bookRepository.count());

		authorRepository.findAll().forEach(author -> System.out.println(author));
		bookRepository.findAll().forEach(book -> System.out.println(book));
	}
}
