package com.backlog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    PresentationLayer presentationLayer;
    @Mock
    DataAccessLayer dataAccess;


    @Test
    public void dontSaveDuplicatedBook() {
        Book book = new Book("Bla", "bla");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        libraryService.saveBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("Book is already in the db");
    }

    @Test
    public void savesDublpicatedBook() {
        Book book = new Book("Bla", "bla");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.checkBookExists(book)).thenReturn(false);
        libraryService.saveBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("Book " + book.getTitle() + " saved successfully");
    }

    @Test
    public void cantBorrowBorrowedBook() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is already borrowed");
    }

    @Test
    public void canBorrowAvailableBook() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("Book " + book.getTitle() + " borrowed");
    }

    @Test
    public void cantReturnBookThatDoesntExist() {
        Book book = new Book("Bla", "bla");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.checkBookExists(book)).thenReturn(false);
        libraryService.returnBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("There's no such book in the Database");
        Mockito.verify(dataAccess, never()).returnBook(any(), any());
    }

    @Test
    public void cantReturnBookThatIsNotBorrowed() {
        Book book = new Book("Bla", "bla");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.returnBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("This book is not currently borrowed");
        Mockito.verify(dataAccess, never()).returnBook(any(), any());
    }

    @Test
    public void bookCanBeReturnedSuccessfullyWhenReturnedInTime() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        when(dataAccess.getReturnByDate(book)).thenReturn(LocalDate.now().plusWeeks(1));
        libraryService.returnBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is returned successfully");
    }

    @Test
    public void fineUserReturnedABookIfItsReturnedLate() {
        Book book = new Book("Bla", "bla");
        User user = new User("user");
        LibraryService libraryService = new LibraryService();
        when(dataAccess.availableToBorrow(book)).thenReturn(true);
        libraryService.borrowBook(book, user, dataAccess, presentationLayer);
        when(dataAccess.checkBookExists(book)).thenReturn(true);
        when(dataAccess.availableToBorrow(book)).thenReturn(false);
        when(dataAccess.getReturnByDate(book)).thenReturn(LocalDate.now().minusDays(3));
        libraryService.returnBook(book, dataAccess, presentationLayer);
        Mockito.verify(presentationLayer).printSuccess("User fined £3");
        Mockito.verify(dataAccess).returnBook(book, LocalDate.now());
        Mockito.verify(presentationLayer).printSuccess("Book " + book.getTitle() + " is returned successfully");
    }


}
