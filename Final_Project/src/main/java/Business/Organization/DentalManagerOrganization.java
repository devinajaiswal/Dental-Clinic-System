/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DentalDentistRole;
import Business.Role.DentalFrontdeskRole;
import Business.Role.DentalManagerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class DentalManagerOrganization extends Organization{

    public DentalManagerOrganization() {
        super(Organization.Type.DentalManager);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new DentalManagerRole());
        return roles;
    }
     
}