/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.DentalDentistOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import userinterface.DoctorRole.DoctorWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class DentalFrontdeskRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        // TODO create dental front desk page
        return new DoctorWorkAreaJPanel(userProcessContainer, account, (DentalDentistOrganization)organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.DentalFrontdesk;
    }
    
    
}
