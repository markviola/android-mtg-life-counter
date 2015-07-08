#MTG Life Counter

The MTG Life Counter is an Android app that allows people to easily keep track of life totals when playing "Magic the Gathering". Functionality includes support for up to 4 players, regular life total & poison counter tracker, end screen when a player loses, and user customized color schemes.

###General Screenshots

Starting screen, player tracker screen, and settings screen (respectively):

<p align="center">
  <img src="https://github.com/markviola/android-mtg-life-counter/blob/master/images/all_types_screen.jpg?raw=true" alt="Player Screens"/>
</p>

###Use:

To use the app, from the main activity, the user will need to select the number of players that he wants to keep track of; from two to four players. This will take the user to the life totals activity for the corresponding number of players. By swiping left or right the activity will change to the poison counter activity for the corresponding number of players. 

Pressing the Reset button will change all life totals back to "20" and all poison counters to "0". By pressing the Settings button the user will be taken to the Settings activity where they can change the names of the various players.

###Inverted Second Player

Below are images for when the "Invert second player's screen" option is selected:

<!-- <p align="center">
  <img src="https://github.com/markviola/android-mtg-life-counter/blob/master/images/both_inverted_screens.jpg?raw=true" alt="Player Screens" height="328px" width="370px"/>
</p> -->

The inverted screen is used only for two player (the option disappears when two players is not selected). This option makes it easier for the user's opponent to keep track of their own life total and poison counters since their player screen is orient in their direction. 


###To-do List:
* Add activity where the user can see a record of life total changes (Use services to check length between inputs?)
* Add feature for when a player gets eliminated, it takes the user to a defeat screen then goes to a new activity with [player # - 1] people
* Add text-to-speech for defeat screen
* Add option for Two-Headed Giant (40 life start) for four players, and Commander (30 life start)
* Fix inverted second player option to cater to new layout