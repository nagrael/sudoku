package view

import model.GameModel

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.KeyEvent
import scalafx.scene.layout._

class GameView(val model : GameModel) extends PrimaryStage {
  title.value = "Sudoku"
  val sudokuView = new SudokuView(model)
  val settingsBarView = new SettingsBarView()
  val scoreBarView = new ScoreBarView()
  scene = new Scene {
    onKeyPressed = (event: KeyEvent) => app.App.controller.onKeyPressed(event)
    resizable = false
    content = new BorderPane {
      center = sudokuView
      top = settingsBarView
      bottom = scoreBarView
    }
  }

}
