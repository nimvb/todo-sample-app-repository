package com.nimvb.app.repository;

import com.nimvb.app.database.document.Document;
import com.nimvb.app.database.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;

@Repository
public class TodoRepository implements SimpleRepository<Todo,Integer> {

    private final Document<Todo,Integer> todoDocument;

    @Autowired
    public TodoRepository(@Qualifier("todoDocument") Document<Todo, Integer> todoDocument) {
        this.todoDocument = todoDocument;
    }

    @Override
    public Todo save(Todo todo) {
        Assert.notNull(todo,"null object is passed");
        if(todo.getId() == null){
            return todoDocument.insert(todo);
        }
        return todoDocument.update(todo.getId(),todo);
    }

    @Override
    public Todo persist(Todo todo) {
        Assert.notNull(todo,"null object is passed");
        if(todo.getId() == null){
            return todoDocument.insert(todo);
        }
        return todoDocument.update(todo.getId(),todo);
    }

    @Override
    public Optional<Todo> findById(Integer integer) {
        Assert.notNull(integer,"null id is passed");
        return Optional.ofNullable(todoDocument.find(integer));
    }

    @Override
    public Optional<Todo> fetchById(Integer id) {
        Assert.notNull(id,"null id is passed");
        return Optional.ofNullable(todoDocument.fetch(id));
    }

    @Override
    public Collection<Todo> findAll() {
        return todoDocument.all();
    }

    @Override
    public void deleteById(Integer integer) {
        Assert.notNull(integer,"null id is passed");
        todoDocument.delete(integer);
    }

    @Override
    public Integer count() {
        return todoDocument.count();
    }
}
