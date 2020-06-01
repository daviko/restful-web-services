package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
// @RequestMapping("/api")
public class TodoJpaResource {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping(path = "/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName) {
        return todoJpaRepository.findByUserName(userName);
    }

    @GetMapping(path = "/users/{userName}/todos/{id}")
    public Todo getTodo(@PathVariable String userName, @PathVariable long id) {
        return todoJpaRepository.findById(id).orElse(null);
    }

    @DeleteMapping(path = "/users/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String userName, @PathVariable long id) {
        todoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/users/{userName}/todos")
    public ResponseEntity<Todo> saveTodo(@PathVariable String userName, @RequestBody Todo todo) {
        Todo todoUpdated = todoJpaRepository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoUpdated.getId())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable long id,
        @RequestBody Todo todo) {
        Todo todoUpdated = todoJpaRepository.save(todo);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }

}
