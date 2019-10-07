
/**
 * @author sahoi
 * class that initializes a specific card type, a card with only numbers
 *  and colors (no actions)
 */
class NumberCard(num: Int, color: String) extends Card(num, color){
  /**
   * funtion to override the printCard funtion in the abstract class card
   */
  override def printCard() = {
    println("{ " + num + " | " + color + " }")
  }
}