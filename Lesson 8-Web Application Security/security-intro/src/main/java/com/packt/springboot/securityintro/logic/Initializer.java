package com.packt.springboot.securityintro.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final AuthorService authorService;
    private final ShortMessageService sms;
    private final PasswordEncoder passwordEncoder;

    /**
     * After construction of the context, create a base
     * set of authors and their short messages.
     */
    @PostConstruct
    void prefillShortMessages() {
        Author admin = Author.builder().admin(true)
                .username("admin")
                .fullName("Administrator")
                .password(passwordEncoder.encode("admin"))
                .build();
        authorService.add(admin);
        Author peter = Author.builder()
                .username("peter")
                .fullName("Peter Quinn")
                .password(passwordEncoder.encode("quinn"))
                .build();
        authorService.add(peter);
        Author paul = Author.builder()
                .username("paul")
                .fullName("Paul Nipkow")
                .password(passwordEncoder.encode("nipkow"))
                .build();
        authorService.add(paul);
        Author cate = Author.builder()
                .username("cate")
                .fullName("Catherine Sakai")
                .password(passwordEncoder.encode("sakai"))
                .build();
        authorService.add(cate);

        sms.add(admin, LocalDateTime.now().minusDays(12), "Welcome to the message service!");
        sms.add(cate, LocalDateTime.now().minusDays(8), "While I was out there, I saw something. What was it?");
        sms.add(peter, LocalDateTime.now().minusDays(7), "My thoughts keep disappearing.");
        sms.add(paul, LocalDateTime.now().minusDays(6), "Television? The word is half Greek and half Latin. No good will come of it.");
        sms.add(cate, LocalDateTime.now().minusDays(4), "I don’t mean to alarm you, but your pants are talking to you.");
        sms.add(peter, LocalDateTime.now().minusDays(1), "For once in your life you need to listen!");
    }

}
