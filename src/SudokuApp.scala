/**
  * Created by Jan on 2016-01-17.
  */
object SudokuApp extends App {
  val puzzles = new SudokuPuzzle(new Hard)
  println(puzzles.puzzleToString(puzzles.puzzle))
  println("")
  println(puzzles.puzzleToString(puzzles.presented))
  println("")
  println(puzzles.generate(0, puzzles.possibleNumbers(0, puzzles.presented),puzzles.presented))
  println("")
  println(puzzles.puzzleToString(puzzles.presented))

}

