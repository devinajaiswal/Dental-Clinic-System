/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.DentalDentistOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.CustomerRole.CustomerWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class CustomerRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        return new CustomerWorkAreaJPanel(userProcessContainer, account, organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.Customer;
    }
    
    
}
