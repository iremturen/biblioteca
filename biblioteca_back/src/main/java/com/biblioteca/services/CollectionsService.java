package com.biblioteca.services;

import com.biblioteca.models.Collections;
import com.biblioteca.repositories.interfaces.ICollectionRepository;
import com.biblioteca.services.interfaces.ICollectionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Service
@Validated
public class CollectionsService implements ICollectionsService {

    private ICollectionRepository collectionRepository;
    @Override
    public List<Collections> getCollecitonsByUserId(Integer userId) {
        return collectionRepository.getCollecitonsByUserId(userId);
    }

    @Override
    public Collections getCollecitonById(Integer collectionId) {
        return collectionRepository.getCollecitonById(collectionId);
    }

    @Override
    public Collections update(Integer collectionId, Collections collections) {
        return collectionRepository.update(collectionId,collections);
    }

    @Override
    public Collections create(Collections collections) {
        return collectionRepository.create(collections);
    }

    @Override
    public void delete(Integer collectionId) {
        collectionRepository.delete(collectionId);
    }
}
