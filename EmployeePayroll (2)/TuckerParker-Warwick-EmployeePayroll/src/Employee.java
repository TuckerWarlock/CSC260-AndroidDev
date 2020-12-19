import java.security.InvalidParameterException;
//import java.time.LocalDate;
//import java.time.Period;

// Employee abstract superclass.

public abstract class Employee  implements Comparable<Employee>{
   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;
   //private final int age;
   private final Date birthDate;
   

   // constructor
   public Employee(String firstName, String lastName, 
      String socialSecurityNumber, /*int age,*/ int month, int day, int year) {
      this.firstName = firstName;                                    
      this.lastName = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;
      //this.age = age;
      birthDate = new Date(month, day, year);
      
   } 
   
   // return first name
   public String getFirstName() {
      return firstName;
   } 
   
   // enforce First name cannot equal Last name
   public void setLastName() {
	   if(lastName.equals(firstName)) {
		   throw new InvalidParameterException("FirstName and LastName cannot be the same");
	   }
   }

   // return last name
   public String getLastName() {
      return lastName;
   } 

   // return social security number
   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   } 

   // return birth date
   public Date getBirthDate() {
      return birthDate;
   }
   
   //return age of employee in years
/*   public int getAgeInYears() {
	   LocalDate birthday = LocalDate(getBirthDate());
	   LocalDate today = LocalDate.now();
	   Period years = Period.between(birthday, today);
	   int age = years.getYears();
	   return age;
   }
*/

// return String representation of Employee object
   @Override
   public String toString() {
      return String.format("%s %s\n%s: %s\n%s: %s", 
         getFirstName(), getLastName(), 
         "social security number", getSocialSecurityNumber(), 
         "birth date", getBirthDate()); /*"age", getAgeInYears());*/
   } 

   // abstract method must be overridden by concrete subclasses
   public abstract double earnings(); // no implementation here
   
   //implements the Interface Comparable
   @Override
   public int compareTo(Employee otherEmployee) {
	   return this.lastName.compareTo(otherEmployee.getLastName());
   }
} 