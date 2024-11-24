package com.example.quiz2.Service;

import com.example.quiz2.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean updateBook(String id, Book book){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equals(id)){
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equals(id)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public Book getOneBook(String name){
        for(Book book : books){
            if(book.getName().equalsIgnoreCase(name)){
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getSameCategory(String category){
        ArrayList<Book> sameCategoryBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getCategory().equalsIgnoreCase(category)){
                sameCategoryBooks.add(book);
            }
        }
        return sameCategoryBooks;
    }

    public ArrayList<Book> getSamePagesOrAbove(int number_of_pages){
        ArrayList<Book> samePagesOrAboveBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getNumber_of_pages() >= number_of_pages){
                samePagesOrAboveBooks.add(book);
            }
        }
        return samePagesOrAboveBooks;
    }

    public boolean changeBookStatus(String role, String bookID){
        for(Book book : books){
            if(book.getId().equals(bookID)){
                book.setAvailable(false);
                return true;
            }
        }
        return false;
    }







}
