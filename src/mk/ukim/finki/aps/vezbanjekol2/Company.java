package mk.ukim.finki.aps.vezbanjekol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//II partial exam 2019;
class EmployeeEntry {
    String employee;
    String manager;

    public EmployeeEntry(String employee, String manager) {
        this.employee = employee;
        this.manager = manager;
    }

    public String getEmployee() {
        return employee;
    }

    public String getManager() {
        return manager;
    }
}

public class Company {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfEntries = Integer.parseInt(br.readLine());

        ArrayList<String> employees = new ArrayList<>();
        ArrayList<EmployeeEntry> entries = new ArrayList<>();

        for (int i = 0; i < numberOfEntries; i++) {
            String entry = br.readLine();
            String[] tokens = entry.split("\\s+");
            employees.add(tokens[0]);
            entries.add(new EmployeeEntry(tokens[0], tokens[1]));
        }

        System.out.println(proccessEntries(employees, entries));
    }

    public static Map<String, Integer> proccessEntries(ArrayList<String> employees, ArrayList<EmployeeEntry> entries) {
        Map<String, Integer> returnMap = new HashMap<>();
        entries.stream().
                filter(employeeEntry -> employees.contains(employeeEntry.getManager())
                        && !employeeEntry.getManager().equals(employeeEntry.getEmployee()))
                .forEach(employeeEntry -> {
                    String manager = employeeEntry.getManager();
                    returnMap.putIfAbsent(manager, 0);
                    returnMap.computeIfPresent(manager, (k, v) -> v + 1);
                });
        return returnMap;
    }
}
