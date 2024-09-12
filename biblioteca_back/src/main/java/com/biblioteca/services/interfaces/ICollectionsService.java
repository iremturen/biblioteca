package com.biblioteca.services.interfaces;

import com.biblioteca.models.Collections;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ICollectionsService {

    List<Collections> getCollecitonsByUserId(Integer userId);
    Collections getCollecitonById(Integer collectionId);
}
