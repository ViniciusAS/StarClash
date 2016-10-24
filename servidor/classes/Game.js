'use strict';

var Lobby = require('./Lobby');
var Player = require('./Player');

module.exports = class Game {
    constructor(io) {
        this._io = io;
        this._lobbies = [];
        this._players = [];
        this._playerIndex = 0;
        this._lobbyIndex = 0;

        var game = this;
        io.on('connection', function (socket) {
            game.onSocketConnect(socket);

            //////////// Game actions ////////////////

            socket.on('joinLobby', function (shipType) {
                var player = game.getPlayerFromSocket(socket);

                game.onPlayerSetShipType(player, shipType);

                //Start the lobby search to start the game
                game.setPlayerLobby(player);

                console.log(player.getName() + " joined lobby #" + player.getLobby().getId());
            });

            //////////// Player actions ////////////////

            socket.on('move', function (position) {
                var posArray = position.split(',');
                var posX = posArray[0], posY = posArray[1];

                var player = game.getPlayerFromSocket(socket);
                var lobby = player.getLobby();

                lobby.onPlayerMove(player, posX, posY);
            });

            socket.on('fire', function (position) {
                var posArray = position.split(',');
                var posX = posArray[0], posY = posArray[1];

                var player = game.getPlayerFromSocket(socket);
                var lobby = player.getLobby();

                lobby.onPlayerFired(player, posX, posY);
            });

            socket.on('special', function (position) {
                var posArray = position.split(',');
                var posX = posArray[0], posY = posArray[1];

                var player = game.getPlayerFromSocket(socket);
                var lobby = player.getLobby();

                lobby.onPlayerSpecial(player, posX, posY);

                console.log(player.getName() + " used special");
            });

            socket.on('getShot', function () {
                var player = game.getPlayerFromSocket(socket);
                var lobby = player.getLobby();

                lobby.onPlayerGetShot(player);

                console.log(player.getName() + " got shot");
            });

            //////////// Socket actions ////////////////

            socket.on('disconnect', function () {
                game.onSocketDisconnect(socket);
            });
        });

        console.log("Game server running!");
    }

    //Called when a socket connects
    onSocketConnect(socket) {
        this._playerIndex++;
        var player = new Player("Player #" + this._playerIndex, socket.id);

        this._players[socket.id] = player;

        this.onPlayerConnected(player);
    }

    //Called when socket disconnects
    onSocketDisconnect(socket) {
        var player = this._players[socket.id];

        this.onPlayerDisconnected(player);

        delete this._players[socket.id];
    }

    //Called after socket connects and player is in memory array
    onPlayerConnected(player) {
        console.log(player.getName() + " connected with socket " + player.getSocketId());
    }

    //Called when socket disconnects
    onPlayerDisconnected(player) {
        console.log(player.getName() + " disconnected with socket " + player.getSocketId());
        if (player.isInLobby()) {
            player.getLobby().removePlayer(player);
        }
    }

    //Called when player choose the ship type
    onPlayerSetShipType(player, shipType) {
        player.setShipType(shipType);
    }

    //Create new lobby
    createLobby() {
        this._lobbyIndex++;
        var lobby = new Lobby(this._io, this._lobbyIndex);
        this._lobbies.push(lobby);

        return lobby;
    }

    //Removes a lobby
    removeLobby(lobby) {
        if (this._lobbies.indexOf(lobby) > -1) {
            //Removes users from lobby
            lobby.destroy();

            this._lobbies.splice(this._lobbies.indexOf(lobby), 1);
        }
    }

    //Find a lobby and put user into it
    setPlayerLobby(player) {
        var game = this;
        //If lobby count is 0, creates a lobby and puts the player in it
        if (game._lobbies.length == 0) {
            var lobby = game.createLobby();

            lobby.addPlayer(player);
        } else {
            var playerJoinedFreeLobby = false;
            //Check if there's a lobby with free spaces to user join
            for (var i = 0; i < game._lobbies.length; i++) {
                var lobby = game._lobbies[i];
                if (lobby.getPlayerCount() < lobby.getMaxPlayerCount()) {
                    lobby.addPlayer(player);
                    playerJoinedFreeLobby = true;
                    break;
                }
            }

            //If there's no lobbies with free space, creates a new lobby and puts the player in it
            if (playerJoinedFreeLobby == false) {
                var lobby = game.createLobby();

                lobby.addPlayer(player);
            }
        }
    }

    //Return player instance from socket
    getPlayerFromSocket(socket) {
        return this._players[socket.id];
    }

}