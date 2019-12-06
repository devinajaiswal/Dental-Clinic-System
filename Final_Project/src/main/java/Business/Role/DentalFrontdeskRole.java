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
import userinterface.DentalFrontdeskRole.DentalFrontdeskWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class DentalFrontdeskRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        return new DentalFrontdeskWorkAreaJPanel(userProcessContainer, account, organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.DentalFrontdesk;
    }
    
    
}
