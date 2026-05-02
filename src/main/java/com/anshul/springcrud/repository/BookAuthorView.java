package com.anshul.springcrud.repository;

public interface BookAuthorView {
    Long getBookId();
    String getTitle();
    String getIsbn();
    int getPublishedYear();
    String getAuthorName();
    String getSpecialization();
}
