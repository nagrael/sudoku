package app

/**
 * Created by Krystian on 2016-01-17.
 */

import controller.GameController
import model.GameModel
import view.GameView

import scalafx.application.JFXApp


object App extends JFXApp {
  val model = new GameModel();
  val view = new GameView(model);
  stage = view
  val controller = new GameController(model, view)
}