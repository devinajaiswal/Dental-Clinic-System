/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.FinanceManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author devinajaiswal
 */
public class FinanceManagerOrganization extends Organization {
    public FinanceManagerOrganization() {
        super(Organization.Type.InsuranceFinanceManager.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new FinanceManagerRole());
        return roles;
    }
}
