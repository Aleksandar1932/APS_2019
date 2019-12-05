package mk.ukim.finki.aps.vezbanjekol1;

import mk.ukim.finki.aps.vezbanje1.SLL;
import mk.ukim.finki.aps.vezbanje1.SLLNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Employee implements Comparable<Employee> {
    private int id;
    private int salary;

    public Employee(int id, int salary) {
        this.id = id;
        this.salary = salary;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(other.id, this.id);
    }
}

public class SLLKompanija {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        ArrayList<Employee> employeesList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int idAdd = in.nextInt();
            int salaryAdd = in.nextInt();
            employeesList.add(new Employee(idAdd,salaryAdd));
        }

        int minSalary = in.nextInt();

        System.out.println("Before delete");
        System.out.println(employeesList);

        removeEmployees(employeesList, minSalary);
        System.out.println("After delete");
        Collections.sort(employeesList);
        System.out.println(employeesList);
    }

    public static void removeEmployees(ArrayList<Employee> list, int minSalary) {
        System.out.println("SALARY  to delete" + minSalary);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSalary() < minSalary) {
                list.remove(i);
            }
        }
    }


}