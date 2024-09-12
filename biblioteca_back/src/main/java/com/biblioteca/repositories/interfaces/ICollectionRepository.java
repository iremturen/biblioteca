package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.Collections;

import java.util.List;

public interface ICollectionRepository {

    List<Collections> getCollecitonsByUserId(Integer userId);

    Collections getCollecitonById(Integer collectionId);
}