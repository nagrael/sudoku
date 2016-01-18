/**
  * Created by Jan on 2016-01-17.
  */
object SudokuApp extends App {
  var b = true
  for(i<-0 to 100) {
    var puzzles = new SudokuPuzzle(new Medium)
    println("")
    println(puzzles.puzzleToString(puzzles.presented))

    (puzzles.solve(0, puzzles.possibleNumbers(0, puzzles.presented), puzzles.presented))

    (puzzles.puzzleToString(puzzles.presented))

    b = b && (puzzles.equals(puzzles.presented, puzzles.puzzle))
  }
  println("")
  println(b)
}

