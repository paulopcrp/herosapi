package com.github.paulopcrp.heroesapi.herosapi.repository;

import com.github.paulopcrp.heroesapi.herosapi.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String>{
}
