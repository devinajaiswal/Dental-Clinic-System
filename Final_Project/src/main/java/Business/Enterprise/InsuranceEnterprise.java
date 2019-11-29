<<<<<<< HEAD
<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author MyPC1
 */
public class InsuranceEnterprise extends Enterprise {
    
    public InsuranceEnterprise(String name){
        super(name,EnterpriseType.INSURACE);
    }
//    @Override
//    public ArrayList<Role> getSupportedRole() {
//        return null;
//    }
    
}
=======
=======
>>>>>>> 69e29b63ee9e4421195ff5fb2209e64001682b91
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Enterprise;

import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author devinajaiswal
 */
public class InsuranceEnterprise extends Enterprise {
     public InsuranceEnterprise(String name){
        super(name,EnterpriseType.DENTAL_CLINIC);
    }
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
    
    
}
<<<<<<< HEAD
>>>>>>> 37b8d98e2b29c4df644a52b92c9beeac6ea9ab29
=======
>>>>>>> 69e29b63ee9e4421195ff5fb2209e64001682b91
