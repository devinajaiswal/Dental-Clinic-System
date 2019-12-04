/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Organization.DentalAdminOrganization;
import Business.Organization.DentalDentistOrganization;
import Business.Organization.DentalFrontDeskOrganization;
import Business.Organization.DentalManagerOrganization;
import Business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class DentalClinicEnterprise extends Enterprise {

    public DentalClinicEnterprise(String name) {
        super(name, EnterpriseType.DENTAL_CLINIC);
    }
//    @Override
//    public ArrayList<Role> getSupportedRole() {
//        return null;
//    }

    @Override
    public ArrayList<Organization> getSupportedOrganizations() {
        ArrayList<Organization> list = new ArrayList<>();
        list.add(new DentalAdminOrganization());
        list.add(new DentalDentistOrganization());
        list.add(new DentalFrontDeskOrganization());
        list.add(new DentalManagerOrganization());
        return list;
    }

}
