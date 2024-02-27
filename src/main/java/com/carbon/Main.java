package com.carbon;

import com.carbon.engine.GameEngine;
import com.carbon.engine.GameEngineImpl;
import com.carbon.entities.Adventurer;
import com.carbon.entities.Mountain;
import com.carbon.entities.Board;
import com.carbon.entities.Treasure;
import com.carbon.utils.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File file = new File("mock.txt");

        GameEngine gameEngine = initBoard(file);

        gameEngine.play();

        printResult(gameEngine);
    }

    private static GameEngine initBoard(File file) {
        int boardWidth = 0;
        int boardHeight = 0;
        List<Mountain> mountains = new ArrayList<>();
        List<Treasure> treasures = new ArrayList<>();
        List<Adventurer> adventurers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null) {
                if (line.contains("#")) {
                    System.err.println(line);
                } else {
                    String[] inputs = StringUtil.sanitize(line);

                    switch(inputs[0]) {
                        case "C":
                            boardWidth = Integer.parseInt(inputs[1]);
                            boardHeight = Integer.parseInt(inputs[2]);
                            break;
                        case "M":
                            mountains.add(new Mountain(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
                            break;
                        case "T":
                            treasures.add(new Treasure(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])));
                            break;
                        case "A":
                            adventurers.add(new Adventurer(inputs[1], Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]), inputs[4].charAt(0), inputs[5]));
                            break;
                        default: break;
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        Board board = new Board(boardWidth, boardHeight, mountains, treasures);
        return new GameEngineImpl(board, adventurers);
    }

    private static void printResult(GameEngine gameEngine) {
        Board board = gameEngine.getBoard();
        List<String> mountains = new ArrayList<>();
        List<String> treasures = new ArrayList<>();

        for (int i=0; i<board.getWidth(); i++) {
            for (int j=0; j<board.getHeight(); j++) {
                if (board.getContent(i, j) instanceof Mountain m) {
                    mountains.add("M - " + m.getX() + " - " + m.getY());
                }
                if ((board.getContent(i, j) instanceof Treasure t) && t.getQuantity() != 0) {
                    treasures.add("T - " + t.getX() + " - " + t.getY() + " - " + t.getQuantity());
                }
            }
        }

        List<Adventurer> adventurers = gameEngine.getAdventurers();
        List<String> stringAdventurers = new ArrayList<>();

        adventurers.forEach(a ->
            stringAdventurers.add("A - " + a.getName() + " - " + a.getX() + " - " + a.getY() + " - " + a.getOrientation() + " - " + a.getMovement())
        );

        writeInFile(board, mountains, treasures, stringAdventurers);
    }

    private static void writeInFile(Board board, List<String> mountains, List<String> treasures, List<String> stringAdventurers) {
        final String FILE_NAME = "result.txt";
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)){
            PrintWriter writer = new PrintWriter(fileWriter);

            writer.printf("C - %d - %d%n", board.getWidth(), board.getHeight());
            mountains.forEach(writer::println);
            writer.println("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
            treasures.forEach(writer::println);
            writer.println("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}");
            stringAdventurers.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
