package view

import model.GameModel

import scalafx.Includes._
import scalafx.geometry.Insets
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, Text}


class SudokuView(val model : GameModel) extends VBox{

  var squareTexts = new Array[Text](81)
  var squareField = new Array[Rectangle](81)
  val squares = new GridPane {
    margin = Insets(20)
    gridLinesVisible = false
    for(i <- 0 until 3) {
      for (j <- 0 until 3) {
        val subGrid  = new GridPane {
          margin = Insets(5)
          gridLinesVisible = true
          for(ii <- 0 until 3){
            for(jj <- 0 until 3){
              val child = new StackPane(){
                val x= i * 3 + ii
                val y = j * 3 + jj
                onMouseClicked = (event: MouseEvent) => app.App.controller.onMouseClick(x,y) // controller
                var textValue = ""
                var field = new Rectangle{
                  id = "rectangle"
                  width = 60
                  height = 60
                  fill = Color.rgb(231,245,250)
                  stroke = Color.Black
                }
                var text = new Text{
                  id = "text"
                  text = ((i * 3 + ii)*9 +j * 3 + jj).toString
                  font = new Font(40)

                }
                squareTexts(((i * 3 + ii)*9 +j * 3 + jj)) = text
                squareField(((i * 3 + ii)*9 +j * 3 + jj)) = field
                children = Seq(field,text)
              }
              add(rowIndex = ii, columnIndex = jj,child = child)

            }
          }

        }
        add(rowIndex = i, columnIndex = j,child = subGrid)

      }
    }
  }
  children = Seq(squares)

  def refreshSquares(): Unit ={
    for (x <- squareTexts){
      x.text = model.sudokuPuzzle.presented(squareTexts.indexOf(x)).toString match {
        case "0" => ""
        case foo => foo
      }
    }

    for (x <- squareField){
      val i = squareField.indexOf(x)
      if( i == model.selectedSquare) {
        x.fill =Color.rgb(255,196,123)
      } else if(i%9 == model.selectedSquare%9 || i/9 == model.selectedSquare/9 ){
        x.fill =Color.rgb(255,246,193)

      } else {
        x.fill = model.sudokuPuzzle.modifable(i) match {
          case true => Color.rgb(231, 245, 250)
          case false => Color.rgb(201, 205, 205)
        }
      }
    }



  }



}

