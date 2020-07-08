package com.awi.pocs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

  @Id
  private Integer id;

  private String number;

  private Boolean active;

  @Transient
  private List<Product> products;


  public Card(int i, String s, boolean b) {
    this.id = i;
    this.number = s;
    this.active = b;
  }
}
