package com.lcwd.todo.services;

import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TodoService {

    List<Todo> todos = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(TodoService.class);


    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("todos - {}", this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getSingleTodo(int id) {
        Todo todo = todos.stream().filter(t -> t.getId() == id).findAny().get();
        logger.info("todo - {}", todo);
        return todo;
    }

    public Todo updateTod(int todoId, Todo todo){
        List<Todo> newUpdateList = todos.stream().map(t -> {
            if (t.getId() == todoId) {
                //perform update
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            } else {
                return t;
            }
        }).collect(Collectors.toList());

        todos = newUpdateList;
        todo.setId(todoId);
        return todo;
    }

    public void deleteTodo(int todoId){
        logger.info("DELETING TODO");
        List<Todo> newList = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
        todos=newList;
    }
}
