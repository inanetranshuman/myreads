package com.example.inane.myreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.inane.myreads.dao.AuthorRepository;
import com.example.inane.myreads.dao.BookRepository;
import com.example.inane.myreads.dao.PublisherRepository;
import com.example.inane.myreads.model.Author;
import com.example.inane.myreads.model.Book;
import com.example.inane.myreads.model.Publisher;
import com.github.javafaker.Faker;

@Component
public class DataSeeder {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Value("${seed.data.enabled}")
    private boolean seedDataEnabled;

    public void seedDataIfNeeded() {
        Random random = new Random();
        DataGenerator dataGenerator = new DataGenerator();
        if (seedDataEnabled && bookRepository.count() == 0) {
             List<Author> authors = new ArrayList<>();
            for (int i=0; i < 10; i++) {
                Author randomAuthor = dataGenerator.generateRandomAuthor();
                authorRepository.save(randomAuthor);
                authors.add(randomAuthor);
            }

            List<Publisher> publishers = new ArrayList<>();
            for (int i=0; i < 10; i++) {
                Publisher randomPublisher = dataGenerator.generatePublisher();
                publisherRepository.save(randomPublisher);
                publishers.add(randomPublisher);
            }

            for (int i = 0; i < 50; i++) { 
                Book randomBook = dataGenerator.generateRandomBook();
                randomBook.setAuthorUID(authors.get(random.nextInt(authors.size())).getAuthorUID());
                randomBook.setPublisherUID(publishers.get(random.nextInt(publishers.size())).getPublisherUID());
                bookRepository.save(randomBook);
            }

            System.out.println("Successfully seeded data.");
        } else {
            System.out.println(String.format("%d books already exist.", bookRepository.count()));
        }
    }

    class DataGenerator {
        private final Faker faker = new Faker();

    
        public Book generateRandomBook() {
            Book book = new Book();
            book.setBookUID(UUID.randomUUID().toString());
            book.setTitle(faker.book().title());
            book.setSubTitle(faker.book().title());
            book.setPublishedDate(faker.date().birthday());
            book.setCategory(faker.book().genre());
            book.setSubCategory(faker.book().genre());
            book.setCoverImageUrl(faker.internet().url());
            book.setLanguage(faker.nation().language());
            book.setTags(faker.book().genre());
            book.setDescription(faker.lorem().paragraph());
    
            return book;
        }

        public Author generateRandomAuthor() {
            Author author = new Author();
            author.setAuthorUID(UUID.randomUUID().toString());
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            author.setBirthDate(faker.date().birthday());
            author.setWebsite(faker.internet().url());

            return author;
        }

        public Publisher generatePublisher() {
            Publisher publisher = new Publisher();
            publisher.setPublisherUID(UUID.randomUUID().toString());
            publisher.setName(faker.company().name());
            publisher.setContactNumber(faker.phoneNumber().phoneNumber());
            publisher.setCountry(faker.country().name());
            publisher.setDescription(faker.lorem().paragraph());
            publisher.setFoundedDate(faker.date().birthday());

            return publisher;
        }
    }
}
