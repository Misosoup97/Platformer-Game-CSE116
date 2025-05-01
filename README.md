<div align="center">
  <h1>116 Game engine</h1>
</div>

For each task, all the information you need is on [cse116.com](https://cse116.com/). Scroll through the course 
schedule to find links to the requirements for each task. Links will go live as they are released. 

If you ever have any questions, visit [piazza](https://piazza.com/class/m5loxaebv7zfm/) to ask questions and 
find the office hours schedule.

You are free (and encouraged!) to explore the repository and make changes to the
project outside of the homework we assign. Being able to read, understand, and
edit code that is not all your own is a vital skill in your software development
journey.

> [!warning]
> While you are encouraged to make changes and have fun with the project, you
> should be careful with the changes you make. 
> 
> A good rule of thumb is to 
> - Never move files/folders around in the filetree.
> - Never change method/class names or access modifiers.
> 
> Making any of these changes gives a very high chance that you will fail on
> autolab since the grader is expecting a specific structure.
> 
> If you *really* want to, create a new branch on git, or just copy + paste
> the files into a separate directory and make your changes there.

With that said, here is some information to give you a better understanding of
the repository, whether you're playing around for fun, or working on your
coding tasks.

---

# Repository layout

## `src/main/java`

This is the source root for the project. All package names are relative to this directory

## `src/main/java/app`

This folder (also called a package) contains all the code for the project. It contains two important files.

**StartGame**: Run this file to run the game. You should run this right after cloning to ensure that everything is 
set up correctly with your IntelliJ install.

**Configuration**: This file contains several settings for the run of your games. The String "game" selects which game 
will run when you run StartGame. The project is set up to be able to create multiple games that use the same game 
engine. 

## `src/main/java/app/tests` 

This package is where you will be writing all of your
tests for each task. Unlike in task 0, you will be writing your own tests. 

For Task 1, we have given you some wall collisions tests. Wall collisions can be
finnicky to test, so you should not touch them. However, it is encouraged to
read through them to understand how tests are written.

## `src/main/java/app/games/`

Inside this package, you will find all the code for handling the game state. If
you want to change a level, change a sprite for an object, or change the entire
game itself, this is where you would do so.



## `src/main/java/app/gameengine/`


This package has everything that the games need in order to run. Each sub-package is
listed in detail below.


### `src/main/java/app/gameengine/model`


This package contains all the meta stuff of the actual game: Game objects, physics,
AI (this will be implemented in a later task), data structures, etc.

This is where you will write most of your code throughout the coding tasks.


###  `src/main/java/app/gameengine/controller`

This is where the controls for the game are. If you ever want to add a new
buttons or remap your controls, this is where you should do it.

This package will not be tested in Autolab so you have the freedom to change your buttons 
without affecting grading. Some tasks may ask you to make changes in this package so you can add your 
work to the game, though this isn't strictly required.


### `src/main/java/app/gameengine/graphics` 

This controls all the graphics rendered on screen: stuff like managing the
sprites, loading files, etc.

This package will not be tested in Autolab and you should never need to touch anything in this package 
when adding to your game, unless you want to.


## `data/MiniWorldSprites`

This directory contains a subset of the Mini World Sprites by itch.io user Shade.
More information, and all sprite sheets, can be found at https://merchant-shade.itch.io/16x16-mini-world-sprites

If you want to use more/different sprites in your game, download more sprite sheets and add them to this directory.