package org.academiadecodigo.argicultores;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Player implements Runnable {

    private String name;
    private int score;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private PlayerInterface playerInterface;
    private Server server;
    private boolean readyToStart;
    public List<Player> arrayList;
    private boolean arePlayersReady;

    public Player(Socket socket, String name, Server server, List<Player> arrayList) {

        score = 0;
        this.name = name;
        this.socket = socket;
        this.server = server;
        this.arrayList = arrayList;


        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        playerInterface = new PlayerInterface(this, server, arrayList);

        /*synchronized (server) {
            while (!arePlayersReady) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        playerInterface.gameInterface();
        //game.init();
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean isReadyToStart() {
        return readyToStart;
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public BufferedWriter getBw() {
        return bw;
    }
    public void close(){
        try {
            bw.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
