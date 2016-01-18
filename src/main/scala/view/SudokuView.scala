package view

import model.GameModel


import scalafx.Includes._
import scalafx.beans.property.StringProperty
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.{ImageView, Image}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class SudokuView(val model : GameModel) extends VBox{
  val squares = new GridPane {
    margin = Insets(20)
    gridLinesVisible = true
    for(i <- 0 until 9){
      for(j <- 0 until 9){
        if(i % 3 == 0){

        }
        val child = new StackPane(){
          var x = i
          var y = j
          children = List(new Rectangle{

            width = 70
            height = 70
            if((i + j)%2 == 0)
              fill = Color.SandyBrown
            else
              fill = Color.SaddleBrown

          })
        }
        add(rowIndex = i, columnIndex = j,child = child)


      }
    }
  }
  children = List(squares)
}
