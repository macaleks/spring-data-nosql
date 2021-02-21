package ru.otus.jdbcprj.cascade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.repository.BookRepository;

import java.util.Objects;

public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if ((source instanceof Book) && ((Book) source).getGenre() != null) {
            mongoOperations.save(((Book) source).getGenre());
        }
    }

    @Autowired
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        if (Objects.equals(event.getCollectionName(), "book")) {
            String id = null;
            for (Object key : event.getSource().values()) {
                id = String.valueOf(key);
            }
//            if (bookRepository.)
        }
    }

}
