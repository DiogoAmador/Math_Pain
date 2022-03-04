package org.academiadecodigo.argicultores;

import java.net.Socket;
import java.util.ArrayList;

public class PlayerHandler {

    public ArrayList<Player> players;
    private Socket socket;


    public PlayerHandler() {

        players = new ArrayList<>();

    }
}
