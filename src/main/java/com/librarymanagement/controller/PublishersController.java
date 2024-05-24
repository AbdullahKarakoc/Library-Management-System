package com.librarymanagement.controller;
import com.librarymanagement.domain.request.PublishersRequestDto;
import com.librarymanagement.domain.response.PublishersResponseDto;
import com.librarymanagement.service.PublishersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/V1/publishers")
@Tag(name = "Publishers-Controller", description = "Controller managing operations related to publishers")
public class PublishersController {
    @Autowired
    private PublishersService publishersService;

    @Operation(
            summary = "Save a new publisher",
            description = "An endpoint used to save a new publisher.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Publisher successfully saved"),
                    @ApiResponse(responseCode = "404", description = "Error, publisher not saved")
            }
    )
    @PostMapping()
    public ResponseEntity<String> addPublisher(@Valid @RequestBody PublishersRequestDto publisherDto) {
        publishersService.savePublisher(publisherDto);
        return ResponseEntity.ok("Publisher successfully added");
    }

    @Operation(
            summary = "Get all publishers",
            description = "An endpoint used to list all publishers."
    )
    @GetMapping()
    public ResponseEntity<List<PublishersResponseDto>> getAllPublishers() {
        List<PublishersResponseDto> allPublishers = publishersService.getPublishers();
        return ResponseEntity.ok(allPublishers);
    }

    @Operation(
            summary = "Get publisher details by ID",
            description = "An endpoint used to get details of a publisher by their ID."
    )
    @GetMapping("/{publisherId}")
    public ResponseEntity<PublishersResponseDto> getPublisherById(@PathVariable UUID publisherId) {
        PublishersResponseDto publisher = publishersService.getPublisherById(publisherId);
        return ResponseEntity.ok(publisher);
    }

    @Operation(
            summary = "Update publisher",
            description = "An endpoint used to update an existing publisher by their ID."
    )
    @PutMapping("/{publisherId}")
    public ResponseEntity<String> updatePublisher(@PathVariable UUID publisherId, @Valid @RequestBody PublishersRequestDto updatedPublisherDto) {
        publishersService.updatePublisher(publisherId, updatedPublisherDto);
        return ResponseEntity.ok("Publisher successfully updated");
    }

    @Operation(
            summary = "Delete publisher",
            description = "An endpoint used to delete an existing publisher by their ID."
    )
    @DeleteMapping("/{publisherId}")
    public ResponseEntity<String> deletePublisher(@PathVariable UUID publisherId) {
        publishersService.deletePublisher(publisherId);
        return ResponseEntity.ok("Publisher successfully deleted");
    }
}
