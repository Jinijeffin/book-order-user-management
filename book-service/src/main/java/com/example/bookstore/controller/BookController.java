package com.example.bookstore.controller;

import com.example.bookstore.dto.BookRequestDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Books", description = "Book management APIs")
@RestController
@RequestMapping("/books")
public class BookController{
    @Autowired
    public BookService bookService;

    @Operation(summary = "Create a new book")
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto dto){
       BookResponseDto createdBook = bookService.createBook(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @Operation(summary = "Create a new book")
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }


    @Operation(summary = "Get book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id){
        BookResponseDto book=bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @Operation(summary = "Update a book")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto>updateBook(@PathVariable Long id, @RequestBody BookRequestDto dto){
    BookResponseDto updatedBook = bookService.updateBook(id,dto);
    return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
     }
    @PatchMapping("/{id}/add-stock")
    public ResponseEntity<BookResponseDto> addStock(@PathVariable Long id, @RequestParam int quantity){
        BookResponseDto addedStock=  bookService.addStock(id, quantity);
        return ResponseEntity.status(HttpStatus.OK).body(addedStock);
    }
    @PatchMapping("/{id}/reduce-stock")
    public ResponseEntity<String> reduceStock(@PathVariable Long id, @RequestParam int quantity){
          bookService.reduceStock(id, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Book reduced successfully");
    }

    @Operation(summary = "Delete a book")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
    bookService.deleteBook(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }

}