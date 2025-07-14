package singletonpack;

public class EmployeeSingeton {
    private String name;
    private int id;
    private String department;

    //. private constructor to prevent instantiation
    private EmployeeSingeton(){
        this.name = "Default name";
        this.id =0;
        this.department = "General" ;
    }

    // Static inner class -- loaded only when getinstance() is called
    private static class Holder{
        private static final EmployeeSingeton INSTANCE = new EmployeeSingeton();
    }

    public static EmployeeSingeton getInstance(){
        return Holder.INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void display() {
        System.out.println("Employee [ID=" + id + ", Name=" + name + ", Department=" + department + "]");
    }


    public static void main(String[] args) {
        EmployeeSingeton emp = EmployeeSingeton.getInstance();
        emp.setId(101);
        emp.setName("Ranjan");
        emp.setDepartment("Engineering");

        emp.display(); // Output: Employee [ID=101, Name=Ranjan, Department=Engineering]

        // Verify singleton behavior
        EmployeeSingeton anotherEmp = EmployeeSingeton.getInstance();
        anotherEmp.display(); // Same output as above

    }
}
