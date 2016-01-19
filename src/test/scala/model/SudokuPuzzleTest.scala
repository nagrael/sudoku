
import model.{Hard, Medium, Easy, SudokuPuzzle}
import org.scalatest._

class SudokuPuzzleTest extends FlatSpec with Matchers{

  "equals" should "return true for two same tables" in {
    var puzzle = new SudokuPuzzle(new Hard())
    SudokuPuzzle.equals(puzzle.puzzle, puzzle.puzzle) should be (true)
    SudokuPuzzle.equals(puzzle.presented, puzzle.presented) should be (true)
  }
  "SudokuPuzzle solve" should "always return same score" in {
    var puzzle = new SudokuPuzzle(new Hard())
    puzzle.solve(0, List.empty[Int] ++ puzzle.possible, puzzle.presented )
    SudokuPuzzle.equals(puzzle.puzzle, puzzle.presented) should be (true)
    puzzle = new SudokuPuzzle(new Medium())
    puzzle.solve(0, List.empty[Int] ++ puzzle.possible, puzzle.presented )
    SudokuPuzzle.equals(puzzle.puzzle, puzzle.presented) should be (true)
    puzzle = new SudokuPuzzle(new Easy())
    puzzle.solve(0, List.empty[Int] ++ puzzle.possible, puzzle.presented )
    SudokuPuzzle.equals(puzzle.puzzle, puzzle.presented) should be (true)

  }

}
