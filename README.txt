To run: 
-unzip folder
-from command line:
	enter:
		cd "." (where . represents the path to the folder)
		SpiderSolitaire

This is a version of Spider Solitaire, a variant of Solitaire made popular by its inclusion in some versions of Windows.

The rules are as follows:

Cards are laid out in 10 columns. A face up card is represented by its suit, followed by its value in parentheses.

S(A) = Ace of Spades
H(0) = 10 of Hearts (10 is represented by 0 in this version)

A face down card has hash symbols obscuring these values.

#(#)

The goal of this game is to remove all cards from the game. Players do this by creating top-down sequences or "runs", starting from King at the top and ending at Ace at the bottom. These runs should be all one suit and uninterrupted by other cards. When such a run is complete, all of its cards are removed from the game.

Players make moves in the game by moving a card or multiple between these columns. A card can only be moved if there are no cards below it. Multiple cards can only be moved if they are a subsequence of a run - that is, they are all of the same suit and in order. 

A single card, or a set of multiple cards (represented by its top card) may be placed onto a value immediately preceding it. For example, S(9) may be placed on S(0) (and also H(0), D(0), or C(0)).

If a player runs out of moves, they may choose to "deal" new cards into the game, from the reserved cards in the deck. This adds a random card from the deck onto the end of each column. All these cards must be dealt out before the game is over.

Commands used:

(move): this puts you into "move mode," where you will be prompted to enter row and column numbers to move cards around.

	(number): type a number when prompted to select a row or a 	column. Typing -1 at any time will take you back to the 	option selection screen.


(deal): this deals out a card to every column. This may be done only 5 times, and the game will inform you how many shuffles remain. 

(undo): undoes the last move (stores 20 previous moves, resets upon deal-ing).

(restart): starts a new game.

(quit): exits the program.




For my implementation of this game, I chose Java for its ease of manipulating objects (cards) and their properties (suit, value). The objects I implemented were Cards, which each have a suit and value, and may be face down or face up, and Decks, which are made up of cards of one, two, or four suits.

My design philosophy is to minimize dependencies and maximize ease of use. As such, no external libraries have been used for this project: only standard Java libraries. It is lightweight and I have accounted for as many edge cases as possible. I hope you enjoy playing it as much as I enjoyed making it!