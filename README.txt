UNO README -isatou

RUNNING THE CODE:
(after you've downloaded the game from github)
***Run on terminal**
---cd into the Uno project folder
If you don't have the Scala repl installed, type into terminal:
$brew install scala
---Make sure scala/bin is in your path enviornment variable, type:
$echo 'export PATH=/Users/<your username>/scala/bin:$PATH' >> ~/.bash_profile

---If you have the scala repl installed, type:
$scalac -classpath lib/<scala-library.jar> -d classes " src/*
---then: $ scala -classpath classes:lib/<scala-library.jar> src.Uno

OR *****run in eclipse****
---open the project in eclipse
---press on green button with a white right directed arrow (the run button in eclipse)

and play the game!!! 

GAME INSTRUCTIONS:
To play the game run the code. Once you have run the code you will be prompted to 
choose the player 1 or player 2 option or choose about to get the instructions of the
game. You choose the instructions by typing in the number 1 or 2.

The goal of the game is to match the top card in the discard pile with the 
cards that you, as a player, have in your hand. 

Once you have chosen a game option, then you are given a menu of options to pick from.
type in 1 to: view the set of cards in your hand
type in 2 to: place a card on top of the discard pile
(place the card by typing the number of the card and color, for example: 5, red)
type in 3 to: view the top of the discard pile
type in 4 to: draw a card from the main deck

As you match the cards in your with the card on the discard pile, the card on 
the discard pile becomes the card on top of the deck. This matching game
continues until a player does not have any cards in their hand. The player
to first empty their hand wins.


DESIGN AND TOOL CHOICES:
--- choice of a stack as the deck and discard pile:
I chose a stack to represent my deck because when playing a real life card
game you only need the top card on the deck. I wanted a way to just pull (or pop)
the top card from the deck. The pop and push functions within the stack allow
one to do that pretty quickly. 

The thing that takes time is the shuffling of the card, which is an 
additional scala library I've used. It is contained within scala.util.Random. 
I needed to randomize the cards in my stack. 

---choice of the hand as ArrayList
I chose to represent the hand as an arraylist because I wanted a very quick way, 
again I am thinking about the time complexity, to access elements in my hand. 
ArrayList allow indexing. 

--- match statements for executing menu options
I used switch statements for executing my menu options for design purposes. 
Rather than using if/else statements, which can make the code look cluttered, I 
matched on the option numbers. 

I am also here thinking about the edge and error cases. Which I do my best to handle
with the proper instructions printed out to the console.

--- choice of representing card class as abstract class
I chose to represent my cards as an abstract class because I wanted to 
be able to represent different types of cards while making sure to not 
repeat the code that each different card type might share. A regular game of
Uno usually has wild cards, reverse cards, and skip cards, in addition to 
regular numbered cards. I have only implemented matching on the number cards.
I included this functionality so that I can easily add different types of
 uno cards.
