package com.librarymanagement.service;

import java.time.LocalDate;
import java.util.UUID;

public interface BookIssueReturn {

    public LocalDate issueBook(UUID userId ,UUID bookId);

    public Integer returnBook( UUID userId ,UUID bookId);


}
