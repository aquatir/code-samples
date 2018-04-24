package helloworld_backend.controller;

import helloworld_backend.domain.Hero;
import helloworld_backend.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HeroesController {

    private HeroRepository heroRepository;

    public HeroesController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @GetMapping("/heroes")
    public List<Hero> getAllHeroes() {

        return (List<Hero>) heroRepository.findAll();
    }
}
