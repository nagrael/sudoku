package view


import model.GameModel

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout._

class GameView(val model : GameModel) extends PrimaryStage {
  title.value = "Sudoku"
  val sudokuView = new SudokuView(model)
  scene = new Scene {
    resizable = false
    content = new BorderPane {
      center = sudokuView
    }
  }

}
