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
fun main() {

    //Welcome screen and counter setup on the board
    setupGame()
    println("Welcome to Pinned!".bold())
    println("This is a two-player game. Choose who is who now.")
    choosePlayers()

    var currentPlayer = (0..1).random()

    while (true) {
        showGameBoard()
        print(playerNames[currentPlayer].bold()); println(", it's your go...".yellow())
        moveOrRemove()
        if (gameWon) break

        currentPlayer = if (currentPlayer == 1) 0 else 1
    }

    println("${playerNames[currentPlayer]} has Won!")
//    while (true) {
//        println("End of Game. Play Again? [Y/N]")
//        val playAgain = readlnOrNull()
//        when (playAgain) {
//
//        }
//    }
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

fun setupGame() {
    //Filling the list
    repeat(16) {
        gameBoard.add("")
    }
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
            var movingFrom: Int
            while (true) {
                println("Enter the Number of the Square You Want To Move From:")
                val chosenSquare = readlnOrNull()?.toIntOrNull()
                if (chosenSquare != null && chosenSquare in (1..16) && gameBoard[chosenSquare - 1] != "") {
                    println("Moving From Square $chosenSquare")
                    movingFrom = chosenSquare
                    break
                }
                println("Invalid Input".red())
            }

            var numberToMove: Int?
            while (true) {
                while (true) {
                    print("How many squares to the Left? ")
                    numberToMove = readlnOrNull()?.toIntOrNull()
                    if (numberToMove != null && numberToMove in (1..16)) break
                    println("Invalid Input".red())
                }
                var emptyTester: Int? = 2
                if (emptyTester != null) {
                    repeat(numberToMove!!) {
                        if (gameBoard[movingFrom - emptyTester!!] == "") emptyTester =
                            emptyTester!! + 1 else emptyTester = null
                    }
                }
                if (emptyTester != null && gameBoard[movingFrom - emptyTester!!] == gameBoard[movingFrom - numberToMove!!]) break

            }
            if (gameBoard[movingFrom - 1] == " ●") {
                gameBoard[movingFrom - numberToMove!! - 1] = " ●"
            } else if (gameBoard[movingFrom - 1] == " ○")
        //I"M SORRY - IT HURTS MY BRAIN TOO, FUTURE SELF
        }
    }
}


fun choosePlayers() {
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

