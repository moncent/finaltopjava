package ru.javaops.topjava;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.javaops.topjava.service.UserService;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-db.xml", "spring/spring-app.xml", "spring/spring-mvc.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            String adminPass = new BCryptPasswordEncoder().encode("admin");
            String user1Pass = new BCryptPasswordEncoder().encode("password1");
            String user2Pass = new BCryptPasswordEncoder().encode("password2");

            System.out.println(adminPass); // $2a$10$h7MLerDKrQ4e87BMvQC9weqG6crqgBNxFk/TLLf2YgoYtSMlR./Pq
            System.out.println(user1Pass); // $2a$10$faD.ya906aTTEk.S201vJeXl8kbMFxWrGT2WG6ZAhlu2SeBW226XO
            System.out.println(user2Pass); // $2a$10$d8BQjDenE4DAi24QYx3siuVRulKX9mlCMDMdHaKgfQVKwcFnmSgle

            System.out.println("--------------------------------");

            UserService userService = appCtx.getBean(UserService.class);
            System.out.println(userService.get(100000).getName());
        }
    }
}
