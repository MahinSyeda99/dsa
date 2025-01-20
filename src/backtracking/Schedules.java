package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Schedules {

    public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
        int totalHoursWorked = 0;
        //Find total hours worked
        int noOfDaysRemaining = 0;
        for (int i = 0; i < 7; i++) {
            if (pattern.charAt(i) != '?') {
                totalHoursWorked = totalHoursWorked + pattern.charAt(i) - '0';
            } else {
                //Find number of days remaining to work
                noOfDaysRemaining++;
            }
        }
        int noOfHoursRemaining = workHours - totalHoursWorked;
        List<String> result = new ArrayList<>();
        //If no remaining hours to fulfill, replace ? with 0
        if (noOfHoursRemaining == 0) {
            result.add(pattern.replace('?', '0'));
            return result;
        }
        List<List<Integer>> possibilities = new ArrayList<>();
        //Generate all combinations of sum = noOfHoursRemaining with each day hours not exceeding dayHours for noOfDaysRemaining
        generateCombinations(possibilities, dayHours, noOfHoursRemaining, noOfDaysRemaining, new ArrayList<>());
        for (List<Integer> l : possibilities) {
            StringBuilder newStr = new StringBuilder();
            int j = 0;
            for (int i = 0; i < 7; i++) {
                if (pattern.charAt(i) == '?') {
                    newStr.append((j < l.size()) ? l.get(j++) : '0');
                } else {
                    newStr.append(pattern.charAt(i));
                }
            }
            result.add(newStr.toString());
        }
        return result;
    }

    public static void generateCombinations(List<List<Integer>> possibilities, int dayHours, int hoursRemanining, int noOfDays, List<Integer> l) {
        if (noOfDays == 0 && hoursRemanining == 0) {
            possibilities.add(new ArrayList<>(l));
            return;
        }
        //Hours remaining is not zero, then not a valid combination
        if (noOfDays == 0) {
            return;
        }
        for (int i = 0; i <= dayHours; i++) {
            //If i > hoursRemaining, then its invalid case, and we can break
            if ( i > hoursRemanining) {
                break;
            }
            l.add(i);
            generateCombinations(possibilities, dayHours, hoursRemanining - i, noOfDays - 1, l);
            l.remove(l.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> schedules = findSchedules(24, 4, "08??840");
        System.out.println(schedules);
        schedules = findSchedules(56, 8, "???8???");
        System.out.println(schedules);
    }
}
