package com.awi.pocs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

  private Integer id;
  private String number;
  private Boolean active;
  private List<Product> products;

}
