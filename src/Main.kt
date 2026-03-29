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
var gameWon = false
var firstTurn = true
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

    val randomNumber = (1..2).random()
    print("Starting Player: ".yellow()); println(playerNames[randomNumber-1].bold())

    while (true) {
        if (gameWon) break
        moveOrRemove()
    }
    println("We have a Winner!")
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
    var action: String?
    while (true) {
        println("Choose an Action".red().bold())
        print("M".bold().green())
        println("ove")
        print("R".bold().green()); println("emove Counter")
        print("Choice: ")
        println()
        val turnChoice = readln()

        action = when (turnChoice) {
            "M", "m" -> "Move"
            "R", "r" -> "Remove"
            else -> null
        }
        if (action == "Remove" && gameBoard[0] == "") action = null
        if (action != null) break

        println("Sorry, Invalid Choice: Removing from square 1 may be impossible".red())

    }
    println("Action: $action")
    while (true) {
        if (action == "Remove") {
            when (gameBoard[0]) {
                " ●" -> {
                    gameBoard[0] = ""
                    showGameBoard()
                }
                " ○" -> {
                    gameBoard[0] = ""
                    showGameBoard()
                    gameWon = true
                    break
                }
            }
        }
        if (action == "Move") {
            println("Enter the Number of the Square You Want To Move From:")
        }
    }
}

fun choosePlayers(){
    while (true) {
        print("Enter First Player Name: ")
        println()
        val playerOne = readlnOrNull()
        if (playerOne != null) {
            playerNames.add(playerOne)
            println("Welcome, $playerOne")
            break
        }
    }
    while (true) {
        print("Enter Second Player Name: ")
        println()
        val playerTwo = readlnOrNull()
        if (playerTwo != null) {
            playerNames.add(playerTwo)
            println("Welcome, $playerTwo")
            break
        }
    }
}