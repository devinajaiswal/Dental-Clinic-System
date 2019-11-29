/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.EmployeeDirectory;
import Business.Role.Role;
import Business.UserAccount.UserAccountDirectory;
import Business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public abstract class Organization {

    private String name;
    private WorkQueue workQueue;
    private EmployeeDirectory employeeDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
<<<<<<< HEAD
    private static int counter = 0;

    public enum Type {
        DentalAdmin("Dental Admin Organization"),
        DentalFrontDesk("Dental Front Desk Organization"),
        DentalDentist("Dental Dentist Organization"),
        DentalManager("Dental Manager Organization"),
        InsuranceAdmin("Insurance Admin Organization"),
        InsuranceRepresentative("Insurance Representative Organization"),
        InsurancePolicyManager("Insurance Policy Manager Organization"),
        InsuranceFinanceManager("Insurance Finance Manager Organization");
        
        
=======
    private static int counter=0;
    
    public enum Type{
        Admin("Admin Organization"), Doctor("Doctor Organization"),CustomerRepresentativeOrganization(" Customer Representative"),FinanceManagerOrganization("Finance Manager Organization"), PolicyManagerOrganization("Policy Manager Organization");
<<<<<<< HEAD
>>>>>>> 37b8d98e2b29c4df644a52b92c9beeac6ea9ab29
=======
>>>>>>> 69e29b63ee9e4421195ff5fb2209e64001682b91
        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        employeeDirectory = new EmployeeDirectory();
        userAccountDirectory = new UserAccountDirectory();
        organizationID = counter;
        ++counter;
    }

    public abstract ArrayList<Role> getSupportedRole();

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }

    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    @Override
    public String toString() {
        return name;
    }

}
