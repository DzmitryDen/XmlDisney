package com.hfad.xmldisney.util

import com.hfad.xmldisney.R
import com.hfad.xmldisney.models.DisneyHero
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.models.Fields
import com.hfad.xmldisney.network.entity.DisneyHeroResponse
import com.hfad.xmldisney.network.entity.ListDisneyHeroResponse

fun ListDisneyHeroResponse.toDisneyHeroList(): List<DisneyHeroList> {
    val list = ArrayList<DisneyHeroList>()
    for (item in this.data) {
        list.add(
            DisneyHeroList(
                id = item._id,
                name = item.name,
                imageUrl = item.imageUrl
            )
        )
    }
    return list
}


fun DisneyHeroResponse.toDisneyHero(): DisneyHero {
    return this.data.run {
        val list = arrayListOf<Fields>()
        if (films.isNotEmpty()) {
            list.add(Fields(R.string.films, films))
        }
        if (parkAttractions.isNotEmpty()) {
            list.add(Fields(R.string.park_attractions, parkAttractions))
        }
        if (shortFilms.isNotEmpty()) {
            list.add(Fields(R.string.short_films, shortFilms))
        }
        if (tvShows.isNotEmpty()) {
            list.add(Fields(R.string.tv_shows, tvShows))
        }
        if (videoGames.isNotEmpty()) {
            list.add(Fields(R.string.video_games, videoGames))
        }
        DisneyHero(
            name = name,
            image = imageUrl,
            fields = list
        )
    }
}