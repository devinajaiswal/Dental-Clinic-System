/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.PolicyManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author devinajaiswal
 */
public class PolicyManagerOrganization extends Organization {
    public PolicyManagerOrganization() {
        super(Organization.Type.PolicyManagerOrganization.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new PolicyManagerRole());
        return roles;
    }
}
