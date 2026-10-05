package com.backlog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    PresentationLayer presentationLayer;
    @Mock
    DataAccessLayer dataAccess;

    LibraryService libraryService = new LibraryService();

    @Test
    public void dontSaveDuplicatedBook() {
        Book book = new Book("Bla", "bla");
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        libraryService.saveBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Book is already in the db");
    }

    @Test
    public void savesDuplicatedBook() {
        Book book = new Book("Bla", "bla");
        when(dataAccess.checkBookExists(book)).thenReturn(false);
        libraryService.saveBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Book " + book.getTitle() + " saved successfully");
    }

    @Test
    public void cantBorrowBorrowedBook() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is already borrowed");
    }

    @Test
    public void canBorrowAvailableBook() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Book " + book.getTitle() + " borrowed");
    }

    @Test
    public void cantReturnBookThatDoesntExist() {
        Book book = new Book("Bla", "bla");
        when(dataAccess.checkBookExists(book)).thenReturn(false);
        libraryService.returnBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("There's no such book in the Database");
        verify(dataAccess, never()).returnBook(any(), any());
    }

    @Test
    public void cantReturnBookThatIsNotBorrowed() {
        Book book = new Book("Bla", "bla");
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.returnBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("This book is not currently borrowed");
        verify(dataAccess, never()).returnBook(any(), any());
    }

    @Test
    public void bookCanBeReturnedSuccessfullyWhenReturnedInTime() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        when(dataAccess.getReturnByDate(book)).thenReturn(LocalDate.now().plusWeeks(1));
        libraryService.returnBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is returned successfully");
    }

    @Test
    public void fineUserReturnedABookIfItsReturnedLate() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        when(dataAccess.getReturnByDate(book)).thenReturn(LocalDate.now().minusDays(3));
        libraryService.returnBook(book, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("User fined £3");
        verify(dataAccess).returnBook(book, LocalDate.now());
        verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is returned successfully");
    }

    @Test
    public void listsBorrowedBooks() {
        Book book1 = new Book("Bla", "bla");
        Book book2 = new Book("Bla1", "bla1");
        List<Book> borrowedBooks = new ArrayList<>(Arrays.asList(book1, book2));
        User user = new User("user");
        when(dataAccess.listBorrowed(user)).thenReturn(borrowedBooks);
        libraryService.listBorrowed(user, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("Books borrowed by " + user.name() + ":");
        verify(presentationLayer).printSuccess(book1.getTitle() + " by " + book1.getAuthor());
        verify(presentationLayer).printSuccess(book2.getTitle() + " by " + book2.getAuthor());
    }

    @Test
    public void tellsUserHeHasntBorrowedAnyBook() {
        User user = new User("user");
        when(dataAccess.listBorrowed(user)).thenReturn(new ArrayList<>());
        libraryService.listBorrowed(user, dataAccess, presentationLayer);
        verify(presentationLayer).printSuccess("No books borrowed");
    }

    @Test
    void canBeBorrowed_bookDoesNotExist() {
        when(dataAccess.checkBookExists("Bla")).thenReturn(false);

        libraryService.canBeBorrowed("Bla", dataAccess, presentationLayer);

        verify(presentationLayer).printSuccess("No such books exist");
        verify(dataAccess, never()).availableToBorrow(anyString());
        verifyNoMoreInteractions(presentationLayer);
    }

    @Test
    void canBeBorrowed_bookIsAvailable() {
        when(dataAccess.checkBookExists("Bla")).thenReturn(true);
        when(dataAccess.availableToBorrow("Bla")).thenReturn(true);

        libraryService.canBeBorrowed("Bla", dataAccess, presentationLayer);

        verify(presentationLayer).printSuccess("Book with title Bla can be borrowed");
        verifyNoMoreInteractions(presentationLayer);
    }

    @Test
    void canBeBorrowed_bookIsAlreadyBorrowed() {
        when(dataAccess.checkBookExists("Bla")).thenReturn(true);
        when(dataAccess.availableToBorrow("Bla")).thenReturn(false);

        libraryService.canBeBorrowed("Bla", dataAccess, presentationLayer);

        verify(presentationLayer).printSuccess("Book with title Bla is borrowed by another user");
        verifyNoMoreInteractions(presentationLayer);
    }


}
