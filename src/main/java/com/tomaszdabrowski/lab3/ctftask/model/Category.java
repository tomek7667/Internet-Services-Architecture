package com.tomaszdabrowski.lab3.ctftask.model;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "categories")
public class Category implements Serializable {

  @Id
  @Basic(optional = false)
  @Column(name = "id", unique = true, nullable = false, length = 16)
  private UUID id;

  @Basic(optional = false)
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  private String description;

  public static MinifiedCategory asMinifiedCategory(Category category) {
    return MinifiedCategory
      .builder()
      .id(category.getId())
      .name(category.getName())
      .build();
  }
}
