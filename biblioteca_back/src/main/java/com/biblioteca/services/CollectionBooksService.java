package com.biblioteca.services;

import com.biblioteca.repositories.interfaces.ICollectionBooksRepository;
import com.biblioteca.services.interfaces.ICollectionBooksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class CollectionBooksService implements ICollectionBooksService {

    private ICollectionBooksRepository collectionBooksRepository;

    @Override
    public Integer getBookCount(Integer collectionId) {
        return collectionBooksRepository.getBookCount(collectionId);
    }
}
