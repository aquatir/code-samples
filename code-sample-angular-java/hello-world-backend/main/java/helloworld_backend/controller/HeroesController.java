package helloworld_backend.controller;

import helloworld_backend.dto.Hero;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeroesController {

    @GetMapping("/hero")
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>() {};
        heroes.add(new Hero(1, "Ivan"));
        heroes.add(new Hero(2, "Narkoman"));

        return heroes;
    }
}
