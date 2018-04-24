package helloworld_backend.controller;

import helloworld_backend.dto.Hero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeroesController {

    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero(1, "Batman"));
        heroes.add(new Hero(2, "Superman"));
        heroes.add(new Hero(3, "Wonder woman"));
        heroes.add(new Hero(4, "Manly man"));
        heroes.add(new Hero(5, "Ant man"));

        return heroes;
    }
}
