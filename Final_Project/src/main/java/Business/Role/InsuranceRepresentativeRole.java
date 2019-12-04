/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.Enterprise.Enterprise;
import Business.Organization.InsuranceRepresentativeOrganization;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import javax.swing.JPanel;
import userinterface.InsuranceRepresentativeRole.InsuranceRepresentativeWorkAreaJPanel;

/**
 
 * @author devinajaiswal
 */
public class InsuranceRepresentativeRole extends Role{
    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise) {
        return new InsuranceRepresentativeWorkAreaJPanel(userProcessContainer, account, (InsuranceRepresentativeOrganization)organization, enterprise);
    }

    @Override
    public RoleType getRoleType() {
        return Role.RoleType.InsuranceRepresentative;
    }
}
