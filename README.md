# Minesweeper Game

[![Coverage Status](https://coveralls.io/repos/github/JonasSeifried/Minesweeper/badge.svg?branch=main)](https://coveralls.io/github/JonasSeifried/Minesweeper?branch=main)
[![Scala CI](https://github.com/JonasSeifried/Minesweeper/actions/workflows/scala.yml/badge.svg)](https://github.com/JonasSeifried/Minesweeper/actions/workflows/scala.yml)

### This Project was made for the lecture Software Engineering at [Htwg Konstanz](https://www.htwg-konstanz.de/).
Made in collaboration with [Berrak Uzun](https://github.com/berrakuzun).

![Minesweeper](img_1.png)

This is a command-line implementation of the classic game Minesweeper, written in [Scala](https://scala-lang.org/). The objective of the game is to clear the minefield without detonating any mines.

## Rules

The game board consists of a grid of cells, some of which contain hidden mines. The player's task is to uncover all the cells that do not contain mines, using clues provided by the neighboring cells.

Each cell can have one of the following states:

- **Unrevealed**: Initially, all cells are unrevealed. They are denoted by a hidden.
- **Revealed**: When a cell is clicked or uncovered, it reveals its content or clues about adjacent mines. An empty cell is represented by a blank symbol, while a cell with a clue shows the number of neighboring mines.
- **Flagged**: The player can flag a cell that they suspect contains a mine. Flagged cells are denoted by a flag symbol.

The game progresses as follows:

1. The player selects a cell to uncover by providing the coordinates (row and column) of the desired cell.
2. If the selected cell contains a mine, the game ends, and the player loses.
3. If the selected cell does not contain a mine, it reveals itself along with any adjacent empty cells or clues.
4. If an empty cell is revealed, all of its adjacent cells are automatically uncovered recursively until reaching cells with clues.
5. The player continues uncovering cells until either all non-mine cells are revealed (win) or a mine is uncovered (loss).

## Getting Started

To run the Minesweeper game, you need to have Scala and sbt (Scala Build Tool) installed on your system. Follow these steps to get started:

1. Clone the repository or download the source code.
2. Open a terminal and navigate to the project directory.
3. Build the project using sbt by running the following command:
   ```
   sbt compile
   ```
4. Run the game using the following command:
   ```
   sbt run
   ```

## Controls

Uncover a cell: Click on a cell to uncover it.
Flag a cell: Right-click on a cell to flag it.
Quit the game: Click on "File" and then select "Exit" to quit the game.


## License

This Minesweeper game is licensed under the [MIT License](LICENSE).
