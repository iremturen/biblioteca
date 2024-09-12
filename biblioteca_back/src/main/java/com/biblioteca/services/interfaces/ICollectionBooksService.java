package com.biblioteca.services.interfaces;

import org.springframework.validation.annotation.Validated;

@Validated
public interface ICollectionBooksService {

    Integer getBookCount(Integer collectionId);
}
