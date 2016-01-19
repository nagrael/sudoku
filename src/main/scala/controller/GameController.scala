package controller

import model.{SudokuPuzzle, _}
import view.GameView

import scalafx.application.Platform
import scalafx.scene.input.{KeyCode, KeyEvent}


class GameController(val model : GameModel, val view : GameView  ) {

  def onKeyPressed(e :KeyEvent){
    if (model.selectedSquare != -1) {
      model.sudokuPuzzle.presented(model.selectedSquare) = e.code match {
        case KeyCode.DIGIT1 => 1
        case KeyCode.DIGIT2 => 2
        case KeyCode.DIGIT3 => 3
        case KeyCode.DIGIT4 => 4
        case KeyCode.DIGIT5 => 5
        case KeyCode.DIGIT6 => 6
        case KeyCode.DIGIT7 => 7
        case KeyCode.DIGIT8 => 8
        case KeyCode.DIGIT9 => 9
        case a => 0

      }
      view.sudokuView.refreshSquares()
    }

  }
  def onMouseClick(x: Int, y: Int): Unit ={

    if(model.sudokuPuzzle.modifable(SudokuPuzzle.getIndex(x, y))){
      model.selectedSquare = SudokuPuzzle.getIndex(x, y)
      view.sudokuView.refreshSquares()
    }
  }

  def onNewGameButtonClick(): Unit ={

    val difficulty : Difficulty = view.settingsBarView.difficultyChoiceBox.getValue match{
      case "Easy" => new Easy
      case "Medium" => new Medium
      case "Hard" => new Hard
    }
    model.newGame(difficulty)
    view.scoreBarView.changeToUnfinished()
    view.sudokuView.refreshSquares()


  }

  def onCloseButtonClick(): Unit ={
    Platform.exit();
    System.exit(0);
  }
  def onSolveButtonClick(): Unit ={
    model.sudokuPuzzle.solve(0,  model.sudokuPuzzle.possibleNumbers(0,model.sudokuPuzzle.presented.toArray),
      model.sudokuPuzzle.presented )
    view.sudokuView.refreshSquares()
  }
  def onCheckButtonClick(): Unit ={
    if(SudokuPuzzle.equals(model.sudokuPuzzle.puzzle, model.sudokuPuzzle.presented)){
      view.scoreBarView.changeToFinished()
    } else {
      view.scoreBarView.changeToIncorrect()
    }

  }

}
