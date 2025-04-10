package com.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RatingAverage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer bookId;
    private Double averageRating;
    private Date updatedAt;

    public RatingAverage(Integer bookId, Double averageRating, Date updatedAt) {
        this.bookId = bookId;
        this.averageRating = averageRating;
        this.updatedAt = updatedAt;
    }
}
