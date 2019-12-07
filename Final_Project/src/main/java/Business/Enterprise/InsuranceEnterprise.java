package Business.Enterprise;

import Business.Organization.DentalAdminOrganization;
import Business.Organization.DentalDentistOrganization;
import Business.Organization.DentalFrontDeskOrganization;
import Business.Organization.DentalManagerOrganization;
import Business.Organization.InsuranceAdminOrganization;
import Business.Organization.InsuranceFinanceManagerOrganization;
import Business.Organization.InsurancePolicyManagerOrganization;
import Business.Organization.InsuranceRepresentativeOrganization;
import Business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author devinajaiswal
 */
public class InsuranceEnterprise extends Enterprise {

    public InsuranceEnterprise(String name) {
        super(name, EnterpriseType.INSURACE);
    }

    @Override
    public ArrayList<Organization> getSupportedOrganizations() {
        ArrayList<Organization> list = new ArrayList<>();
        list.add(new InsuranceRepresentativeOrganization());
        list.add(new InsuranceFinanceManagerOrganization());
        list.add(new InsurancePolicyManagerOrganization());
        list.add(new InsuranceAdminOrganization());
        return list;
    }

}
