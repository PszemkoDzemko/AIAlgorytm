
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        int  HMS=10, budget=1000000;
        List<Team> HM = new ArrayList<Team>();


        for (int j = 1; j < (HMS+1); j++) {

            HM.add(generateTeam(budget));
        }
        saveTeams(HM);

    }

    public static Team generateTeam(int budget) throws IOException {

        int  equal=0, sumPrice=0, iteracja=0;

        String nazwa =  "randomdata.csv";
        Player[]  players = new Player[6];
        String[] parts = new String[4];


        Random random = new Random();
        RandomAccessFile randomAccessFile = new RandomAccessFile(nazwa,"r");
        System.out.println("------------  Team ------------");
        do {
            sumPrice  = 0;

            for (int i=0; i<6; i++){

                iteracja++;

                do {

                    do {
                        randomAccessFile.seek(random.nextInt((int) randomAccessFile.length()-62));
                        int ch;
                        do {
                            ch = randomAccessFile.read();
                        }while (ch != '\n');
                        for (Player  player:players) {
                            if (player !=  null){
                                if (randomAccessFile.getFilePointer() == player.id) {
                                    equal = 1;
                                    break;
                                }
                            }
                        }
                    }while (equal == 1);
//System.out.println(randomAccessFile.getFilePointer());
                    if (randomAccessFile.readLine() ==  null) {  equal=1;  }
                    else parts  = randomAccessFile.readLine().split(";");

                }while (equal == 1);


                if (parts.length==0)System.out.println(randomAccessFile.getFilePointer());

                sumPrice=sumPrice+ Integer.parseInt(parts[3]);

                players[i] = new Player(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));

            }

        }while (sumPrice>budget);
        //System.out.println(iteracja+" Cena: "+sumPrice);
        Team team= new Team(players);
        int sum=0;
        for (Player player : players){
            System.out.println(player.toString());
            sum=sum+ player.price;
        }
        //System.out.println(sum);
        return team;
    }

    public static void saveTeams(List<Team> teams){
        int i=1;

        try {
            FileWriter myWriter = new FileWriter("teams.txt");

            for (Team team :teams) {

                myWriter.write("\n----------- Team "+i+" -----------");
                myWriter.write(team.readTeam());
                i++;

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


