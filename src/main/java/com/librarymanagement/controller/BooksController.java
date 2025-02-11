package com.librarymanagement.controller;

import com.librarymanagement.component.SwaggerAnnotations;
import com.librarymanagement.domain.request.BooksRequestDto;
import com.librarymanagement.domain.response.BooksResponseDto;
import com.librarymanagement.service.BookIssueReturnImpl;
import com.librarymanagement.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/V1/books")
@RestController
@Tag(name = "Books-Controller", description = "Controller managing operations related to books")
public class BooksController {
    @Autowired
    private BooksService service;
    @Autowired
    BookIssueReturnImpl bookIssueReturnImpl;




    @Operation(
            summary = "Adds a new book",
            description = "An endpoint used by administrators to add a new book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully added"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @PostMapping()
    public ResponseEntity<String> addBooks(@Valid @RequestBody BooksRequestDto booksDto){
        service.saveBook(booksDto);
        return ResponseEntity.ok("Book Successfully added");
    }


    @Operation(
            summary = "Lists all books",
            description = "An endpoint used to list all books.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access")
            }
    )
    @GetMapping()
    public ResponseEntity<List<BooksResponseDto>> findAllBooks(){
        List<BooksResponseDto> allBooks = service.getBooks();
        return ResponseEntity.ok(allBooks);
    }


    @Operation(
            summary = "Searches for a book by ID",
            description = "Searches for a specific book by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BooksResponseDto> findBookById(@PathVariable UUID id){
        BooksResponseDto book = service.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @Operation(
            summary = "Searches for a book by name",
            description = "Searches for a specific book by its name.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<BooksResponseDto>> findBookByName(@RequestParam String bookName){
        List<BooksResponseDto> book = service.getBookByName(bookName);
        return ResponseEntity.ok(book);
    }



    @Operation(
            summary = "Updates a book",
            description = "Updates the information of an existing book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable UUID id, @RequestBody BooksRequestDto booksDto){
        BooksResponseDto updatedBook = service.updateBook(id, booksDto);
        return ResponseEntity.ok("Book Successfully updated");
    }


    @Operation(
            summary = "Deletes a book",
            description = "Deletes an existing book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        String result = service.deleteBook(id);
        return ResponseEntity.ok("Book Successfully deleted");
    }



    @Operation(
            summary = "Issues a book to a user",
            description = "Issues a book to a user identified by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "User or book not found")
            }
    )
    @PutMapping("/{userId}/issue/{bookId}")
    public ResponseEntity<String> bookIssueControlHandler(
            @PathVariable("userId") UUID userId,
            @PathVariable("bookId") UUID bookId){

        LocalDate date = bookIssueReturnImpl.issueBook(userId, bookId);
        String responseMessage = "Book deadline: " + date.toString();

        return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
    }


    @Operation(
            summary = "Returns a book",
            description = "Returns a book issued by a user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "User or book not found")
            }
    )
    @PutMapping("/{userId}/return/{bookId}")
    public ResponseEntity<String> bookReturnControlHandler(
            @PathVariable("userId") UUID userId,
            @PathVariable("bookId") UUID bookId){

        Integer response = bookIssueReturnImpl.returnBook( userId, bookId);
        String responseMessage = "Fine amount: " + response.toString();

        return new ResponseEntity<String>(responseMessage, HttpStatus.OK);
    }



}
