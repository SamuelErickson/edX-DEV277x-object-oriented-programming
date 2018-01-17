import java.util.Scanner;

public class MazeRunner {
    // Sam Erickson, January 2018, Module 4 Final Project
    public static void main(String[] args){
        Maze myMap = intro();
        int moves = 0;
        while (myMap.didIWin()==false&&moves<100){
            inputCheckMove(myMap);
            moves++;
            movesMessage(moves);
        }
        if (myMap.didIWin()==true) {
            System.out.println("Congratulations, you made it out alive!");
            System.out.println("and you did it in "+moves+" moves");
        } else {
            System.out.println("Sorry, but you didn't escape in time- you lose!");
        }
    }
    public static Maze intro(){
        Maze myMap = new Maze();
        System.out.println("Welcome to MAZERUNNER...");
        myMap.printMap();
        return myMap;
    }
    public static String userMove() {
        System.out.print("Where would you like to move? (R, L, U, D)");
        Scanner input = new Scanner(System.in);
        String dir = input.nextLine();
            while (dir.matches("[RLUD]")!=true){
                System.out.print("Invalid selection. Where would you like to move? (R, L, U, D)");
                dir = input.nextLine();
            }
        System.out.println("the selection is "+dir);
        return dir;
    }

    public static String inputCheckMove(Maze myMap) {
        String dir = userMove();
        if (myMap.isThereAPit(dir)) {
            navigatePit(myMap, dir);
            myMap.printMap();
        } else if (dir.equals("R")) {
            if (myMap.canIMoveRight()) {
                myMap.moveRight();
                myMap.printMap();
            } else{
                myMap.printMap();
                System.out.println("Sorry, you've hit a wall");
            }
        }   else if (dir.equals("L")) {
            if (myMap.canIMoveLeft()) {
                myMap.moveLeft();
                myMap.printMap();
            } else {
                myMap.printMap();
                System.out.println("Sorry, you've hit a wall");
            }
        }   else if (dir.equals("U")) {
            if (myMap.canIMoveUp()) {
                myMap.moveUp();
                myMap.printMap();
            } else {
                myMap.printMap();
                System.out.println("Sorry, you've hit a wall");
            }
        }   else if (dir.equals("D")) {
            if (myMap.canIMoveDown()) {
                myMap.moveDown();
                myMap.printMap();
            } else {
                myMap.printMap();
                System.out.println("Sorry, you've hit a wall");
            }
        }   else {
            System.out.println("Something went wrong, restart game.");
        }
        return dir;
    }


    public static void movesMessage(int moves){
        if (moves==50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        } else if (moves==75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (moves==90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (moves==100){
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
    } else {
        System.out.println("You have taken "+moves+" moves so far"); //nothing happens
    }
    }

    public static void navigatePit(Maze myMap, String dir) {
        System.out.println("Watch out! There's a pit ahead, jump it?");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if (choice.substring(0,1).equals("y")||choice.substring(0,1).equals("Y")){
            myMap.jumpOverPit(dir);
        } else {
            return;
        }
    }
}
