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
  val squares = new GridPane {
    margin = Insets(20)
    gridLinesVisible = false
    for(i <- 0 until 3) {
      for (j <- 0 until 3) {
        val subGrid  = new GridPane {
          margin = Insets(3)
          gridLinesVisible = true
          for(ii <- 0 until 3){
            for(jj <- 0 until 3){
              val child = new StackPane(){

                onMouseClicked = (event: MouseEvent) => app.App.controller.onMouseClick(i*3+ii,j*3+jj)
                children = List(new Rectangle{
                  id = "square"
                  width = 60
                  height = 60
                  fill = Color.rgb(231,245,250)
                  smooth = true
                  stroke = Color.Black
                },new Text{
                  id = "text"
                  text = (3*ii+jj+1).toString
                  font = new Font(30)

                })
              }
              add(rowIndex = ii, columnIndex = jj,child = child)


            }
          }

        }
        add(rowIndex = i, columnIndex = j,child = subGrid)

      }
    }
  }

  children = List(squares)

  def getSquare(val x: Int, val y: Int) : StackPane {

  }
}


/*
val squares = new GridPane {
    margin = Insets(20)
    gridLinesVisible = true

    for(i <- 0 until 9){
      for(j <- 0 until 9){
        val child = new StackPane(){

          children = List(new Rectangle{
            width = 60
            height = 60
            fill = Color.rgb(231,245,250)
            smooth = true
            stroke = Color.Black

          })
        }
        add(rowIndex = i, columnIndex = j,child = child)


      }
    }
  }
 */