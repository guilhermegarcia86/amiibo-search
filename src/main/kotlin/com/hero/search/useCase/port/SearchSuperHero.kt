package com.hero.search.useCase.port

import com.hero.search.domain.Superhero
import com.hero.search.useCase.port.exception.SuperheroNotFoundException

class SearchSuperHero(private val repository: Repository, private val api: Api) {

    fun searchSuperHeroByName(superheroName: String): Superhero {

        repository.findBySuperHeroName(superheroName)
            .takeIf { superhero: Superhero? -> superhero != null }
            .apply {
            //TODO implements business rule
            //Mapper superhero returned and present to API request
            return this!!
        } ?: api.searchSuperHeroByName(superheroName).takeIf { superhero: Superhero? ->  superhero != null }.apply {
            //TODO Save superhero found and present to request API
            return this!!
        } ?: throw SuperheroNotFoundException("Superhero $superheroName not found")

    }
}