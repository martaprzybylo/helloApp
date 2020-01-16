package io.github.mat3e.hello;

import static org.junit.Assert.*;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;
import java.util.Optional;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hejka";

    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName(){
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(WELCOME + " " + HelloService.FALBACK_NAME + "!", result);
    }


    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var name = "Kasia";
        // when
        var result = SUT.prepareGreeting(name, -1);
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang(){
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALBACK_NAME + "!", result);
    }

//    @Test
//    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang(){
//        //given
//        var mockRepository = fallbackLangIdRepository();
//        var SUT = new HelloService(mockRepository);
//        // when
//        var result = SUT.prepareGreeting(null, "abc");
//        //then
//        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALBACK_NAME + "!", result);
//    }



    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository(){
            @Override
            public Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALBACK_LANG.getId())){
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
}
