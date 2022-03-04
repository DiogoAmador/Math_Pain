package org.academiadecodigo.argicultores;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private Socket playerSocket;
    private int maxPlayerCount;
    private int playerCount;
    private List<Player> players;
    private boolean victory;
    private BufferedWriter bw;

    public static final int PORT = 4020;
    private boolean arePlayersReady;
    private boolean[] playersReady;


    public Server(int maxPlayerCount) {

        playersReady = new boolean[maxPlayerCount +1];

        try {

            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.maxPlayerCount = maxPlayerCount;
        playerCount = 0;
        players = Collections.synchronizedList(new ArrayList<Player>());
    }


    public synchronized void startServer(){

        while(serverSocket.isBound() && playerCount < maxPlayerCount){

            try {
                playerSocket = serverSocket.accept();
                bw = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
                playerCount++;
                bw.write("Player " + playerCount + " connected");
                bw.newLine();
                bw.flush();
                Player p = new Player(playerSocket, "Player " + playerCount, this, players);
                //players.add(p);
                Thread tr = new Thread(p);
                tr.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }





        }

        //players.get(0).getPlayerInterface().gameInterface();
        //players.get(1).getPlayerInterface().gameInterface();
        //namesReady();


    public void gameOver(){
        for (Player p: players) {
            BufferedWriter bw = p.getBw();

            try {
                bw.newLine();
                bw.write("bye bye");
                bw.newLine();
                bw.flush();
                p.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkIn(){

       playersReady[playerCount] = true;

    }

    /*public boolean isAllPlayersReady(){
        for (int i = 0; i < maxPlayerCount; i++) {
            if(!playersReady[i]){
                return false;
            }
        }
        return true;
    }*/

    public void namesReady(){

        while(players.size() < maxPlayerCount){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //notifyAll();
    }

    public void setVictory() {
        this.victory = true;
    }

    public boolean isVictory() {
        return victory;
    }
}
