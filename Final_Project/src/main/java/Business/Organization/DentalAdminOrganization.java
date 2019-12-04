/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.EnterpriseAdminRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class DentalAdminOrganization extends Organization{

    public DentalAdminOrganization() {
        super(Type.DentalAdmin);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new EnterpriseAdminRole());
        return roles;
    }
     
}
