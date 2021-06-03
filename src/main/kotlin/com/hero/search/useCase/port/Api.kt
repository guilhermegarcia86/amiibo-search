package com.hero.search.useCase.port

import com.hero.search.domain.Superhero

interface Api {

    fun searchSuperHeroByName(superheroName: String): Superhero?
}