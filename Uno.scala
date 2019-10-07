import java.io.IOException
import scala.io.StdIn.readLine
import java.util.ArrayList
import scala.collection.mutable.Stack
import scala.util.Random.shuffle

/**
 * @author sahoi
 */
class Uno() {

  var myDeck = new Stack[Card]() //the initial deck - this is randomized
  var myDiscard = new Stack[Card]() //the discard pile
  fillDeck() //populates the game deck
  var player1 = new Player(1, buildHand)
  var player2 = new Player(2, buildHand)
  /**
   * function to fill the deck up with all the types of cards
   * as the game is initialized
   */
  def fillDeck() = {
    var red = "red"
    var green = "green"
    var yellow = "yellow"
    var blue = "blue"
    for (i <- 0 to 9) { //uno cards have twice the number for each color
      myDeck.push(new NumberCard(i, red))
      myDeck.push(new NumberCard(i, green))
      myDeck.push(new NumberCard(i, yellow))
      myDeck.push(new NumberCard(i, blue))
    }
    shuffleDeck() //randomizes the elements in the stack
  }

  /**
   * function to shuffle the deck
   * called when game is initialized
   * called when deck is empty and discard pile becomes the deck
   */
  def shuffleDeck() = {
    myDeck = scala.util.Random.shuffle(myDeck)
  }

  /**
   * function that repopulates the stack when it is near empty,
   * and re-shuffles it
   */
  def refillDeck() = {
    println("Refilling Deck")
    val top = myDiscard.pop
    myDeck = myDeck.pushAll(myDiscard) //using stack library for traversals
    shuffleDeck()
    myDiscard.push(top)
  }

  /**
   * function to build up the players hand by taking cards from the game deck
   * @return the arraylist of cards that make up the players hand
   */
  def buildHand(): ArrayList[Card] = {
    val hand: ArrayList[Card] = new ArrayList()
    for (i <- 1 to 7) {
      hand.add(myDeck.pop()) //creating a hand
    }
    hand
  }
  /**
   * helper function to error check integers
   * @return the integer after it has been converted to an int
   */
  def errInt(input: String, player: Player): Int = {
    try {
      input.trim.toInt
    } catch { //if the input is not a number
      case e: NumberFormatException =>
        println(input + " is not a valid option")
        displayMenu(player)
    }
    input.trim.toInt
  }
  /**
   * draw a card from the card deck
   * @return the top most card from the deck
   */
  def drawCard(): Card = {
    myDeck.pop
  }
  /**
   * display the card that is at the top of the discard pile
   * on to the console
   */
  def displayDiscard() = {
    val top = myDiscard.pop()
    println("Here is the top of your discard pile: ")
    println("-------")
    top.printCard()
    println("-------")
    myDiscard.push(top)
  }

  /**
   * function to check if the
   */
  def matchCard(cardNum: Int, cardColor: String): Boolean = {
    val temp = myDiscard.pop()
    if (cardNum.equals(temp.getNum()) || cardColor.equals(temp.getColor())) {
      myDiscard.push(temp)
      return true
    } else {
      myDiscard.push(temp)
      return false
    }
  }

  /**
   * function to parse the options the user inputs
   * @return the output of the interaction between the players on to the
   * console
   */
  def displayMenu(player: Player): Unit = {
    if (myDeck.size < 3) {
      refillDeck()
    }
    println("*******************************")
    var lastCard = false
    if (player.hand.size == 1) {
      println("Player " + player.playNum + "  - Last Card!")
      lastCard = true
    }
    println("It is Player" + player.playNum + "'s turn!")
    println("Player " + player.playNum + " please pick a menu option: ")
    println("[1] View Hand [2] Play Card [3] Display Discard Top [4] Draw A Card")

    val option = errInt(readLine(), player)

    option match {
      case 1 => //to view hand, redisplay menu afterwards
        player.viewHand
        displayMenu(player)
      case 2 =>
        println("What card would you like to place on the discard pile? " +
          "\n" + "Enter in this format: 1, green ")

        val inPair = readLine().split(',')

        //enter a specific number to view hand again
        val cardNum = errInt(inPair(0).trim(), player)
        val cardColor = inPair(1).trim()

        if (matchCard(cardNum, cardColor)) { //check if player can play the card
          //card is taken from hand and placed on discard pile only if it matches
          val toDis = player.removeCard(cardNum, cardColor)
          if (toDis == null) { //card to place on discard is not in hand
            println("That card does not exist in your hand, pick another")
            displayMenu(player)
          } else { //place card on discard and look at it
            if (lastCard) {
              println("CONGRATULATIONS PLAYER " + player.playNum + " HAS WON!!!")
            } else {
              myDiscard.push(toDis)
              displayDiscard
              if (player.playNum == 1) {
                displayMenu(player2)
              } else {
                displayMenu(player1)
              }
            }

          }
        } else { //chosen card does not match the card on the top of discard pile
          println("Your chosen card does not match the card " +
            "at the top of the discard pile, please draw a card")
          displayMenu(player) //play again
        }
      case 3 => //this is to see the card that is on top of the discard pile
        displayDiscard
        displayMenu(player)
      case 4 => //no card in the player's hand matches the discard, so they must draw
        player.addCard(drawCard)
        println("CHANGE IN TURN")
        if (player.playNum == 1) {
          displayMenu(player2)
        } else {
          displayMenu(player1)
        }
      case _ =>
        println("Not a valid option")
        displayMenu(player)
    }

  }

  /**
   * function to explain the rules of the game to the user
   */
  def about(): Unit = {

    println("The goal of the game is to match the top card in the discard pile "
      + "with the cards that you, as a player, have in your hand."  + "\n" + "Once you have "
      + "chosen a game option, then you are given a menu of options to pick from."
      + "\n" 
      + "type in 1 to: view the set of cards in your hand " + "\n"
      + "type in 2 to: place a card on top of the discard pile " + "\n"
      + "(place the card by typing the number of the card and color, for example: 5, red) " + "\n"
      + "type in 3 to: view the top of the discard pile " + "\n"
      + "type in 4 to: draw a card from the main deck " + "\n"
      + "\n"
      + "As you match the cards in your with the card on the discard pile, the card on "
      + "the discard pile becomes the card on top of the deck." + "\n" + "This matching game "
      + "continues until a player does not have any cards in their hand. " + "\n" + "The player "
      + "to first empty their hand wins." + "\n" )

    play()
  }

  /**
   * main play function, distinguishes the type of game to play
   * calls the displayMenu method for the user to interact with
   */
  def play(): Unit = {

    println("Welcome to Uno, please choose a game option" + "\n" +
      "[1] Player 1 vs Player 2" + "\n" + "[3] About")
    val input = readLine.trim
    try {
      input.toInt
    } catch {
      case e: NumberFormatException =>
        println("You did not enter a valid option, please enter a number")
        play()
    }
    input.toInt match {
      case 1 => //game is now player1 vs player 2
        myDiscard.push(myDeck.pop) //take a card from the to deck to begin game
        displayDiscard
        displayMenu(player1)
      case 3 =>
        about()
      case _ =>
        println("You entered a number that is not an option, try again")
        play()

    }
  }
}

/**
 * object to uno class, main argument runs here
 */
object Uno {
  var isPlaying = true
  def main(args: Array[String]) = {

    if (args.length != 0) {
      println("Sorry! The Uno Game does not take in any arguments")
    } else {
      try {
        val uno = new Uno()
        while (isPlaying) {
          uno.play()
          isPlaying = false
        }
      } catch {
        case e: IOException =>
          println("Encountered an error: " + e.getMessage())
      }
    }
  }
}