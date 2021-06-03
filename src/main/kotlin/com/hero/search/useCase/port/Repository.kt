package com.hero.search.useCase.port

import com.hero.search.domain.Superhero

interface Repository {

    fun findAll(): Set<Superhero>?

    fun findBySuperHeroName(superHeroName: String): Superhero?

    fun insertSuperHero(superhero: Superhero): Superhero?
}