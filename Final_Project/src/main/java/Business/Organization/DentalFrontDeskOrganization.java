/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.DentalDentistRole;
import Business.Role.DentalFrontdeskRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class DentalFrontDeskOrganization extends Organization{

    public DentalFrontDeskOrganization() {
        super(Organization.Type.DentalFrontDesk);
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new DentalFrontdeskRole());
        return roles;
    }
     
}