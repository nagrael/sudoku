package view

import javafx.collections.FXCollections

import app.App

import scalafx.Includes._
import scalafx.geometry.Pos
import scalafx.scene.control.{Button, ChoiceBox}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.HBox


class SettingsBarView() extends HBox {
  alignment = Pos.Center
  spacing = 10


  var buttonNewGame = new Button("New Game"){
    onMouseClicked = (event: MouseEvent) => App.controller.onNewGameButtonClick()
  }
  var buttonCheck = new Button("Check"){
    onMouseClicked = (event: MouseEvent) => App.controller.onCheckButtonClick()
  }
  var buttonSolve = new Button("Solve"){
    onMouseClicked = (event: MouseEvent) => App.controller.onSolveButtonClick()
  }
  var buttonExit = new Button("Exit"){
    onMouseClicked = (event: MouseEvent) => App.controller.onCloseButtonClick()
  }

  var difficultyChoiceBox = new ChoiceBox(FXCollections.observableArrayList("Easy", "Medium", "Hard"))
  difficultyChoiceBox.value = "Easy"




  children = Seq(buttonNewGame,difficultyChoiceBox,buttonSolve ,buttonCheck)
}
