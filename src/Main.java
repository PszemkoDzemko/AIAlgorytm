
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        int  HMS=10, budget=1000000, HMCR=70, NI=50, r1,r2;
        double sum=0, som;
        Random r=new Random();
        ArrayList<Team> HM = new ArrayList<Team>();
        Player[] newPlayers = new Player[6];
        Team newTeam;

        for (int j = 1; j < (HMS+1); j++) {

            HM.add(generateTeam(budget));
        }

        //saveTeams(HM,"teamsR1.csv");
        //  Nowe i jak lepsze wymieniamy:

        for (int i = 0; i < NI; i++) {
            System.out.println(i);
            r1= r.nextInt(0, 100); //100;


            if (r1 <= HMCR){
                do{
                    for (int j = 0; j < 6; j++) {
                        r2 = r.nextInt(HMS);
                        newPlayers[j] = HM.get(r2).getPlayers()[j];
                    }
                    newTeam = new Team(newPlayers);
                }while(newTeam.price>budget);

                newTeam.setPlayers(newPlayers);
                newPlayers= new Player[6];
            }else {
                newTeam = generateTeam(budget);
            }
            for (int j = 0; j < HMS; j++) {
                if (HM.get(j).overall < newTeam.overall){
                    HM.set(j,newTeam);
                    break;
                }
            }
        }

        saveTeams(HM,"teams.csv");

    }

    public static Team generateTeam(int budget) throws IOException {

        int  equal=0, sumPrice=0, iteracja=0;

        String nazwa =  "randomdata.csv";
        Player[]  players = new Player[6];
        String[] parts = new String[4];


        Random random = new Random();
        RandomAccessFile randomAccessFile = new RandomAccessFile(nazwa,"r");
        //System.out.println("------------  Team ------------");
        do {
            //System.out.println("szukam jeszcze raz ... ");
            sumPrice  = 0;

            for (int i=0; i<6; i++){

                iteracja++;

                do {
                    equal=0;
                    randomAccessFile.seek(random.nextInt((int) randomAccessFile.length()-62));
                    int ch;
                    do {
                        ch = randomAccessFile.read();
                    }while (ch != '\n');
//                        for (Player  player:players) {
//                            if (player !=  null){
//                                if (randomAccessFile.getFilePointer() == player.id) {
//                                    equal = 1;
//                                    break;
//                                }
//                            }
//                        }
                    //                    if (randomAccessFile.readLine() ==  null) {  equal=1;  }
//                    else
                        parts  = randomAccessFile.readLine().split(";");
                    for (Player player:players) {
                        if (player != null){
                            if (Integer.parseInt(parts[0])==player.id){
                                equal=1;
                                System.out.println(parts[0]+" = " + player.id);
                            }
                        }
                    }

                }while (equal == 1);


                if (parts.length==0)System.out.println(randomAccessFile.getFilePointer());

                sumPrice=sumPrice+ Integer.parseInt(parts[3]);

                players[i] = new Player(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2]),Integer.parseInt(parts[3]));

            }

        }while (sumPrice>budget);


        return new Team(players);
    }

    public static void saveTeams(List<Team> teams, String nazwa ){
        int i=1;
/*
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
        }*/

        try {
            FileWriter myWriter = new FileWriter(nazwa);
            String temp;

            for (Team team :teams) {

                for (int j = 0; j < team.getPlayers().length; j++) {
                    temp = team.getPlayers()[j].id +";"+ team.getPlayers()[j].name+";"+ team.getPlayers()[j].overall+";"+ team.getPlayers()[j].price+"\n";
                    myWriter.write(temp);

                }
                i++;
                myWriter.write(";;"+team.overall+";"+team.price);
                myWriter.write("\n");

            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}


