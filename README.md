# Level Two Game - Pinned

Maia Wingate

---

This is a programming project for **NCEA Level 2**, assessed against standard [91896](task/as91896.pdf).

The project is written in the [Kotlin](https://kotlinlang.org) programming language and demonstrates a number of **advanced programming techniques**:
- Modifying data stored in collections
- Creating functions that use parameters and/or return values
---

## Project Files

- Program source code can be found in the [src](src/) folder
- Program test plan is in [test-plan.md](docs/test-plan.md) in the docs folder
- Program test evidence is in [test-results.md](docs/test-results.md) in the docs folder

---

### The game rules... 
## Pinned 📌

### Game Setup
- A row of 16 squares, numbered 1 to 16 from left to right
- 5 counters (total) are placed randomly on the board - 4 white and 1 black
- Decide who goes first

### Gameplay
- Players take turns - You may not skip your turn
- On your turn you must do exactly one of the following:
    - Slide any counter (black or white) any number of squares to the left, as long as no other counter is in the way and the destination square is empty, or...
    - Remove the counter on square 1 (only if a counter is there)

### Win Condition
- The player who removes the black counter from square 1 wins

### Variant
- Counters can slide either left or right (but still can't jump other counters)
