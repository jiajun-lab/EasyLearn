package As2;

public class FooCorporation {
    private static String strategy(double basePay, int workingHours){
        if(basePay < 8.00){
            return basePay+ "$ base pay is too low";
        }

        if(workingHours > 70){
            return workingHours+ "h working hours is too long";
        }

        StringBuilder stringBuilder = new StringBuilder();
        if(workingHours < 40){
            double baseSalary = basePay * workingHours;
            stringBuilder.append(baseSalary);
        }else {
            double baseSalary = basePay * 40;
            double overtimeSalary = workingHours * (workingHours-40);
            stringBuilder.append(overtimeSalary+baseSalary);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(7.5, 35);
        Employee e2 = new Employee(8.2, 47);
        Employee e3 = new Employee(10.0, 73);
        System.out.println(strategy(e1.getBasePay(), e1.getWorkingHours()));
        System.out.println(strategy(e2.getBasePay(), e2.getWorkingHours()));
        System.out.println(strategy(e3.getBasePay(), e3.getWorkingHours()));
    }
}

class Employee{
    private double basePay;
    private int workingHours;

    public double getBasePay() {
        return basePay;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    Employee(double basePay, int workingHours){
        setBasePay(basePay);
        setWorkingHours(workingHours);
    }
}
