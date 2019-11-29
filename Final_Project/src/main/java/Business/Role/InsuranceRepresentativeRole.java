/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.CustomerRepresentativeOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.CustomerRepresentativeRole.CustomerRepresentativeAreaJPanel;

/**
 
 * @author devinajaiswal
 */
public class InsuranceRepresentativeRole extends Role{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        return new CustomerRepresentativeAreaJPanel(userProcessContainer, account, (CustomerRepresentativeOrganization)organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.InsuranceRepresentative;
    }
}
