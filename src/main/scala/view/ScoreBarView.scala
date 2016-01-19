package view

import scalafx.geometry.Pos
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}


class ScoreBarView extends HBox {

  def changeResultText(s: String): Unit ={
    scoreResultText.text = s;
  }
  def changeToUnfinished(): Unit ={
    scoreResultText.text = "unfinished"
    scoreResultText.fill = Color.Black
  }
  def changeToFinished(): Unit ={
    scoreResultText.text = "finished"
    scoreResultText.fill = Color.Green
  }
  def changeToIncorrect(): Unit ={
    scoreResultText.text = "incorrect"
    scoreResultText.fill = Color.Red
  }

  alignment = Pos.Center
  spacing = 10
  var scoreText = new Text {
    text = "Result: "
    font = new Font(60)
  }

  var scoreResultText = new Text {
    text = "unfinished"
    font = new Font(60)
    fill = Color.Black
  }
  children = Seq(scoreText,scoreResultText )
}
