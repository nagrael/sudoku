package controller


import model.GameModel
import view.GameView
/**
 * Created by Krystian on 2016-01-18.
 */
class GameController(val model : GameModel, val view : GameView  ) {
  def onMouseClick(x: Int, y: Int): Unit = {
    println(x,y)
  }

}
