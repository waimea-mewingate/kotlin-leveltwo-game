/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   200DTD Game- Pinned
 * Project Author: Maia Wingate
 * GitHub Repo:    https://github.com/waimea-mewingate/kotlin-leveltwo-game/tree/main
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * =====================================================================
 */

val gameBoard = mutableListOf<String>()
val playerNames = mutableListOf<String>()

fun main() {
    //Filling the list
    repeat(16) {
        gameBoard.add("")
    }
    //Welcome screen and counter setup on the board
    setupCounters()
    println("Welcome to Pinned!".bold())
    println("This is a two-player game. Choose who is who now.")
    showGameBoard()
    choosePlayers()
    for (players in playerNames) {
        println("Welcome, $players")
    }
    val randomNumber = (1..2).random()
    print("Starting Player: "); println(playerNames[randomNumber].bold())

}

fun showGameBoard() {
    println("╒═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╕".green())
    for (i in 0..15) {
        print("│".green()); print(gameBoard[i].padEnd(3))
    }
    println("│".green())
    println("╘═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╛".green())
    println(" 1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16")
}

fun setupCounters() {
    while (true) {
        //Adding Black Counter
        for (i in 0..gameBoard.size) {
            val randomIndex = gameBoard.indices.random()
            if (gameBoard[randomIndex] == "") gameBoard[randomIndex] = " ○"; break
        }
        //Adding White Counters (also keeping track of the number of them)
        var counterCount = 0
        for (i in 0..gameBoard.size) {
            val randomIndex = gameBoard.indices.random()
            if (gameBoard[randomIndex] == "") {
                gameBoard[randomIndex] = " ●"
                counterCount++
            }
            if (counterCount == 4) {
                break
            }
        }
        break
    }
}

fun moveOrRemove() {
    //UNFINISHEDDDDDDDD
    //still working out how this works - started something and then realised it goes the wrong way
    while (true)
        if (gameBoard[0] != "") {
            print("M".bold().green()); println("ove")
            print("R".bold().green()); println("emove Counter")
            val turnChoice = readlnOrNull()
            if (turnChoice != null && turnChoice in "MmRr") {
                break
            }
        }
         else {
            println("Moving is the only option: Enter square with counter to move:")
            val squareChosen = readlnOrNull()?.toIntOrNull()
            if (squareChosen != null && gameBoard[squareChosen - 1] != "") {
                println("Enter amount of squares to move: ")
                val squaresMoving = readlnOrNull()?.toIntOrNull()
                if (squaresMoving != null && gameBoard[squareChosen] != "") {}
            }
        }
    }


fun choosePlayers(){
    while (true) {
        print("Enter First Player Name: ")
        println()
        val playerOne = readlnOrNull()
        print("Enter Second Player Name: ")
        println()
        val playerTwo = readlnOrNull()
        if (playerOne != null && playerTwo != null) {
            playerNames.add(playerOne)
            playerNames.add(playerTwo)
            break
        }
    }
}