package com.github.paulopcrp.heroesapi.herosapi.controller;

import com.github.paulopcrp.heroesapi.herosapi.document.Heroes;
import com.github.paulopcrp.heroesapi.herosapi.repository.HeroesRepository;
import com.github.paulopcrp.heroesapi.herosapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.github.paulopcrp.heroesapi.herosapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger log=
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesRepository= heroesRepository;
        this.heroesService= heroesService;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems() {
        log.info("Requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/id")
    public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id) {
        log.info("requesting the hero with id {}", id);
        return heroesService.findById(id).map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("a new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/id")
    @ResponseStatus(code = HttpStatus.CONTINUE)
    public Mono<HttpStatus> deletedbyHero(@PathVariable String id) {
        heroesService.deleteByHero(id);
        log.info("deleting a hero with id {}", id);
        return Mono.just(HttpStatus.CONTINUE);
    }

}
