package com.tomaszdabrowski.lab3.ctftask.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
  @Basic(optional = false)
  @Column(name = "id", unique = true, nullable = false, length = 16)
  private UUID id;

  @Basic(optional = false)
  @Column(name = "name", nullable = false, length = 255)
  private String name;

  private String description;
  private Integer points;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ToString.Exclude
  private String flag;

  public static MinifiedTask asMinifiedTask(Task task) {
    return MinifiedTask.builder().id(task.getId()).name(task.getName()).build();
  }
}
