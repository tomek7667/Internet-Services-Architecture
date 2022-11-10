package com.tomaszdabrowski.lab3.configuration;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.service.CategoryService;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializedData {

  private CategoryService categoryService;

  @Autowired
  public InitializedData(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostConstruct
  private synchronized void init() {
    if (categoryService.findMany().size() == 0) {
      UUID category1uuid = UUID.fromString(
        "ed2d750e-190f-44a7-9035-c2e12bcf35bd"
      );
      UUID category2uuid = UUID.fromString(
        "02b48bbe-f84d-4f9d-be39-8406969c2cfe"
      );
      UUID category3uuid = UUID.fromString(
        "ad9171f8-44f5-4d54-a81a-e2deda3fce03"
      );
      UUID category4uuid = UUID.fromString(
        "8845aff8-4a09-4b6b-899d-8c2723ead3ff"
      );
      UUID category5uuid = UUID.fromString(
        "6f4e1358-d822-4247-9a8b-152f513ba963"
      );
      UUID category6uuid = UUID.fromString(
        "6ac180af-714f-495f-a874-50ccda747f1a"
      );

      Category category1 = Category
        .builder()
        .id(category1uuid)
        .name("Reverse Engineering")
        .description(
          "Reverse engineering is the process of discovering the technological principles of a device, object, or system through analysis of its structure, function, and operation."
        )
        .build();
      Category category2 = Category
        .builder()
        .id(category2uuid)
        .name("Web Exploitation")
        .description(
          "Web exploitation is the process of exploiting vulnerabilities in web applications and web services."
        )
        .build();
      Category category3 = Category
        .builder()
        .id(category3uuid)
        .name("Cryptography")
        .description(
          "Cryptography is the practice and study of techniques for secure communication in the presence of third parties called adversaries."
        )
        .build();
      Category category4 = Category
        .builder()
        .id(category4uuid)
        .name("Binary Exploitation")
        .description(
          "Binary exploitation is the process of exploiting vulnerabilities in binary programs."
        )
        .build();
      Category category5 = Category
        .builder()
        .id(category5uuid)
        .name("Forensics")
        .description(
          "Forensics is the process of uncovering hidden information from a digital artifact."
        )
        .build();
      Category category6 = Category
        .builder()
        .id(category6uuid)
        .name("Miscellaneous")
        .description(
          "Miscellaneous is a category for tasks that do not fit into any other category."
        )
        .build();

      categoryService.createOne(category1);
      categoryService.createOne(category2);
      categoryService.createOne(category3);
      categoryService.createOne(category4);
      categoryService.createOne(category5);
      categoryService.createOne(category6);
    }
  }
}
