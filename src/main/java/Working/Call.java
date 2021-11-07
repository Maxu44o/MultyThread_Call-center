package Working;

public class Call {
    int number;
    String string;

    public Call(String string, int number) {
        this.number = number;
        this.string = string;
    }

    @Override
    public String
    toString() {
        return "#" + number + " поступивший в " + string;
    }
}



