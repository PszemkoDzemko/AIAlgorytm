public class Player {
    int id;
    String name;
    int overall;
    int price;

    public Player(int id, String name, int overall, int price  ) {
        this.id  =  id;
        this.name = name;
        this.overall = overall;
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", overall=" + overall +
                ", price=" + price;
    }
}
