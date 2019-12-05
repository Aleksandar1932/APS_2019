package mk.ukim.finki.aps.lab2.zad2;

public class Vraboten {
    private int ID;
    private int salary;

    public Vraboten(int ID, int salary) {
        this.ID = ID;
        this.salary = salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Vraboten{" +
                "ID=" + ID +
                ", salary=" + salary +
                '}';
    }
}
