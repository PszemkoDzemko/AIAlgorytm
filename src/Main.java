
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static ArrayList<Player> playersList = new ArrayList<>();
    static Random r=new Random();

    public static void main(String[] args) throws IOException {

        int  HMS=10, budget=1000000, HMCR=30, NI=1000050, r1,r2,same=0;

        ArrayList<Team> HM = new ArrayList<Team>();
        //ArrayList<Player> players = new ArrayList<>();
        Player[] newPlayers = new Player[6];
        Team newTeam;

        String[] parts = new String[4];
        Player tempPlayer = null;
        String nazwa =  "randomdata.csv";
        InputStream is = null;
        BufferedReader reader = null;

        try {
            is = new FileInputStream(nazwa);

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(nazwa)));
            //System.out.println("Reading file...");
            String line = reader.readLine();
            while (line != null) {
                parts = line.split(";");
                tempPlayer = new Player(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                playersList.add(tempPlayer);
                line = reader.readLine();
            }
            is.close();
            reader.close();
        }finally {
                if (is != null) {
                    is.close();
                }
                if (reader != null) {
                    reader.close();
                }
        }

        for (int j = 1; j < (HMS+1); j++) {

            HM.add(generateTeam(budget));
        }

        saveTeams(HM,"teamsR1.csv");
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
            same=0;
            for (int j = 0; j < HMS; j++) {
                for (int k = 0; k < 6; k++) {
                    if(HM.get(j).getPlayers()[k]==newTeam.getPlayers()[k]){
                        same++;
//                        System.out.println("same"+same);
                    }
                }
                if (same<6){
                    if (HM.get(j).overall < newTeam.overall){
                        HM.set(j,newTeam);
                        break;
                    }
                }
            }
        }

        saveTeams(HM,"teams.csv");

    }

    public static Team generateTeam(int budget) throws IOException {

        int  equal=0, sumPrice=0, iteracja=0;
        Player tempPlayer = null;
        Player[]  players = new Player[6];


        do {
            //System.out.println("szukam jeszcze raz ... ");
            sumPrice  = 0;

            for (int i=0; i<6; i++){

                iteracja++;

                do {
                    equal=0;
                    tempPlayer = playersList.get(r.nextInt(playersList.size()));
                    for (Player player:players) {
                        if (player != null){
                            if (tempPlayer.id==player.id){
                                equal=1;
                            }
                        }
                    }
                }while (equal == 1);
                sumPrice=sumPrice+ tempPlayer.price;
                players[i] = tempPlayer;
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
