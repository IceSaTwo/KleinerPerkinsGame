
/**
 *@author sahoi
 *  abstract card class, stores shared methods between the different cards 
 */
abstract class Card(val num: Int, val color: String) {

  /**
   * returns the value of the color attribute within card
   */
  def getColor(): String = {
    this.color
  }
  /**
   * function that returns the value of the number attribute within the card class
   */
  def getNum(): Int = {
    this.num
  }


  /**
   * prints the value of the card and it's color to the console
   */
  def printCard() = {}
}