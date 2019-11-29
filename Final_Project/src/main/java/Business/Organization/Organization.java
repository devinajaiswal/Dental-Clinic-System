/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Employee.EmployeeDirectory;
import Business.Enterprise.Enterprise;
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
    private Enterprise enterprise;
    private Type type;

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public enum Type {
        DentalAdmin("Dental Admin Organization"),
        DentalFrontDesk("Dental Front Desk Organization"),
        DentalDentist("Dental Dentist Organization"),
        DentalManager("Dental Manager Organization"),
        InsuranceAdmin("Insurance Admin Organization"),
        InsuranceRepresentative("Insurance Representative Organization"),
        InsurancePolicyManager("Insurance Policy Manager Organization"),
        InsuranceFinanceManager("Insurance Finance Manager Organization");
        
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
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public static Organization createOrganization(String organizationType) {
        Organization organization = null;

        if (organizationType.equals(Organization.Type.DentalAdmin.getValue())) {
            organization = new DentalAdminOrganization();
        }

        return organization;
    }

}
