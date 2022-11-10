package com.tomaszdabrowski.lab3.ctftask.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MinifiedCategory {

  public String name;
  public UUID id;
}
