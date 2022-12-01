package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Task;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTaskDto {

  private UUID id;
  private String name;
  private String description;
  private int points;
  private UUID category;
  private String flagHash;

  public static String sha512(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] messageDigest = md.digest(input.getBytes());
      BigInteger no = new BigInteger(1, messageDigest);
      String hashtext = no.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static Function<Task, GetTaskDto> entityToDtoMapper() {
    return task ->
      GetTaskDto
        .builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .points(task.getPoints())
        .category(task.getCategory().getId())
        .flagHash(GetTaskDto.sha512(task.getFlag()))
        .build();
  }
}
