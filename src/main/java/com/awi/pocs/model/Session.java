package com.awi.pocs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

  private int id;
  private String documentNumber;
  private boolean blacklisted;
}
