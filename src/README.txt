COSC1295 : Assignment 2 Readme

CONTENTS OF THIS FILE
---------------------
   
 * Introduction
 * Installation Instructions
 * Operating Instructions
 * File Manifest
 * Credits and Acknowledgements


INTRODUCTION
------------

Ozlympic games is an interactive sport simulation application. 
The Ozlympic menu provides the user with a variety of options to run the games.


INSTALLATION INSTRUCTIONS
-------------------------

1. Unzip file and Run the Assignment02.Jar file.
2. Unzip file and run from command line using: java -jar Assignment02.jar
   OzlympicGameView has the main(String[] args) method

OPERATING INSTRUCTIONS
----------------------

Run OzlympicGameView application. Follow the instructions provided by the user interface to run the games.

NAVIGATION MENU:
----------------

1. GAME TYPE:  
   ----------	
   Click on the game you wish to run (Swimming, Cycling, Running): 
   	-Brings up list of referees and athletes to choose from for the game
	-Use the colour coded legend in the navigation menu to choose appropriate participants for the game
	-Drag and drop one referee for each game to the Official Selected box and from 5 to 8 athletes in the Athletes Selected box
	-When you are finished with your selections, click on the Confirm button on the bottom of the screen to start the game

2. PARTICIPANT INFO:
   -----------------
   Outline of the game participant rules

3. DISPLAY RESULTS:
   ---------------- 
   Click on the Athlete Points button to display a table containing all athletes and accumulated points from all the games executed so far.
	-First place  = 5 points
	-Second place = 3 points
	-Third place  = 1 point

   Click on the Game Result History button in the navigation menu to view a table containing all executed games so far containing:
	-Game type and ID
	-Date and time of game execution
	-Top 3 athletes with their corresponding times 
	-Acting referee for the game

4. PARTICIPANT LEGEND:
   -------------------
   Depicts colour corresponding to each participant type (swimmers, cyclist, sprinters, superathletes, referees

TOOLBAR:
--------

1. HELP:
   -----
   Click on the help button to view the application instructions

EXTERNAL DATA STORE:
--------------------

1. DATABASE - GAMERESULT:
   ---------------------
   Insert each record while the game is excuted.
   
2. FILE - GAMERESULT:
   ------------------
   Write all the game histroy when the application is closed.

FILE MANIFEST
-------------

GameFullException.java
GameUnexecutedException.java
NoRefereeException.java
TooFewAthleteException.java
TooManyRefereeException.java
WrongTypeException.java
Data.java
Driver.java
Athlete.java
Competable.java
Game.java
Official.java
Participant.java
SuperAthlete.java
AthletePointsTable.java
GameResultHistoryTable.java
InstractionsPane.java
NavigationMenu.java
OzlympicGameView.java
AthleteTest_compareTo.java
AthleteTest_compete.java
GameTest_ExecuteGame.java
GameTest_generateTime.java
OfficialTest.java
Image.png
Assignment 2 - Class Diagram.ucls
gameResults.txt
Participants.txt
MYDB.lck
MYDB.log
MYDB.properties
MYDB.script
OzlympicDB.lck
OzlympicDB.log
OzlympicDB.properties
OzlympicDB.script
Design.docx
LeanTesting - Bug Report.csv
README.txt


CREDITS AND ACKNOWLEDGEMENTS
----------------------------

Original work by Ya-Huai Lai (Loso s3579161) and Arion Barzoucas-Evans (s3650046).

