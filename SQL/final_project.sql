CREATE TABLE Final_Project.`User` (
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`username`)
);
insert into Final_Project.`User` values ('sysadmin', 'sysadmin');



CREATE TABLE Final_Project.`Network` (
    `network_id` INT NOT NULL,
    `network_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`network_id`)
);

CREATE TABLE Final_Project.`EnterpriseType` (
    `enterprise_type` VARCHAR(50) NOT NULL UNIQUE
);
insert into Final_Project.`EnterpriseType` values ('DentalClinic'), ('Insurance');

CREATE TABLE Final_Project.`Enterprise` (
    `enterprise_id` INT NOT NULL,
    `enterprise_name` VARCHAR(50) NOT NULL,
    `enterprise_type` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`enterprise_id`),
    FOREIGN KEY (enterprise_type)
        REFERENCES EnterpriseType (enterprise_type)
);

CREATE TABLE Final_Project.`NetworkEnterprise` (
    `network_id` INT NOT NULL,
    `enterprise_id` INT NOT NULL,
    PRIMARY KEY (`network_id` , `enterprise_id`),
    FOREIGN KEY (enterprise_id)
        REFERENCES Enterprise (enterprise_id),
    FOREIGN KEY (network_id)
        REFERENCES Network (network_id)
);	


CREATE TABLE Final_Project.`Roles` (
    `username` VARCHAR(50) NOT NULL,
    `role_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`username`),
    FOREIGN KEY (username)
        REFERENCES User (username)
);
insert into Final_Project.roles values ('sysadmin', 'SysAdmin');

CREATE TABLE Final_Project.`Employee` (
    `employee_id` INT NOT NULL,
    `employee_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`employee_id`)
);

CREATE TABLE Final_Project.`Employee_User` (
    `employee_id` INT NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`employee_id` , `username`),
    FOREIGN KEY (`employee_id`)
        REFERENCES `Employee` (`employee_id`),
    FOREIGN KEY (`username`)
        REFERENCES `User` (`username`)
);


-- added
CREATE TABLE Final_Project.EnterpriseOrganization (
    organization_id INT NOT NULL,
    organization_type VARCHAR(50) NOT NULL,
    organization_name VARCHAR(50) NOT NULL,
    enterprise_id INT NOT NULL,
    PRIMARY KEY (organization_id),
    CONSTRAINT FOREIGN KEY (enterprise_id)
        REFERENCES enterprise (enterprise_id),
    UNIQUE (enterprise_id , organization_name)
);



CREATE TABLE Final_Project.OrganizationType (
    enterprise_type VARCHAR(50) NOT NULL,
    organization_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (enterprise_type , organization_type)
);

CREATE TABLE Final_Project.RoleType (
    role_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_type)
);
insert into  Final_Project.RoleType values ('SysAdmin'), ('Customer'), ('EnterpriseAdmin'),
('DentalFrontdesk'), ('DentalDentist'), ('DentalManager'), ('InsuranceRepresentative'), 
('InsurancePolicyManager'), ('InsuranceFinanceManager');

alter table Final_Project.Roles 
add constraint foreign key (role_name) references Final_Project.RoleType (role_type);

CREATE TABLE Final_Project.EnterpriseUser (
    enterprise_id INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    PRIMARY KEY (enterprise_id , username),
    CONSTRAINT FOREIGN KEY (enterprise_id)
        REFERENCES enterprise (enterprise_id),
    CONSTRAINT FOREIGN KEY (username)
        REFERENCES User (username)
);
use Final_project;
ALTER TABLE `Final_project`.`Roles` 
RENAME TO  `Final_project`.`Role_User` ;
ALTER TABLE `Final_project`.`EnterpriseOrganization` 
RENAME TO  `Final_project`.`Organization` ;
ALTER TABLE `Final_project`.`EnterpriseUser` 
RENAME TO  `Final_project`.`Enterprise_User`;
ALTER TABLE `Final_project`.`NetworkEnterprise` 
RENAME TO  `Final_project`.`Network_Enterprise`;

ALTER TABLE Employee MODIFY email VARCHAR(50);

CREATE TABLE Final_Project.`Organization_User` (
    `organization_id` INT NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`username` , `organization_id`),
    FOREIGN KEY (username)
        REFERENCES User (username),
    FOREIGN KEY (organization_id)
        REFERENCES Organization (organization_id)
);

CREATE TABLE Final_Project.States (
    `state_name` VARCHAR(50) NOT NULL UNIQUE
);
insert into Final_Project.States values ('Alabama'),
('Alaska'),
('Arizona'),
('Arkansas'),
('California'),
('Colorado'),
('Connecticut'),
('Delaware'),
('District of Columbia'),
('Florida'),
('Georgia'),
('Hawaii'),
('Idaho'),
('Illinois'),
('Indiana'),
('Iowa'),
('Kansas'),
('Kentucky'),
('Louisiana'),
('Maine'),
('Maryland'),
('Massachusetts'),
('Michigan'),
('Minnesota'),
('Mississippi'),
('Missouri'),
('Montana'),
('Nebraska'),
('Nevada'),
('New Hampshire'),
('New Jersey'),
('New Mexico'),
('New York'),
('North Carolina'),
('North Dakota'),
('Ohio'),
('Oklahoma'),
('Oregon'),
('Pennsylvania'),
('Puerto Rico'),
('Rhode Island'),
('South Carolina'),
('South Dakota'),
('Tennessee'),
('Texas'),
('Utah'),
('Vermont'),
('Virginia'),
('Washington'),
('West Virginia'),
('Wisconsin'),
('Wyoming');

alter table Final_Project.Network 
add constraint foreign key (network_name) references Final_Project.States (state_name);
alter table Final_Project.Network 
MODIFY network_name VARCHAR(50) NOT NULL unique;


CREATE TABLE Final_Project.`User_PersonalInfo` (
    `username` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `ssn` VARCHAR(50) NOT NULL,
    `street` VARCHAR(50) NOT NULL,
    `city` VARCHAR(50) NOT NULL,
    `state` VARCHAR(50) NOT NULL,
    `postcode` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`username`),
    FOREIGN KEY (username)
        REFERENCES User (username),
    FOREIGN KEY (state)
        REFERENCES States (state_name)
);

CREATE TABLE Final_Project.`User_Verficationcodes` (
    `username` VARCHAR(50) NOT NULL,
    `phone_code` VARCHAR(50) NULL,
    `email_code` VARCHAR(50) NULL,
    PRIMARY KEY (`username`),
    FOREIGN KEY (username)
        REFERENCES User (username)
);

--- add 2
CREATE TABLE Final_Project.`TreatmentType` (
  `treatment_type` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`treatment_type`)
);
insert into Final_Project.TreatmentType values ('Filling'),
('Root Canal'), ('SRP');

CREATE TABLE Final_Project.`TreatmentPrice` (
    `enterprise_id` INT NOT NULL,
    `filling_price` double NOT NULL,
    `rootcanal_price` double NOT NULL,
    `srp_price` double NOT NULL,
    PRIMARY KEY (`enterprise_id`),
    FOREIGN KEY (enterprise_id)
        REFERENCES Enterprise (enterprise_id)
);

CREATE TABLE Final_Project.`InsurancePlan` (
    `plan_id` INT NOT NULL,
    `plan_name` VARCHAR(50) NOT NULL,
    `price` DOUBLE NOT NULL,
    `enterprise_id` INT NOT NULL,
    `filling_coverage` double not null,
    `root_coverage` double NOT NULL,
    `srp_coverage` double NOT NULL,
    PRIMARY KEY (`plan_id`),
    FOREIGN KEY (enterprise_id)
        REFERENCES Enterprise (enterprise_id)
);


CREATE TABLE Final_Project.`PlanCoverage` (
    `plan_id` INT NOT NULL,
    `treatment_type` VARCHAR(50) NOT NULL,
    `coverage` DOUBLE NOT NULL,
    PRIMARY KEY (`plan_id` , `treatment_type`),
    FOREIGN KEY (plan_id)
        REFERENCES InsurancePlan (plan_id),
    FOREIGN KEY (treatment_type)
        REFERENCES TreatmentType (treatment_type)
);

CREATE TABLE Final_Project.`WorkRequest` (
    `request_id` INT NOT NULL,
    `message` VARCHAR(500) NULL,
    `sender_username` VARCHAR(50) NOT NULL,
    `receiver_username` VARCHAR(50) NULL,
    `receiver_org_id` INT NULL,
    `request_time` DATETIME NOT NULL,
    `finish_time` DATETIME NULL,
    PRIMARY KEY (request_id),
    FOREIGN KEY (sender_username)
        REFERENCES User (username),
    FOREIGN KEY (receiver_username)
        REFERENCES User (username),
    FOREIGN KEY (receiver_org_id)
        REFERENCES Organization (organization_id)
);

CREATE TABLE Final_Project.`Enterprise_Rate` (
    `username` VARCHAR(50) NOT NULL,
    `enterprise_id` INT NOT NULL,
    `rate` INT NOT NULL,
    `comment` VARCHAR(500) NOT NULL,
    PRIMARY KEY (username , enterprise_id),
    FOREIGN KEY (username)
        REFERENCES User (username),
    FOREIGN KEY (enterprise_id)
        REFERENCES Enterprise (enterprise_id)
);

CREATE TABLE Final_Project.`Enterprise_Address` (
    `enterprise_id` INT NOT NULL,
    `street` VARCHAR(50) NOT NULL,
    `city` VARCHAR(50) NOT NULL,
    `state` VARCHAR(50) NOT NULL,
    `postcode` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`enterprise_id`),
    FOREIGN KEY (enterprise_id)
        REFERENCES Enterprise (enterprise_id),
    FOREIGN KEY (state)
        REFERENCES States (state_name)
);

CREATE TABLE Final_Project.`Message` (
    `message_id` INT NOT NULL,
    `request_id` INT NOT NULL,
    `from_username` VARCHAR(50) NOT NULL,
    `to_username` VARCHAR(50) NULL,
    `message` VARCHAR(500) NOT NULL,
    `sent_time` DATETIME NOT NULL,
    PRIMARY KEY (`message_id`),
    FOREIGN KEY (request_id)
        REFERENCES WorkRequest (request_id),
    FOREIGN KEY (from_username)
        REFERENCES User (username),
    FOREIGN KEY (to_username)
        REFERENCES User (username)
);

create table DentalTreatmentPlans(
  plan_id int not null,
  plan_name varchar(50) not null,
   `price` DOUBLE NOT NULL,
    `enterprise_id` INT NOT NULL,
    treatmentType varchar(50) not null,
    coverage DOUBLE NOT NULL
);

-- add 3

CREATE TABLE Final_Project.`User_MedicalInfo` (
    `username` VARCHAR(50) NOT NULL,
    `gender` VARCHAR(5) NOT NULL,
    `dob` DATE NOT NULL,
    `smoking` VARCHAR(5) NOT NULL,
    `sweet` VARCHAR(5) NOT NULL,
    `diabetes` VARCHAR(5) NOT NULL,
    `cardio` VARCHAR(5) NOT NULL,
    `immune` VARCHAR(5) NOT NULL,
    PRIMARY KEY (`username`),
    FOREIGN KEY (username)
        REFERENCES User (username)
);

CREATE TABLE Final_Project.`Appointment` (
    `appointment_id` INT NOT NULL,
    `request_id` INT NOT NULL,
    `appointment_time` TIMESTAMP NOT NULL,
    PRIMARY KEY (`appointment_id`),
    FOREIGN KEY (request_id)
        REFERENCES WorkRequest (request_id)
);

alter table User_MedicalInfo modify column dob timestamp NOT NULL;

CREATE TABLE Final_Project.`Treatment` (
    `treatment_id` INT NOT NULL,
    `request_id` INT NOT NULL,
    `patient_username` VARCHAR(50) NULL,
    `hygiene_score` INT NULL,
    `type` VARCHAR(50) NULL,
    `note` VARCHAR(500) NULL,
    PRIMARY KEY (`treatment_id`),
    FOREIGN KEY (patient_username)
        REFERENCES User (username),
    FOREIGN KEY (request_id)
        REFERENCES WorkRequest (request_id)
);



CREATE TABLE Final_Project.`Policy` (
	`policy_id` INT NOT NULL,
    `request_id` INT NOT NULL,
	`plan_id` INT NOT NULL,
    `username` varchar(50) NOT NULL,
    `start_date` TIMESTAMP NOT NULL,
    `premium` double NULL,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`policy_id`),
    FOREIGN KEY (request_id)
        REFERENCES WorkRequest (request_id),
    FOREIGN KEY (plan_id)
        REFERENCES InsurancePlan (plan_id),
    FOREIGN KEY (username)
        REFERENCES User (username)
);

alter table Appointment add column status varchar(50);

CREATE TABLE Final_Project.`Payment` (
	`payment_id` INT NOT NULL,
    `request_id` INT NOT NULL,
	`treatment_id` INT NULL,
    `policy_id` INT NULL,
    `amount` double NULL,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`payment_id`),
    FOREIGN KEY (treatment_id)
        REFERENCES Treatment (treatment_id),
    FOREIGN KEY (policy_id)
        REFERENCES Policy (policy_id)
);
