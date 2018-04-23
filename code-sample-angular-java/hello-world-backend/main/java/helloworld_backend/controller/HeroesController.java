package helloworld_backend.controller;

import helloworld_backend.dto.Hero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class HeroesController {

    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {
        log.info("I'm being called");
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero(1, "Ivan"));
        heroes.add(new Hero(2, "Narkoman"));

        return heroes;
    }
}
