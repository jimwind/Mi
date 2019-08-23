package com.up360.mi;

import com.up360.mi.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}