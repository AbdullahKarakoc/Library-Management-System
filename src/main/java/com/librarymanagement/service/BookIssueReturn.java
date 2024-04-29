package com.librarymanagement.service;

import java.time.LocalDate;
import java.util.UUID;

public interface BookIssueReturn {

    public LocalDate issueBook(String bookName , UUID userId);

    public Integer returnBook( UUID userId ,UUID bookId);


}
