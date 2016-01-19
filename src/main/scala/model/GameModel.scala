package model


class GameModel  {

  var sudokuPuzzle : SudokuPuzzle = new SudokuPuzzle(Easy())
  var selectedSquare: Int = -1
  def newGame(diffculty : Difficulty): Unit ={
    sudokuPuzzle = new SudokuPuzzle(diffculty)
  }

}
