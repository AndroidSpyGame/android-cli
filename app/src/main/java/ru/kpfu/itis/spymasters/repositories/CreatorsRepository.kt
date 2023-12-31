package ru.kpfu.itis.spymasters.repositories

import ru.kpfu.itis.spymasters.R
import ru.kpfu.itis.spymasters.entities.Creator

object CreatorsRepository {
    val creatorsList: List<Creator> = listOf(
        Creator(
            "Ислам",
            "Багавиев",
            "@Weirdn3ss",
            R.drawable.developer
        ),

        Creator(
            "Эвелина",
            "Дереженко",
            "@EviM6",
            R.drawable.developer
        ),

        Creator(
            "Булат",
            "Субуханкулов",
            "@ForeverestlllBulat",
            R.drawable.developer
        ),

        Creator(
            "Георгий",
            "Козобродов",
            "@vaihdass",
            R.drawable.developer
        )
    )
}