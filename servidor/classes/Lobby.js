'use strict';

//Requires
var Player = require('./Player');

//Constants
const maxPlayers = 2;

module.exports = class Lobby {
    constructor(io, id) {
        this._id = id;
        this._io = io;
        this._players = [];
        this._maxPlayers = maxPlayers;
        this._gameStarted = false;
    }

    //Called to update the location of player, used to synchronize it in all other players at this lobby
    onPlayerMove(player, x, y) {
        var lobby = this;
        var io = this._io;
        var position = x + "," + y;

        for (var i = 0; i < lobby._players.length; i++) {
            var otherPlayer = lobby._players[i];

            if (player.getSocketId() != otherPlayer.getSocketId()) {
                io.to(otherPlayer.getSocketId()).emit("enemy_move", position);
            }
        }
    }

    //Called when a player fires a shot, used to synchronize it in all other players at this lobby
    onPlayerFired(player, x, y) {
        var lobby = this;
        var io = this._io;
        var position = x + "," + y;

        for (var i = 0; i < lobby._players.length; i++) {
            var otherPlayer = lobby._players[i];

            if (player.getSocketId() != otherPlayer.getSocketId()) {
                io.to(otherPlayer.getSocketId()).emit("enemy_fire", position);
            }
        }
    }

    //Called when a player uses a special skill, used to synchronize it in all other players at this lobby
    onPlayerSpecial(player, x, y) {
        var lobby = this;
        var io = this._io;
        var position = x + "," + y;

        for (var i = 0; i < lobby._players.length; i++) {
            var otherPlayer = lobby._players[i];

            if (player.getSocketId() != otherPlayer.getSocketId()) {
                io.to(otherPlayer.getSocketId()).emit("enemy_special", position);
            }
        }
    }

    //Called when a player get hit by a shot, used to synchronize it in all other players at this lobby
    onPlayerGetShot(player) {
        var lobby = this;
        var io = this._io;

        io.to(player.getSocketId()).emit("player_getShot", "");

        for (var i = 0; i < lobby._players.length; i++) {
            var otherPlayer = lobby._players[i];

            if (player.getSocketId() != otherPlayer.getSocketId()) {
                io.to(otherPlayer.getSocketId()).emit("enemy_getShot", "");
            }
        }
    }

    //Called when a player join the lobby
    onPlayerJoin(player) {
        var lobby = this;
        var io = this._io;

//        io.to(player.getSocketId()).emit("player_join", "");

        for (var i = 0; i < lobby._players.length; i++) {
            var otherPlayer = lobby._players[i];

            if (player.getSocketId() != otherPlayer.getSocketId()) {
                io.to(otherPlayer.getSocketId()).emit("enemy_join", player.getShipType());
                io.to(player.getSocketId()).emit("enemy_join", otherPlayer.getShipType());
            }
        }
    }

    //When a player is disconnected (after X seconds of non-reconnection)
    onPlayerDisconnect(player) {
        this.removePlayer(player);
    }

    //Returns the number of players in this lobby
    getPlayerCount() {
        return this._players.length;
    }

    //Return the lobby ID
    getId() {
        return this._id;
    }

    //Returns the number of how many players can play in this lobby
    getMaxPlayerCount() {
        return this._maxPlayers;
    }

    //Returns if the game is running or not (boolean)
    isGameStarted() {
        return this._gameStarted;
    }

    //Returns if a player can join the game or not (boolean)
    canPlayerJoin() {
        if ((this.getPlayerCount() < this.getMaxPlayerCount()) && !this.isGameStarted()) {
            return true;
        } else {
            return false;
        }
    }

    //Add a player to the lobby
    addPlayer(player) {
        if ((this.getPlayerCount() + 1) > this.getMaxPlayerCount())
            throw new Error("Lobby full.");

        player.joinLobby(this);
        this._players.push(player);

        this.onPlayerJoin(player);

        //If the lobby is full, starts the game
        if (this.getPlayerCount() == this.getMaxPlayerCount()) {
            this._gameStarted = true;
            this.startGame();
        }
    }

    //Removes a player from the lobby
    removePlayer(player) {
        player.leaveLobby();
        this._players.splice(this._players.indexOf(player), 1);

        this.endGame();
    }

    //Called to start the game
    startGame() {
        if (this.getPlayerCount() != this.getMaxPlayerCount())
            throw new Error("Insufficient players.");

        var lobby = this;
        var io = this._io;

        for (var i = 0; i < lobby._players.length; i++) {
            var player = lobby._players[i];
            io.to(player.getSocketId()).emit("startGame", "");
        }
    }

    //Called when the game is over
    endGame(){
        for (var i = 0; i < lobby._players.length; i++) {
            io.to(otherPlayer.getSocketId()).emit("endGame", position);
        }
    }

    //Force all players to leave lobby if the lobby is destroyed
    destroy() {
        for (var i = 0; i < this._players.length; i++) {
            this._players[i].leaveLobby();
        }
    }

    getPlayers(){
        return this._players;
    }

}
