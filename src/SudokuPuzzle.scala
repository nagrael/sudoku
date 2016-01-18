

// Sudoku Generator For Scala
// Pretty efficient, can create a puzzle in under a millisecond on my machine.

abstract class Difficulty { def revealCount: Int }
case class Easy() extends Difficulty { val revealCount = 48 }
case class Medium() extends Difficulty { val revealCount = 35 }
case class Hard() extends Difficulty { val revealCount = 31 }

class SudokuPuzzle(difficulty: Difficulty) {
  private val possible: Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)

  private val rand = new scala.util.Random(System.currentTimeMillis)

  val puzzle = new Array[Int](81)

  lazy val presented = new Array[Int](81)

  def roll(p: Array[Int]) {
    def randomSet(max: Int, count: Int, set: Set[Int]): Set[Int] = {
      set.size match {
        case `count` => set
        case _ => randomSet(max, count, set + rand.nextInt(max))
      }
    }
    randomSet(81, difficulty.revealCount, Set()).foreach(index => p(index) = puzzle(index))

  }
  private def equals(p: Array[Int],q: Array[Int]): Boolean ={
    for(i)
  }

  // What values have been used in the same row as the cell?
  private def rowValues(cell: Int,p: Array[Int]): Set[Int] = {
    val index: Int = cell / 9 * 9
    p.slice(index, index+9).toSet
  }

  // What values have been used in the same column as the cell?
  private def columnValues(cell: Int,p: Array[Int]): Set[Int] =
    ((cell % 9) to 80 by 9).map(index => p(index)).toSet

  // What values have been used in the same block as the cell?
  private def blockValues(cell: Int,p: Array[Int]): Set[Int] = {
    val startColumn = ((cell % 9) / 3) * 3
    val startRow = ((cell / 9) / 3) * 3
    val blockArray = (0 to 2) flatMap {
      (row: Int) =>
        val currentLocation = ((startRow + row) * 9) + startColumn
        p.slice(currentLocation, currentLocation + 3)
    }
    blockArray.toSet
  }

  // What are the possible numbers for this cell?
  def possibleNumbers(cell: Int,p: Array[Int]): List[Int] = {
    val items = if(cell!=81){
      if(p(cell)==0){
        possible -- (rowValues(cell, p: Array[Int]) ++
          columnValues(cell, p: Array[Int]) ++ blockValues(cell, p: Array[Int]))}

      else {
        possible -- (rowValues(cell, p: Array[Int]) ++
          columnValues(cell, p: Array[Int]) ++ blockValues(cell, p: Array[Int])) ++ Set(p(cell))
    }
    }
    else{Set()}
    scala.util.Random.shuffle(items.toList)
  }

  def puzzleToString(p: Array[Int]): String = {
    (0 to 8).map {
      row => (0 to 8).map(column => p(row * 9 + column).toString ).mkString(" ")
    }.mkString("\n")
  }

  override def toString = puzzleToString(puzzle)

  def generate(cell: Int, possibilities: List[Int], p: Array[Int]): Boolean = {
    (cell, possibilities) match {
      case (81, _) => true	// If we get to cell 81, we're done!
      case (_, Nil) => false	// If we have no possibilities, time to backtrack
      case _ => {
        // Try filling in the cell with each of the possibilities until either all
        // possibilities are exhausted or an acceptable possibility is found
        val x = possibilities dropWhile  {
          value =>
            val tmp = p(cell)
            p(cell) = value
            val nextCell = cell + 1
            if (generate(nextCell, possibleNumbers(nextCell,p: Array[Int]), p: Array[Int]) == false) {
              p(cell) = tmp
              true
            }
            else false
        }
        x != Nil
      }
    }
  }

  generate(0, possibleNumbers(0, puzzle),puzzle)
  roll(presented)

}