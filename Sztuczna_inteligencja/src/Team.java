public class Team {
    private Player[] players;
    Double overall;
    int price;

    public Team(Player[] players) {
        double sum= 0.0;
        int sumPrice=0;
        this.players = players;
        for (Player player: players) {
            sum=sum+ player.overall;
            //System.out.println(player.price);
            sumPrice = sumPrice + player.price;
        }
        this.overall  = sum/players.length;
        //System.out.println(players.length);
        this.price = sumPrice;
        //System.out.println(sumPrice);

    }
    public String readTeam(){
        StringBuilder allOfPlayers = new StringBuilder();
        for (Player player: players) {
            allOfPlayers.append("\n").append(player.toString());
        }
        allOfPlayers.append("\n Overall: ").append(this.overall).append(" Full price: ").append(this.price);
        return String.valueOf(allOfPlayers);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
