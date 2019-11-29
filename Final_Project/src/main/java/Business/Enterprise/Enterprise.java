/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;

/**
 *
 * @author MyPC1
 */
public abstract class Enterprise {

    private EnterpriseType enterpriseType;
    private int enterpriseId;
    private String enterpriseName;
    private Network network;
    private OrganizationDirectory organizationDirectory;

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public enum EnterpriseType {
        DENTAL_CLINIC("DentalClinic"),
        INSURACE("Insurance");

        private String value;

        private EnterpriseType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public EnterpriseType getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(EnterpriseType enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Enterprise(String name, EnterpriseType type) {
        this.enterpriseName = name;
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
    }

    public static Enterprise createEnterprise(String name, String type) {
        Enterprise enterprise = null;
        if (type == EnterpriseType.DENTAL_CLINIC.getValue()) {
            enterprise = new DentalClinicEnterprise(name);
        } else if (type == EnterpriseType.INSURACE.getValue()) {
            enterprise = new InsuranceEnterprise(name);
        }
        return enterprise;
    }

    @Override
    public String toString() {
        return this.enterpriseName;
    }
}
