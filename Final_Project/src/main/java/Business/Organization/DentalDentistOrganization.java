/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DentalDentistRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class DentalDentistOrganization extends Organization{

    public DentalDentistOrganization() {
        super(Organization.Type.DentalDentist);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new DentalDentistRole());
        return roles;
    }
     
}