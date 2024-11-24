package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.Book;
import com.example.quiz2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookServices;

    @GetMapping("/get")
    public ResponseEntity getBooks(){
        ArrayList<Book> books = bookServices.getBooks();
        return ResponseEntity.status(200).body(books);
    }

    @PostMapping("/add")
    public ResponseEntity addBooks(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        bookServices.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("bok added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book, Errors errors){
        boolean isUpdated = bookServices.updateBook(id, book);
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("book updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        boolean isDeleted = bookServices.deleteBook(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("book deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }

    @GetMapping("/get/book/{name}")
    public ResponseEntity getOneBook(@PathVariable String name){
        Book book = bookServices.getOneBook(name);
        if(book.equals(null))
            return ResponseEntity.status(400).body(new ApiResponse("book name not found"));

        return ResponseEntity.status(200).body(book);
    }

    @GetMapping("/same/category/{category}")
    public ResponseEntity getSameCategory(@PathVariable String category){
        ArrayList<Book> sameCategoryBooks = bookServices.getSameCategory(category);
        if(!(category.equalsIgnoreCase("novel") || category.equalsIgnoreCase("academic")))
            return ResponseEntity.status(400).body(new ApiResponse("enter correct category. must be either novel or academic"));
//
        return ResponseEntity.status(200).body(sameCategoryBooks);
    }

    @GetMapping("/same/pages/{number_of_pages}")
    public ResponseEntity getSamePagesOrAbove(@PathVariable int number_of_pages){
        ArrayList<Book> samePagesOrAboveBooks = bookServices.getSamePagesOrAbove(number_of_pages);

        if(number_of_pages < 0)
            return ResponseEntity.status(400).body(new ApiResponse("enter valid number of pages. cannot be less than zero"));

        return ResponseEntity.status(200).body(samePagesOrAboveBooks);
    }

    @PutMapping("/change/status/{role}/{bookID}")
    public ResponseEntity changeBookStatus(@PathVariable String role, @PathVariable String bookID){
        boolean isChanged = bookServices.changeBookStatus(role, bookID);
        if(!role.equalsIgnoreCase("librarian")){
            return ResponseEntity.status(400).body(new ApiResponse("only librarian can change book status"));
        }
        if(isChanged)
            return ResponseEntity.status(200).body(new ApiResponse("book status changed successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("book id not found"));
    }
}








