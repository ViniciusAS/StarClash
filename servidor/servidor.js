'use strict';

//Classes
var Game = require('./classes/Game');

//Start socket listener
var io = require('socket.io')(90);

console.log("Node started.");

//Starts the game
new Game(io);