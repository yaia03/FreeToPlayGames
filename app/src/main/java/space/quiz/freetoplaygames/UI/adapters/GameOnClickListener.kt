package space.quiz.freetoplaygames.UI.adapters

import space.quiz.freetoplaygames.Models.Game

interface GameOnClickListener {
    fun onClicked(game: Game)
}