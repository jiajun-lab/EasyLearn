package As3;

public class Marathon {
    public static void main (String[] arguments) {
        String[] names = {
                "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
                "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
                "Aaron", "Kate"
        };
        int[] times = {
                341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
                343, 317, 265
        };
        int timesIndex = 0;
        for (int i = 1; i < names.length; i++) {
            if(times[timesIndex] > times[i]){
                timesIndex = i;
            }
        }
        System.out.println(names[timesIndex] + ": " + times[timesIndex]);
    }
}
