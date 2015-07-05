#MTG Life Counter

The MTG Life Counter is an Android app that allows people to easily keep track of life totals when playing "Magic the Gathering". Functionality includes support for up to 4 players, regular life total & poison counter tracker, end screen when a player loses, and user customized color schemes.

###Screenshots

Starting screen, settings screen, life totals screen, and poison counter screen (respectively):

<p align="center">
  <img src="https://github.com/markviola/android-mtg-life-counter/blob/master/images/all_types_screen.jpg?raw=true" alt="Player Screens"/>
</p>

###Use:

To use the app, from the main activity, the user will need to select the number of players that he wants to keep track of; from two to four players. This will take the user to the life totals activity for the corresponding number of players. By swiping left or right the activity will change to the poison counter activity for the corresponding number of players. 

Pressing the Reset button will change all life totals back to "20" and all poison counters to "0". By pressing the Settings button the user will be taken to the Settings activity where they can change the names of the various players.


###To-do List:
* Incorporate SQL to create a database for all player information
* Include option where the orientation of the player screen is rotated 180 degrees so that in two player mode the opponent can change the total themself
* Consolidate player life total and poison counter fragments so there is only one standard fragment that can be used for all numbers of players