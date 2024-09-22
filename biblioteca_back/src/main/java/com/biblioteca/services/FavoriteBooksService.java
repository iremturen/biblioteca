package com.biblioteca.services;

import com.biblioteca.models.FavoriteBooks;
import com.biblioteca.repositories.interfaces.IFavoriteBooksRepository;
import com.biblioteca.services.interfaces.IFavoriteBooksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class FavoriteBooksService implements IFavoriteBooksService {

    private IFavoriteBooksRepository favoriteBooksRepository;

    @Override
    public List<FavoriteBooks> getFavorites(Integer userId) {
        return favoriteBooksRepository.getFavorites(userId);
    }

    @Override
    public void remove(Integer userId, Integer bookId) {
        favoriteBooksRepository.remove(userId,bookId);
    }

    @Override
    public void addBook(Integer userId, Integer bookId) {
        favoriteBooksRepository.addBook(userId, bookId);
    }

}
