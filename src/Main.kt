/**
 * =====================================================================
 * Programming Project for NCEA Level 2, Standard 91896
 * ---------------------------------------------------------------------
 * Project Name:   200DTD Game- Pinned
 * Project Author: Maia Wingate
 * GitHub Repo:    https://github.com/waimea-mewingate/kotlin-leveltwo-game/tree/main
 * ---------------------------------------------------------------------
 * Notes:
 * Pinned 📌
 * Game Setup:
 *  A row of 16 squares, numbered 1 to 16 from left to right
 *  5 counters (total) are placed randomly on the board - 4 white and 1 black
 *  Decide who goes first
 * Gameplay:
 *  Players take turns - You may not skip your turn
 *  On your turn you must do exactly one of the following:
 *  Slide any counter (black or white) any number of squares to the left, as long as no other counter is in the way and the destination square is empty, or...
 *  Remove the counter on square 1 (only if a counter is there)
 * Win Condition:
 *  The player who removes the black counter from square 1 wins
 * * =====================================================================
 */

// The game board stores all the squares. This ends up being "" or one of the counters.
val gameBoard = mutableListOf<String>()
// Stores the player names in order to refer to them and avoid confusion.
val playerNames = mutableListOf<String>()
//The win condition, met when the black counter is removed from 'square 1' or gameBoard index 0.
var gameWon = false

//Main function. Contains the basic structure of the game - calls functions to set up, choose player names, and loop through turns. Once "gameWon" is fulfilled, it ends the loop and the game.
fun main() {
    //Welcome screen and counter setup on the board. Runs once.
    setupGame()
    println("Welcome to Pinned!".bold().yellow())
    println("This is a two-player game. Choose who is who now.")
    choosePlayers()
    showGameBoard()

    //Starting player.
    var currentPlayer = (0..1).random()

    //Taking turns (main game body). This should loop until win condition is met
    while (true) {
        // The game board is shown when performing a turn, so I have not put showGameBoard here.
        print(playerNames[currentPlayer].bold()); println(", it's your go...".yellow())
        moveOrRemove()
        //Check for win!
        if (gameWon) break

        //Swap player
        currentPlayer = if (currentPlayer == 1) 0 else 1
    }

    // Game won!
    println("${playerNames[currentPlayer]} has Won!".bold().green())
    println("Game Finished! Rerun program to play again :)")
    println("Thanks for playing!")
}
// End of Main loop. :)



fun showGameBoard() {
    //Prints the board squares and fills them with the mutableList "gameBoard".
    println("╒═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╕".green())
    for (i in 0..15) {
        print("│".green()); print(gameBoard[i].padEnd(3))
    }
    println("│".green())
    println("╘═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╛".green())
    println(" 1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16")
    // Squares are numbered ^
}

fun setupGame() {
    //Filling the list
    repeat(16) {
        gameBoard.add("")
    }
    while (true) {
        //Adding Black Counter - choose a random index - if empty, replace it with the black counter. Then break - we only need one!
        for (i in 0..gameBoard.size) {
            val randomIndex = gameBoard.indices.random()
            if (gameBoard[randomIndex] == "") gameBoard[randomIndex] = " ○"; break
        }
        //Adding White Counters until there are four
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
    // This is a particularly long function due to the need to carry variables across the different turn actions
    var action: String?
    while (true) {
        // Asks players to choose an action
        showGameBoard()
        println("Choose an Action".magenta().bold())
        println("(Counters can only be removed from square 1)")
        print("M".bold().green())
        println("ove. You can move any counter any number of squares to the left, provided the path is empty. ")
        print("R".bold().green()); println("emove Counter. This removes a counter from square 1, and can only be chosen when that square is occupied.")
        println("Removing the black counter '○' wins you the game! ")
        print("Choice (M/R): ")
        val turnChoice = readln()

        action = when (turnChoice) {
            "M", "m" -> "Move"
            "R", "r" -> "Remove"
            else -> null
        }
        if (action == "Remove" && gameBoard[0] == "") action = null
        if (action != null) break

        println("Sorry, Invalid or Unavailable Choice".red())

    }
    // Once an action is successfully chosen, either is carried out.
    println("Action: $action")
    while (true) {
        //Removing the counter on square 1
        if (action == "Remove") {
            when (gameBoard[0]) {
                " ●" -> {
                    gameBoard[0] = ""
                    showGameBoard()
                    println("Counter Removed!")
                    break
                }

                //This is the win condition - removing the black counter signals the game over
                " ○" -> {
                    gameBoard[0] = ""
                    showGameBoard()
                    gameWon = true
                    break
                }
            }
        }

        //Move function
        if (action == "Move") {
            var movingFrom: Int
            while (true) {
                // User input for targeting counters - they enter the square number containing their chosen counter.
                showGameBoard()
                print("Enter Target Square Number: ".yellow())
                val chosenSquare = readlnOrNull()?.toIntOrNull()

                // There are so many conditions to validate this - the input needs to be a square on the board containing a counter and there needs to be a valid square to move to
                if (chosenSquare != null && chosenSquare in (1..16) && gameBoard[chosenSquare - 1] != "" && gameBoard[chosenSquare - 2] == "") {
                    println("Moving From Square $chosenSquare")
                    //Since the user input is local to the 'while' loop, I have created a variable local to the 'moving' section and set it to the input once gained, so I can still use error-checking
                    // Probably should try to streamline it
                    movingFrom = chosenSquare -1
                    break
                }
                // If the input fails the error-check-
                println("Invalid Input".red())
            }
            // Making the move function repeat one step at a time was the best way that I could break it down- other tries found in the history of this code all got out of hand for me
            //It does work! :D
            println("Moving 1 Square Left...")
            while (true) {
                // Swap counters to move left as the empty squares are "" - still items in the gameboard list
                val temp = gameBoard[movingFrom - 1]
                gameBoard[movingFrom - 1] = gameBoard[movingFrom]
                gameBoard[movingFrom] = temp
                showGameBoard()
                //Now that they have swapped, if the current counter to be moved is on square 1 (gameBoard[0]) the loop has to be broken else a IndexOutOfBounds error appears
                if (movingFrom == 1) break

                if (gameBoard[movingFrom - 2] == "") {
                    // If possible to move left again, the question is posed until the user decides or the move is impossible.
                    var moveAgain: String?
                    while (true) {
                        println("Move another square? Y/N")
                        moveAgain = readlnOrNull()
                        if (moveAgain != null && moveAgain in "YyNn") break
                        println("Invalid Input")
                    }
                    when (moveAgain) {
                        "y", "Y" -> movingFrom = movingFrom - 1
                        // The above line can be done with a '-=' says the IntelliJ suggestion. Neat trick! Helpful to keep in mind for future reference.
                        "N", "n" -> break
                    }
                } else break
            }
            break
        }
    }
}


fun choosePlayers() {
    // Asks players for their names and stores them!
    while (true) {
        print("Enter First Player Name: ".bold())
        val playerOne = readlnOrNull()
        if (playerOne != null) {
            playerNames.add(playerOne)
            println("Welcome, $playerOne!")
            break
        }
    }
    while (true) {
        print("Enter Second Player Name: ".bold())
        val playerTwo = readlnOrNull()
        if (playerTwo != null) {
            playerNames.add(playerTwo)
            println("Welcome, $playerTwo!")
            break
        }
    }
}

