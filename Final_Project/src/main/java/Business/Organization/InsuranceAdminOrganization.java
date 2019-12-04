/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.EnterpriseAdminRole;
import Business.Role.InsuranceRepresentativeRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author devinajaiswal
 */
public class InsuranceAdminOrganization extends Organization {
    public InsuranceAdminOrganization() {
        super(Organization.Type.InsuranceAdmin);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new EnterpriseAdminRole());
        return roles;
    }
}
