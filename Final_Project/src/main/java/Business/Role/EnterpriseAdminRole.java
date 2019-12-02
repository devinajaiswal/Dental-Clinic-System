/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import userinterface.EnterpriseAdminRole.AdminWorkAreaJPanel;
import javax.swing.JPanel;
import userinterface.EnterpriseAdminRole.EnterpriseAdminWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class EnterpriseAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        return new EnterpriseAdminWorkAreaJPanel(userProcessContainer, account, organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.EnterpriseAdmin;
    }

    
    
}
