package com.librarymanagement.service;

import com.librarymanagement.domain.model.Books;
import com.librarymanagement.domain.model.Members;
import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.exception.BookException;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.repository.BooksRepository;
import com.librarymanagement.repository.MemberRepository;
import com.librarymanagement.util.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class BookIssueReturnImpl implements BookIssueReturn {

    @Autowired
    private MemberRepository userRepository;

    @Autowired
    private BooksRepository bookRepository;



    @Override
    public LocalDate issueBook(UUID userId, UUID bookId) {

        userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));

        bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));

        Members member = userRepository.findById(userId).get();

        List<Books> booklist =  member.getBookList();

        if(booklist.size()<=5) {
            Books book = bookRepository.findById(bookId).get();

            if(book.isIssued()) {
                throw new BookException("Book already issue please issue other book");
            }

            book.setIssued(true);
            book.setBookIssueDate(LocalDate.now());
            book.setBookReturnDate(LocalDate.now().plusDays(10));
            book.setReceiver(member);
            book.setBookStatus(BookStatus.BORROWED);
            member.getBookList().add(book);
            bookRepository.save(book);
            userRepository.save(member);
        }
        else
        {
            throw new BookException("Number of Book issued by User is more than 5");
        }
        return LocalDate.now().plusDays(10);
    }



    @Override
    public Integer returnBook(UUID userId, UUID bookId) {

        int fineAmount = 0; // gecikme ücreti
        int fine = 10;

        userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.MEMBER_NOT_FOUND.getValue()));
        bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException(ErrorMessages.BOOK_NOT_FOUND.getValue()));

        Members user = userRepository.findById(userId).get();
        List<Books> bookList =  user.getBookList();
        Books bookReturn = bookRepository.findById(bookId).get();
        Iterator<Books> iterator = bookList.iterator();

        boolean flag = false;

        while(iterator.hasNext()) {

            Books bookItr = iterator.next();

            if(bookReturn.getId() == bookItr.getId()) {
                flag = true;
                LocalDate todayDate = LocalDate.now();
                LocalDate returnDate = bookReturn.getBookReturnDate();
                Period period = Period.between(returnDate, todayDate);
                int days = period.getDays();
                if(days>0) {
                    fineAmount = period.getDays()*fine;
                }
                break;
            }
        }
        if(flag) {
            bookList.remove(bookReturn);
            userRepository.save(user);
            bookReturn.setBookReturnDate(null);
            bookReturn.setBookIssueDate(null);
            bookReturn.setReceiver(null);
            bookReturn.setIssued(false);
            bookReturn.setBookStatus(BookStatus.IN_LIBRARY);
            bookRepository.save(bookReturn);
        }
        else
        {
            throw	new BookException("Book Not Found in User Account");
        }

        return fineAmount;
    }
}