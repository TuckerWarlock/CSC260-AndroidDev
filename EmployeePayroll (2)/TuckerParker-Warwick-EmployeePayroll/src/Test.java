// Employee hierarchy test program.
import java.util.Scanner; // program uses Scanner to obtain user input
import java.util.ArrayList;
import java.util.Collections;

public class Test {
   public static void main(String[] args) {
      // create subclass objects
      SalariedEmployee salariedEmployee = 
         new SalariedEmployee(
         "John", "Smith", "111-11-1111", 6, 15, 1944, 800.00);
      HourlyEmployee hourlyEmployee = 
         new HourlyEmployee(
         "Karen", "Price", "222-22-2222", 12, 29, 1960, 16.75, 40);
      CommissionEmployee commissionEmployee = 
         new CommissionEmployee(
         "Sue", "Jones", "333-33-3333", 9, 8, 1954, 10000, .06);
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee(
         "Bob", "Lewis", "444-44-4444", 3, 2, 1965, 5000, .04, 300);

      System.out.println("Employees processed individually:\n");
      
      System.out.printf("%s\n%s: $%,.2f\n\n", 
         salariedEmployee, "earned", salariedEmployee.earnings());
      System.out.printf("%s\n%s: $%,.2f\n\n",
         hourlyEmployee, "earned", hourlyEmployee.earnings());
      System.out.printf("%s\n%s: $%,.2f\n\n",
         commissionEmployee, "earned", commissionEmployee.earnings());
      System.out.printf("%s\n%s: $%,.2f\n\n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings());

      // create four-element Employee array
      //Employee[] employees = new Employee[4]; 

      // initialize array with Employees
      //employees[0] = salariedEmployee;
      //employees[1] = hourlyEmployee;
      //employees[2] = commissionEmployee; 
      //employees[3] = basePlusCommissionEmployee;
      
      //create ArrayList for Employees
      ArrayList<Employee> employees = new ArrayList<Employee>();
      
      //add all the different types of employees, all extending employee class
      employees.add(commissionEmployee);
      employees.add(basePlusCommissionEmployee);
      employees.add(hourlyEmployee);
      employees.add(salariedEmployee);
      
      //sort employees by last name
      Collections.sort(employees);

      Scanner input = new Scanner(System.in); // to get current month
      int currentMonth;

      // get and validate current month
      do {
         System.out.print("Enter the current month (1 - 12): ");
         currentMonth = input.nextInt();
         System.out.println();
      } while ((currentMonth < 1) || (currentMonth > 12));

      System.out.println("Employees processed polymorphically:\n");
      
      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if (currentEmployee instanceof BasePlusCommissionEmployee) {
            // downcast Employee reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               (BasePlusCommissionEmployee) currentEmployee;

            double oldBaseSalary = employee.getBaseSalary();
            employee.setBaseSalary(1.10 * oldBaseSalary);
            System.out.printf(
               "new base salary with 10%% increase is: $%,.2f\n",
               employee.getBaseSalary());
         }

         // if month of employee's birthday, add $100 to salary
         if (currentMonth == currentEmployee.getBirthDate().getMonth()) {
            System.out.printf(
               "earned $%,.2f %s\n\n", currentEmployee.earnings(), 
               "plus $100.00 birthday bonus");
         }
         else {
            System.out.printf(
               "earned $%,.2f\n\n", currentEmployee.earnings());
         }
      }

      // get type name of each object in employees array
      for (int j = 0; j < employees.size(); j++) {
         System.out.printf("Employee %d is a %s\n", j, 
            employees.get(j).getClass().getName()); 
      }
   input.close(); //close scanner
   }
}
