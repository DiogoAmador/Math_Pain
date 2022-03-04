package org.academiadecodigo.argicultores;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerInterface {

    private Player p;
    private BufferedReader br;
    private BufferedWriter bw;
    private Server server;
    private boolean allPlayersReady;
    private Prompt prompt = null;
    public List<Player> allPlayers;


    public PlayerInterface(Player p, Server server, List<Player> arrayList) {

        this.server = server;
        this.allPlayers = arrayList;

        try {
            br = new BufferedReader(new InputStreamReader(p.getSocket().getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(p.getSocket().getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.p = p;

        try {
            prompt = new Prompt(p.getSocket().getInputStream(), new PrintStream(p.getSocket().getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringInputScanner username = new StringInputScanner();
        username.setMessage("Username: ");
        String name = prompt.getUserInput(username);
        p.setName(name);
        System.out.println("Welcome " + name);
        server.checkIn();
        allPlayers.add(p);


    }

    public synchronized void gameInterface() {
        GameEngine ge = new GameEngine(5);

        while (ge.getLevel() <= ge.getMaxLevels() && !server.isVictory()) {
            StringInputScanner question = new StringInputScanner();
            question.setMessage(ge.levelGenerator(ge.getLevel()));
            String answer = prompt.getUserInput(question);

            if (ge.getLevels().containsValue(Integer.parseInt(answer))) {
                ge.increaseLevel();
            }
        }
        System.out.println("bitoria");
        server.setVictory();
        server.gameOver();

        /*while(allPlayers.size() < 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
}
