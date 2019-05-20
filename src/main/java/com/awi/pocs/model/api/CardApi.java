package com.awi.pocs.model.api;

import com.awi.pocs.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardApi {

  private Integer id;
  private String number;
  private Boolean active;

}
