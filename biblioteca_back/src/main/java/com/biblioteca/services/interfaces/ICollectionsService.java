package com.biblioteca.services.interfaces;

import com.biblioteca.models.Collections;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ICollectionsService {

    List<Collections> getCollectionsByUserId(Integer userId);
    Collections getCollectionById(Integer collectionId);
    Collections update(Integer collectionId, Collections collections);
    Collections create(Collections collections);
    void delete(Integer collectionId);
}
