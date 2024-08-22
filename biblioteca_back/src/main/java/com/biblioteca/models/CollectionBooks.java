package com.biblioteca.models;

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
public class CollectionBooks implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer bookId;
    private Integer collectionId;
    private Date additionAt;
}
