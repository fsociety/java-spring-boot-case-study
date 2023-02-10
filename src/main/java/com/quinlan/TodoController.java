package com.quinlan;

import com.quinlan.Entity.Todo;
import com.quinlan.Repository.TodoRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public List<Todo> todos()
    {
        return todoRepository.findAll();
    }

    record TodoRequest(
            String title,
            String description,
            Boolean done
    ){}
    @PostMapping(value = "/todo/add", produces = "application/json")
    public ResponseEntity<String> addTodo(@RequestBody TodoRequest request)
    {
        try {
            Todo todo = new Todo();
            todo.setTitle(request.title);
            todo.setDescription(request.description);
            todo.setDone(request.done);
            todoRepository.save(todo);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(todo.toJson());
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"title\": "+ request.title +" ," +
                            "\"msg\": "+ e.toString() +", " +
                            "\"error\": true " +
                            "}");
        }
    }

    @PostMapping(value = "/todo/update/{todoId}", produces = "application/json")
    public ResponseEntity<String> updateTodo(@PathVariable("todoId") Integer id, @RequestBody TodoRequest request)
    {
        try {
            Todo todo = todoRepository.findById(id).orElse(null);
            assert todo != null;
            todo.setTitle(request.title);
            todo.setDescription(request.description);
            todo.setDone(request.done);
            todoRepository.save(todo);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("{\"id\": "+ id +" ," +
                    "\"success\": true, " +
                    "\"msg\": \"Successfully Updated.\" " +
                    "}");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": "+ e.toString() +"}");
        }
    }

    @DeleteMapping(value = "/todo/destroy/{todoId}", produces = "application/json")
    public ResponseEntity<String> deleteTodo(@PathVariable("todoId") Integer id)
    {
        try {
            todoRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("{\"id\": "+ id +" ," +
                            "\"success\": true, " +
                            "\"msg\": \"Deleted.\" " +
                            "}");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": "+ e.toString() +"}");
        }
    }

    @GetMapping("/todo/{todoId}")
    public Optional<Todo> getTodo(@PathVariable("todoId") Integer id)
    {
        return todoRepository.findById(id);
    }
}
