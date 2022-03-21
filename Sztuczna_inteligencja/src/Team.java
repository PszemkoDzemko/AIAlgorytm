public class Team {
    Player[] players;
    Double overall;
    int price;

    public Team(Player[] players) {
        double sum= 0.0;
        int sumPrice=0;
        this.players = players;
        for (Player player: players) {
            sum=sum+ player.overall;
            sumPrice = sumPrice + player.price;
        }
        this.overall  = sum/players.length;
        this.price = sumPrice;

    }
    public String readTeam(){
        StringBuilder allOfPlayers = new StringBuilder();
        for (Player palyer: players) {
            allOfPlayers.append("\n").append(palyer.toString());
        }
        allOfPlayers.append("\n Overall: ").append(this.overall).append(" Full price: ").append(this.price);
        return String.valueOf(allOfPlayers);
    }

}
