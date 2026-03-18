/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: Maia Wingate
 * GitHub Repo:    GITHUB REPO URL HERE
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * =====================================================================
 */

val gameBoard = mutableListOf<String>()

fun main() {
    //Filling the list
    repeat(16) {
        gameBoard.add("..")
    }
    //Welcome screen and counter setup on the board
    println("Welcome to Pinned!")
    println("This is a two-player game. ")
    showGameBoard()
    val players = listOf("Player One, Player Two")
    // Googled how to do a random index
    val whoStarts = players.indices.random()
    println("Starting Player: ${players[whoStarts]}")
    //hmm, prints both players
}

fun showGameBoard() {
    println("╒═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╕")
    for (i in 0..15) {
        print("│"); print(gameBoard[i].padEnd(3))
    }
    println("│")
    println("╘═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╛")
}