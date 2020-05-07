package Project1;

import java.util.Scanner;

public class Project1 {
    static Scanner input = new Scanner(System.in);
    static int[][] block = new int [4][4];//当前方块
    static int[][] nextblock = new int [4][4];//下一个方块
    static int[][] downblock = new int [4][4];//底部提醒的方块
    static int[][] Iblock = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Jblock = {
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Lblock = {
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Oblock = {
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Sblock = {
            {0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Tblock = {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static int[][] Zblock = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    static String[][] table = new String[16][13];//界面数组
    static String [][] smalltable = new String[4][4];
    static int centerX =0;
    static int centerY = 5;
    static int score = 0;



    public static void main(String[] args) {
            initialize();
            initializesmall();
            random();
            nextrandom();
            printFirst();
            flag: while (true) {
                if (over()) {
                    System.out.println("Game over!\nYour final score is  "+score);
                    System.out.println("\n\n\n");
                    initialize();
                    initializesmall();
                    random();
                    nextrandom();
                    printFirst();
                    continue flag;
                }
                clear();
                cleardown();
                command();
                clearline();
                getdownblock();
                gettable();
                getsmalltable();
                printsmalltable();//打印下一个方块界面
                printScore();
                Printtable();
                if (stop()) {
                    block = nextblock;
                    initializesmall();
                    nextrandom();
                    centerX = 0;
                    centerY = 5;
                }
            }
        }

    private static void printFirst(){
        for (int i = 1; i <= 7; i++) {
            switch (i) {
                case 1: {
                    System.out.println("-------------------------------------------------------");
                    break;
                }
                case 2: {
                    System.out.println("|                        Tetris                       |");
                    break;
                }
                case 3: {
                    System.out.println("| - - - - - - - - - - - - - - - - - - - - - - - - - - |");
                    break;
                }
                case 4: {
                    System.out.println("|                        input                        |");
                    break;
                }
                case 5: {
                    System.out.println("|                     s : Start Game                  |");
                    break;
                }
                case 6: {
                    System.out.println("|                     q : Quit Game                   |");
                    break;
                }
                case 7: {
                    System.out.println("-------------------------------------------------------");
                    break;
                }
            }
        }


        System.out.println("Please input your command ('s' to start , 'q' to quit )");
        char ch = input.next().charAt(0);
        if (ch == 'q'){
            System.out.print("Game over!");
            System.exit(0);
        }else
            System.out.print("The game is ready.\nLet's start the game !!!!\nPlease input s again to enjoy the game.");
        }

    private static void random(){
            int random = (int)(Math.random()* 7);
            switch (random){
                case 0: {
                    block = Iblock;
                    break;
                }
                case 1:{
                    block = Jblock;
                    break;
                }
                case 2:{
                    block = Lblock;
                    break;
                }
                case 3:{
                    block = Oblock;
                    break;
                }
                case 4:{
                    block =Sblock;
                    break;
                }
                case 5:{
                    block = Tblock;
                    break;
                }
                case 6:{
                    block = Zblock;
                    break;
                }
            }
        }

    private static void nextrandom(){
            int random = (int)(Math.random()* 7);
            switch (random){
                case 0: {
                    nextblock = Iblock;
                    break;
                }
                case 1:{
                    nextblock = Jblock;
                    break;
                }
                case 2:{
                    nextblock = Lblock;
                    break;
                }
                case 3:{
                    nextblock = Oblock;
                    break;
                }
                case 4:{
                    nextblock =Sblock;
                    break;
                }
                case 5:{
                    nextblock = Tblock;
                    break;
                }
                case 6:{
                    nextblock = Zblock;
                    break;
                }
            }
        }

    private static void initialize() {
        for (int i = 0; i <= 14; i++) {//初始化界面
            table [i][0] = "| ";
            for (int j = 1; j <= 11; j++) {
                table[i][j] = "  ";
            }
            table [i][12] ="|";
        }
        for (int i = 0; i <= 12; i++)
            table[15][i] = "--";
    }

    private static void initializesmall(){
            for (int i = 0; i <= 3; i++) {//初始化以及清除小界面（下一方块提示）
               for (int j = 0; j <= 3; j++) {
            smalltable[i][j] = "  ";
               }
            }
        }

    private static void getsmalltable(){
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (nextblock[i][j] == 1)
                        smalltable[i][j] = "* ";
                }
            }
        }

    private static void gettable() {
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (block[i][j] == 1)
                        table[centerX + i][centerY + j] = "* ";
                }
            }
        }

    private static void printsmalltable() {
        System.out.println("Next Block will be ");
        for(int i = 0;i <= 3 ;i++){
            for (int j = 0;j<=3 ; j++){
                System.out.print(smalltable [i][j]);
            }
            System.out.print("\n");
        }

    }

    private static void Printtable() {//打印
            for (int i = 1; i <= 15; i++) {
                for (int j = 0; j <= 12; j++) {
                    System.out.print(table[i][j]);
                }
                System.out.print("\n");
            }
            System.out.println("a : left    d : right\n s : keep    w : spin\n" +
                    "x : reach the bottom    c : clear the last line and gain scores\n" +
                    "r : clear all the blocks you have had and gain scores.");
        }

    private static void command() {
            char command = input.next().charAt(0);
            switch (command) {
                case 'a': {
                    centerY -= 1;
                    centerY += judgement();
                    centerX += 1;
                    break;
                }
                case 'd': {
                    centerY += 1;
                    centerY += judgement();
                    centerX += 1;
                    break;
                }
                case 's': {
                    centerX += 0;
                    break;
                }
                case 'w': {
                    if(block != Oblock)
                    spin();
                    centerX += 1;
                    break;
                }
                case 'x':{
                    centerX += fall();
                    break;
                }
                case 'c':{
                    cleardownline();
                    break;
                }
                case'r':{
                    getscores();
                    initialize();
                    break;
                }
            }
        }

    private static void clear() {
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (block[i][j] == 1)
                    table[centerX + i][centerY + j] = "  ";
            }
        }
    }

    private static boolean over() {
            boolean [] over = new boolean[15];
            for(int i =0 ; i<= 14 ; i++){
                if (table[i][1].equals("* ") || table[i][2].equals("* ") ||table[i][3].equals("* ") ||table[i][4].equals("* ")
                        ||table[i][5].equals("* ") ||table[i][6].equals("* ") ||table[i][7].equals("* ") ||table[i][8].equals("* ")
                        ||table[i][9].equals("* ") ||table[i][10].equals("* ") ||table[i][11].equals("* "))
                    over [i] = true;
            }
            if(over[1] &&over[2] &&over[3] &&over[4] &&over[5] &&over[6] &&over[7] &&over[8] &&over[9] &&over[10] &&over[11] &&over[12] &&over[13] &&over[14])
                return true;
            else
                return false;
        }//判断游戏结束

    private static void spin() {//旋转
            int[][] temp = new int[3][3];
            int[][] tempI = new int [4][4];
            if (block != Iblock){
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        temp[i][j] = block[i][j];
                    }
                }
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        block[i][j] = temp[(2 - j)][i];
                    }
                }
                if ( (rightboundary()&&leftboundary())) {
                    for (int i = 0; i <= 2; i++) {
                        for (int j = 0; j <= 2; j++) {
                            block[i][j] = temp[i][j];
                        }
                    }
                }
            }
            else {
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        tempI[i][j] = block[i][j];
                    }
                }
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 3; j++) {
                        block[i][j] = tempI[(3 - j)][i];
                    }
                }
                if ( (rightboundary()&&leftboundary())) {
                    for (int i = 0; i <= 3; i++) {
                        for (int j = 0; j <= 3; j++) {
                            block[i][j] = tempI[i][j];
                        }
                    }
                }
            }
        }//旋转的实现

    private static boolean stop() {
            for (int i = 0 ;i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (block[i][j] == 1){
                        if ((centerX + i ) >= 14 ) {
                            return true;
                        }
                        if (table[thelastdig(j)+ 1 ][centerY + j].equals("* ")) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }//判定方块停止

    private static int thelastdig (int j) {
            int last = 0;
            for (int i = 0; i <= 3; i++) {
//            for (int j = 0; j <= 2; j++) {
                if (block[i][j] == 1) {
                    last = centerX + i;
                }
//            }
            }
            return last;
        }//当前方块最下端的方块

    private static boolean leftboundary(){//判断能否向左移动
            for (int i = 0 ;i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (block[i][j] == 1) {
                        if ((centerY + j -1 ) < 0 ){
                            return true;
                        }
                        if (table[centerX + i][theleftdig(i)-1].equals("* ") ){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    private static int theleftdig(int i ){
            int left = 0;
            for (int j = 3 ;j >= 0; j--) {
                //for (int i = 0; i <= 2; i++) {
                if (block[i][j] == 1) {
                    left =  centerY + j;
                }
                //}
            }
            return left;
        }//当前方块最左端的方块

    private static boolean rightboundary(){//判断能否向右移动
            for (int i = 0 ;i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (block[i][j] == 1 ) {
                        if ((centerY + j + 1 ) >= 13){
                            return true;
                        }
                        //System.out.println(centerY + j + 1);
                        if (table [centerX + i][therightdig(i)].equals("* ") ) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    private static int therightdig(int i){
            int right = 0;
            for (int j = 0 ;j <= 3; j++) {
                //for (int i = 0; i <= 2; i++) {
                if (block[i][j] == 1) {
                    right = centerY + j ;
                }
                //}
            }
            return right;
        }//当前方块最右端的方块

    private static int judgement (){
            int judge = 0 ;
            if(rightboundary())
                judge --;
            if(leftboundary())
                judge ++ ;
            return judge;
        }

    private static void clearline() {
            for (int i = 2; i <= 14; i++) {
                if(table[i][1].equals("* ") &&table[i][2].equals("* ") &&table[i][3].equals("* ") &&table[i][4].equals("* ")
                        &&table[i][5].equals("* ") &&table[i][6].equals("* ") &&table[i][7].equals("* ") &&table[i][8].equals("* ")
                        &&table[i][9].equals("* ") &&table[i][10].equals("* ") &&table[i][11].equals("* ")) {
                    score += 10;
                    for (int j = 1; j <= 11; j++) {
                        for (int m = i; m >= 3; m--) {
                            table[m][j] = table[m- 1][j];
                        }
                    }
                }
            }
            for(int j =1; j<=11;j++){
                table[2][j] = "  ";
            }
        }

    private static void printScore(){
            System.out.println("Now  your  score is  " + score);
        }

    private static int fall(){
            int fall = 0;
            int beforeCenttrX = centerX;
            while(!stop()){
                fall ++;
                centerX ++;
            }
            centerX = beforeCenttrX;
            return fall;
        }//获得应该下坠的格数

    private static void getdownblock(){
            downblock = block;
            for (int i = 0; i <= 3; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (downblock[i][j] == 1) {
                        table[centerX + fall()+ i ][centerY + j] = "+ ";
                    }
                }
            }
        }

    private static void cleardown() {
            for(int i = 2 ; i <= 14 ; i++){
                for(int j = 1 ; j <= 11; j++){
                    if (table [i][j].equals("+ "))
                        table [i][j] = "  ";
                }
            }
        }//遍历整个界面，找到“+ ”就清除

    private static void cleardownline(){
                if (table[14][1].equals("* ") || table[14][2].equals("* ") ||table[14][3].equals("* ") ||table[14][4].equals("* ")
                        ||table[14][5].equals("* ") ||table[14][6].equals("* ") ||table[14][7].equals("* ") ||table[14][8].equals("* ")
                        ||table[14][9].equals("* ") ||table[14][10].equals("* ") ||table[14][11].equals("* ")) {
                    score += 10;
                }
            for (int j = 1; j <= 11; j++) {
                for (int m =14 ; m >= 3; m--) {
                    table[m][j] = table[m - 1][j];
                }
            }
            for(int j =1; j<=11;j++){
                table[2][j] = "  ";
            }
        }

    private static void getscores(){
            int number = 0;
            if (table[14][1].equals("* ") || table[14][2].equals("* ") ||table[14][3].equals("* ") ||table[14][4].equals("* ")
                    ||table[14][5].equals("* ") ||table[14][6].equals("* ") ||table[14][7].equals("* ") ||table[14][8].equals("* ")
                    ||table[14][9].equals("* ") ||table[14][10].equals("* ") ||table[14][11].equals("* ")) {
                number= 10;
            }
            if (table[14][1].equals("* ") || table[14][2].equals("* ") ||table[14][3].equals("* ") ||table[14][4].equals("* ")
                    ||table[14][5].equals("* ") ||table[14][6].equals("* ") ||table[14][7].equals("* ") ||table[14][8].equals("* ")
                    ||table[14][9].equals("* ") ||table[14][10].equals("* ") ||table[14][11].equals("* ")) {
                if (table[13][1].equals("* ") || table[13][2].equals("* ") ||table[13][3].equals("* ") ||table[13][4].equals("* ")
                        ||table[13][5].equals("* ") ||table[13][6].equals("* ") ||table[13][7].equals("* ") ||table[13][8].equals("* ")
                        ||table[13][9].equals("* ") ||table[13][10].equals("* ") ||table[13][11].equals("* "))
                number= 20 * 2;
            }
            if (table[14][1].equals("* ") || table[14][2].equals("* ") ||table[14][3].equals("* ") ||table[14][4].equals("* ")
                    ||table[14][5].equals("* ") ||table[14][6].equals("* ") ||table[14][7].equals("* ") ||table[14][8].equals("* ")
                    ||table[14][9].equals("* ") ||table[14][10].equals("* ") ||table[14][11].equals("* ")) {
                if (table[13][1].equals("* ") || table[13][2].equals("* ") ||table[13][3].equals("* ") ||table[13][4].equals("* ")
                        ||table[13][5].equals("* ") ||table[13][6].equals("* ") ||table[13][7].equals("* ") ||table[13][8].equals("* ")
                        ||table[13][9].equals("* ") ||table[13][10].equals("* ") ||table[13][11].equals("* ")){
                    if (table[12][1].equals("* ") || table[12][2].equals("* ") ||table[12][3].equals("* ") ||table[12][4].equals("* ")
                            ||table[12][5].equals("* ") ||table[12][6].equals("* ") ||table[12][7].equals("* ") ||table[12][8].equals("* ")
                            ||table[12][9].equals("* ") ||table[12][10].equals("* ") ||table[12][11].equals("* "))
                    number= 40 * 3;
                }
            }
            if (table[14][1].equals("* ") || table[14][2].equals("* ") ||table[14][3].equals("* ") ||table[14][4].equals("* ")
                    ||table[14][5].equals("* ") ||table[14][6].equals("* ") ||table[14][7].equals("* ") ||table[14][8].equals("* ")
                    ||table[14][9].equals("* ") ||table[14][10].equals("* ") ||table[14][11].equals("* ")) {
                if (table[13][1].equals("* ") || table[13][2].equals("* ") ||table[13][3].equals("* ") ||table[13][4].equals("* ")
                        ||table[13][5].equals("* ") ||table[13][6].equals("* ") ||table[13][7].equals("* ") ||table[13][8].equals("* ")
                        ||table[13][9].equals("* ") ||table[13][10].equals("* ") ||table[13][11].equals("* ")){
                    if (table[12][1].equals("* ") || table[12][2].equals("* ") ||table[12][3].equals("* ") ||table[12][4].equals("* ")
                            ||table[12][5].equals("* ") ||table[12][6].equals("* ") ||table[12][7].equals("* ") ||table[12][8].equals("* ")
                            ||table[12][9].equals("* ") ||table[12][10].equals("* ") ||table[12][11].equals("* ")){
                        if (table[11][1].equals("* ") || table[11][2].equals("* ") ||table[11][3].equals("* ") ||table[11][4].equals("* ")
                                ||table[11][5].equals("* ") ||table[11][6].equals("* ") ||table[11][7].equals("* ") ||table[11][8].equals("* ")
                                ||table[11][9].equals("* ") ||table[11][10].equals("* ") ||table[11][11].equals("* "))
                        number= 80 * 4;
                    }
                }
            }
            score += number;
        }

    }//类括号