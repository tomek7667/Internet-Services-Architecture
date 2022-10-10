package com.tomaszdabrowski.lab1.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.ctftask.service.CategoryService;
import com.tomaszdabrowski.lab1.ctftask.service.TaskService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class InitializedData {
        private CategoryService categoryService;
        private TaskService taskService;

        @Autowired
        public InitializedData(CategoryService categoryService, TaskService taskService) {
                this.categoryService = categoryService;
                this.taskService = taskService;
        }

        @PostConstruct
        private synchronized void init() {
                if (categoryService.findMany().size() == 0) {
                        Category category1 = Category.builder()
                                        .id(1L)
                                        .name("Reverse Engineering")
                                        .description("Reverse engineering is the process of discovering the technological principles of a device, object, or system through analysis of its structure, function, and operation.")
                                        .build();
                        Category category2 = Category.builder()
                                        .id(2L)
                                        .name("Web Exploitation")
                                        .description("Web exploitation is the process of exploiting vulnerabilities in web applications and web services.")
                                        .build();
                        Category category3 = Category.builder()
                                        .id(3L)
                                        .name("Cryptography")
                                        .description("Cryptography is the practice and study of techniques for secure communication in the presence of third parties called adversaries.")
                                        .build();
                        Category category4 = Category.builder()
                                        .id(4L)
                                        .name("Binary Exploitation")
                                        .description("Binary exploitation is the process of exploiting vulnerabilities in binary programs.")
                                        .build();
                        Category category5 = Category.builder()
                                        .id(5L)
                                        .name("Forensics")
                                        .description("Forensics is the process of uncovering hidden information from a digital artifact.")
                                        .build();
                        Category category6 = Category.builder()
                                        .id(6L)
                                        .name("Miscellaneous")
                                        .description("Miscellaneous is a category for tasks that do not fit into any other category.")
                                        .build();
                        categoryService.createOne(category1);
                        categoryService.createOne(category2);
                        categoryService.createOne(category3);
                        categoryService.createOne(category4);
                        categoryService.createOne(category5);
                        categoryService.createOne(category6);

                        Task task1 = Task.builder()
                                        .id(1L)
                                        .name("r3v 1")
                                        .description("This is a simple reverse engineering task. The flag is in the source code.")
                                        .category(category1)
                                        .points(100)
                                        .flag("flag{r3v3rs3_1s_3asy}")
                                        .build();
                        Task task2 = Task.builder()
                                        .id(2L)
                                        .name("r3v 2")
                                        .description("This is a medium reverse engineering task. The flag is in the source code.")
                                        .category(category1)
                                        .points(200)
                                        .flag("flag{r3v3rs3_1s_m3d1um}")
                                        .build();
                        Task task3 = Task.builder()
                                        .id(3L)
                                        .name("r3v 3")
                                        .description("This is a hard reverse engineering task. The flag is in the source code.")
                                        .category(category1)
                                        .points(300)
                                        .flag("flag{r3v3rs3_1s_h4rd}")
                                        .build();

                        Task task4 = Task.builder()
                                        .id(4L)
                                        .name("w3b 1")
                                        .description("This is a simple web exploitation task. The flag is when XSS is performed.")
                                        .category(category2)
                                        .points(100)
                                        .flag("flag{w3b_1s_3asy}")
                                        .build();
                        Task task5 = Task.builder()
                                        .id(5L)
                                        .name("w3b 2")
                                        .description("This is a medium web exploitation task. The flag is when DOM Clobbering is performed.")
                                        .category(category2)
                                        .points(200)
                                        .flag("flag{w3b_1s_m3d1um}")
                                        .build();
                        Task task6 = Task.builder()
                                        .id(6L)
                                        .name("w3b 3")
                                        .description("This is a hard web exploitation task. The flag is when SQL Injection is performed.")
                                        .category(category2)
                                        .points(300)
                                        .flag("flag{w3b_1s_h4rd}")
                                        .build();

                        Task task7 = Task.builder()
                                        .id(7L)
                                        .name("cryp 1")
                                        .description("This is a simple cryptography task. The flag is when RSA is cracked.")
                                        .category(category3)
                                        .points(100)
                                        .flag("flag{cryp_1s_3asy}")
                                        .build();
                        Task task8 = Task.builder()
                                        .id(8L)
                                        .name("cryp 2")
                                        .description("This is a medium cryptography task. The flag is when AES is cracked.")
                                        .category(category3)
                                        .points(200)
                                        .flag("flag{cryp_1s_m3d1um}")
                                        .build();
                        Task task9 = Task.builder()
                                        .id(9L)
                                        .name("cryp 3")
                                        .description("This is a hard cryptography task. The flag is when RSA is cracked.")
                                        .category(category3)
                                        .points(300)
                                        .flag("flag{cryp_1s_h4rd}")
                                        .build();

                        taskService.createOne(task1);
                        taskService.createOne(task2);
                        taskService.createOne(task3);
                        taskService.createOne(task4);
                        taskService.createOne(task5);
                        taskService.createOne(task6);
                        taskService.createOne(task7);
                        taskService.createOne(task8);
                        taskService.createOne(task9);
                }
        }

        @SneakyThrows
        private byte[] getResourceAsByteArray(String name) {
                try (InputStream is = this.getClass().getResourceAsStream(name)) {
                        return is.readAllBytes();
                }
        }
}
