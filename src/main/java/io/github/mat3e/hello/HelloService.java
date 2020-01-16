package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class HelloService {

    static final String FALBACK_NAME = "World";
    static final Lang FALBACK_LANG = new Lang(1, "Hello", "en");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;


    HelloService(){
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }


    String prepareGreeting (String name, String lang){
        Integer langId;
        try {
            langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALBACK_LANG.getId());
        } catch (NumberFormatException e){
            logger.warn("Non-numeric language id used: " + lang);
            langId = FALBACK_LANG.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }



}