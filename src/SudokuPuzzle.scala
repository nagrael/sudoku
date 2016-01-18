

// Sudoku Generator For Scala
//

abstract class Difficulty { def revealCount: Int }
case class Easy() extends Difficulty { val revealCount = 40 }
case class Medium() extends Difficulty { val revealCount = 50 }
case class Hard() extends Difficulty { val revealCount = 65 }

class SudokuPuzzle(difficulty: Difficulty) {
  private val possible: Set[Int] = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)

  //Generated board
  val puzzle = new Array[Int](81)
  //Sudoku to complite
  lazy val presented = puzzle.clone()

  //Create sudoku from generated board. Max empty fields aare ar difficulty
  def roll(p: Array[Int],max:Int) {
    val list = scala.util.Random.shuffle((0 to 80).toList)
    def randomSet( previous:Int, count: Int, trying:Int, set: Set[Int]): Set[Int] = {
      (trying,count) match {
            case (`max`,_) => set
            case (_,81) => set
            case _ => {if(ispossible(set+list(count),p)) randomSet(list(count), count+1,trying+1, set + list(count))
                    else randomSet(previous, count+1,trying, set)}
      }
    }
    randomSet(-1, 0, 0, Set()).foreach(index => p(index) = 0)
  }
  //Check if two arrays are equals
  def equals(p: Array[Int],q: Array[Int]): Boolean ={
    var bool = true
    for(i <- 0 to 80){
      if(q(i)!=p(i))
        bool = false
    }
    bool
  }
  // Has our sudoku unique solution?
  private def ispossible(s: Set[Int],p: Array[Int] ): Boolean ={
    val tmp = p.clone()
    s.foreach(index => tmp(index) = 0)
    count(tmp)
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
      if(p(cell)==0)
          possible -- (rowValues(cell, p: Array[Int]) ++
          columnValues(cell, p) ++ blockValues(cell, p))

      else {
          possible -- (rowValues(cell, p: Array[Int]) ++
          columnValues(cell, p) ++ blockValues(cell, p)) ++ Set(p(cell))
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

  // Solve given sudoku if array is empty generate full random board
  def solve(cell: Int, possibilities: List[Int], p: Array[Int]): Boolean = {
    (cell, possibilities) match {
      case (81, _) => true	// If we get to cell 81, we're done!
      case (_, Nil) => false	// If we have no possibilities, time to backtrack
      case _ => {
        // Try filling in the cell with each of the possibilities until either all
        // possibilities are exhausted or an acceptable possibility is found
        if(p(cell)==0) { // We only need to check fields that are  0
          val x = possibilities dropWhile {
            value =>
              p(cell) = value
              val nextCell = cell + 1
              if (solve(nextCell, possibleNumbers(nextCell, p), p) == false) {
                p(cell) = 0
                true
              }
              else false
          }
          x != Nil
        }
        else { // If field is not 0 skip it
          val nextCell = cell + 1
          solve(nextCell, possibleNumbers(nextCell, p), p)
        }
      }
    }
  }

  // How many solutions our board has?
  def count(p: Array[Int]): Boolean = {
    var c = 0
    def coutPossible(cell: Int, possibilities: List[Int]): Boolean = {
      (cell, possibilities) match {
        case (81, _) => {c+=1; if(c==1)false else true}
        case (_, Nil) => false
        case _ => {
          if (p(cell) == 0) {
            val x = possibilities dropWhile {
              value =>
                p(cell) = value
                val nextCell = cell + 1
                if (coutPossible(nextCell, possibleNumbers(nextCell, p)) == false) {
                  p(cell) = 0
                  true
                }
                else false
            }
            x != Nil
          }
          else {
            val nextCell = cell + 1
            coutPossible(nextCell, possibleNumbers(nextCell, p))
          }
        }
      }
    }
    coutPossible(0, possibleNumbers(0, p))
    c==1
  }
  solve(0, possibleNumbers(0, puzzle),puzzle)
  roll(presented,difficulty.revealCount)

}