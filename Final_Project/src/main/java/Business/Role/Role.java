/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public abstract class Role {

    public abstract RoleType getRoleType();
    
    public enum RoleType {
        SysAdmin("SysAdmin"),
        Customer("Customer"),
        EnterpriseAdmin("EnterpriseAdmin"),
        DentalFrontdesk("DentalFrontdesk"),
        DentalDentist("DentalDentist"),
        DentalManager("DentalManager"),
        InsuranceRepresentative("InsuranceRepresentative"),
        InsurancePolicyManager("InsurancePolicyManager"),
        InsuranceFinanceManager("InsuranceFinanceManager");

        private String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer,
        UserAccount account,
        Organization organization,
        Enterprise enterprise);

    @Override
    public String toString() {
        return this.getRoleType().getValue();
    }

    public static Role createRole(String roleName) {
        Role role = null;
        if (roleName.equals(Role.RoleType.SysAdmin.getValue())) {
            role = new SysAdminRole();
        } else if (roleName.equals(Role.RoleType.Customer.getValue())) {
            role = new CustomerRole();
        }else if (roleName.equals(Role.RoleType.EnterpriseAdmin.getValue())) {
            role = new EnterpriseAdminRole();
        }else if (roleName.equals(Role.RoleType.DentalDentist.getValue())) {
            role = new DentalDentistRole();
        }else if (roleName.equals(Role.RoleType.DentalFrontdesk.getValue())) {
            role = new DentalFrontdeskRole();
        }else if (roleName.equals(Role.RoleType.DentalManager.getValue())) {
            role = new DentalManagerRole();
        }else if (roleName.equals(Role.RoleType.InsuranceRepresentative.getValue())) {
            role = new InsuranceRepresentativeRole();
        }else if (roleName.equals(Role.RoleType.InsurancePolicyManager.getValue())) {
            role = new InsurancePolicyManagerRole();
        }else if (roleName.equals(Role.RoleType.InsuranceFinanceManager.getValue())) {
            role = new InsuranceFinanceManagerRole();
        }

        return role;
    }
}
