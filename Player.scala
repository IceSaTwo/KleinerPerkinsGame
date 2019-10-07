import java.util.ArrayList
/**
 * 
 * @author sahoi
 * player class:
 * player is given the attribute of a hand (initialized with 7 cards) each 
 * player is able to pick from the deck, add to their hand, remove from their 
 * hand & place card on discard pile
 * @param
 */
class Player(val playNum: Int, val hand: ArrayList[Card]) {
  var size = this.hand.size
  def getSize(): Int = {
    size
  }

  /**
   * add a card to player's hand
   */
  def addCard(card: Card) = {
    this.hand.add(card)
    size += 1
  }
  /**
   * remove card from player's hand
   */
  def removeCard(num: Int, color: String): Card = {

    var rem: Card = null
    for (i <- 0 to hand.size - 1) {
      if (hand.get(i).getNum.equals(num) && hand.get(i).getColor.equals(color)) {
        rem = hand.remove(i)
        size -= 1
        return rem
      }
    }
    rem
  }

  /**
   * view the cards in the player's hand
   */
  def viewHand = {
    println("--------")
    hand.forEach(_.printCard())
    println("hand size: " + size)
    println("--------")
  }
}
