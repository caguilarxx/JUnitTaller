package com.awi.pocs.Mockito.repository;

import com.awi.pocs.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
