package swtich;

public class switchh {
    public static void main(String[] args) {
        int day = 4;
        String dayName;
        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid Day! Please enter a number between 1 and 7.";
                break;
        }

        // using lambda fn in switch statement
        // String dayName = switch (day) {
        // case 1 -> "Monday";
        // case 2 -> "Tuesday";
        // case 3 -> "Wednesday";
        // case 4 -> "Thursday";
        // case 5 -> "Friday";
        // case 6 -> "Saturday";
        // case 7 -> "Sunday";
        // default -> "Invalid Day! Please enter a number between 1 and 7.";
        // };

        System.out.println("Selected day is: " + dayName);
    }
}