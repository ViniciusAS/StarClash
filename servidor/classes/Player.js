'use strict';

var Lobby = require('./Lobby');

module.exports = class Player {
    constructor(name, socket) {
        this._name = name;
        this._socket = socket;
        this._socketId = socket.id;
        this._lobby = null;
        this._shipType = null;
    }

    //Returns the player name
    getName() {
        return this._name;
    }

    //Returns the player socket to be able to send commands to him
    getSocket() {
        return this._socket;
    }

    //Returns the player socket id to be able to send commands to him
    getSocketId() {
        //return this._socket.id;
        return this._socketId;
    }

    //Returns the player selected ship type
    getShipType() {
        return this._shipType;
    }

    //Defines the player selected ship type
    setShipType(shipType) {
        this._shipType = shipType;
    }

    //Returns true if the player is in a lobby, else, return false
    isInLobby() {
        return this._lobby != null;
    }

    //Defines the player lobby
    joinLobby(lobby) {
        if (this.isInLobby())
            throw new Error("Player is already in a lobby.");

        this._lobby = lobby;
    }

    //Remove the lobby reference from player
    leaveLobby() {
        if (this._lobby != null) {
            this._lobby = null;
        }
    }

    //Returns the instance of the lobby of this player
    getLobby() {
        return this._lobby;
    }

}
