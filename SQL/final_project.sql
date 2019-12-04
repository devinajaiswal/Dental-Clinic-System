CREATE TABLE Final_Project.`User` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ;
insert into Final_Project.`User` values ('sysadmin', 'sysadmin');



CREATE TABLE Final_Project.`Network` (
  `network_id` int NOT NULL,
  `network_name` varchar(50) NOT NULL,
  PRIMARY KEY (`network_id`)
);

CREATE TABLE Final_Project.`EnterpriseType` (
  `enterprise_type` varchar(50) NOT NULL unique
);
insert into Final_Project.`EnterpriseType` values ('DentalClinic'), ('Insurance');

CREATE TABLE Final_Project.`Enterprise` (
  `enterprise_id` int NOT NULL,
  `enterprise_name` varchar(50) NOT NULL,
  `enterprise_type` varchar(50) NOT NULL,
  PRIMARY KEY (`enterprise_id`),
  foreign key (enterprise_type) references EnterpriseType (enterprise_type)
);

CREATE TABLE Final_Project.`NetworkEnterprise` (
  `network_id` int not NULL,
  `enterprise_id` int NOT NULL,
  PRIMARY KEY (`network_id`, `enterprise_id`),
  foreign key (enterprise_id) references Enterprise (enterprise_id),
  foreign key (network_id) references Network (network_id)
);	


CREATE TABLE Final_Project.`Roles` (
  `username` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  foreign key (username) references User (username)
);
insert into Final_Project.roles values ('sysadmin', 'SysAdmin');

CREATE TABLE Final_Project.`Employee` (
  `employee_id` int NOT NULL,
  `employee_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`employee_id`)
);

CREATE TABLE Final_Project.`Employee_User` (
  `employee_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`employee_id`, `username`),
  foreign key (`employee_id`) references `Employee`(`employee_id`),
  foreign key (`username`) references `User` (`username`)
);


-- added
create table Final_Project.EnterpriseOrganization (
	organization_id int not null,
    organization_type varchar(50) not null,
    organization_name varchar(50) not null,
    enterprise_id int not null,
    PRIMARY KEY (organization_id),
    constraint foreign key (enterprise_id) references enterprise (enterprise_id),
    unique (enterprise_id, organization_name)
);



create table Final_Project.OrganizationType (
	enterprise_type varchar(50) not null,
    organization_type varchar(50) not null,
    primary key (enterprise_type, organization_type)
);

create table Final_Project.RoleType (
	role_type varchar(50) not null,
    primary key (role_type)
);
insert into  Final_Project.RoleType values ('SysAdmin'), ('Customer'), ('EnterpriseAdmin'),
('DentalFrontdesk'), ('DentalDentist'), ('DentalManager'), ('InsuranceRepresentative'), 
('InsurancePolicyManager'), ('InsuranceFinanceManager');

alter table Final_Project.Roles 
add constraint foreign key (role_name) references Final_Project.RoleType (role_type);

create table Final_Project.EnterpriseUser (
    enterprise_id int not null,
    username varchar(50) not null,
    PRIMARY KEY (enterprise_id, username),
    constraint foreign key (enterprise_id) references enterprise (enterprise_id),
    constraint foreign key (username) references User (username)
);

rename table Roles to Role_User;
rename table EnterpriseOrganization to Organization;
rename table EnterpriseUser to Enterprise_User;
rename table NetworkEnterprise to Network_Enterprise;
ALTER TABLE Employee MODIFY email VARCHAR(50);

CREATE TABLE Final_Project.`Organization_User` (
  `organization_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`username`,  `organization_id`),
  foreign key (username) references User (username),
  foreign key (organization_id) references Organization (organization_id)
);

CREATE TABLE Final_Project.States (
  `state_name` varchar(50) NOT NULL UNIQUE
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
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `ssn`varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `postcode` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  foreign key (username) references User (username),
  foreign key (state) references States (state_name)
);

CREATE TABLE Final_Project.`User_Verficationcodes` (
  `username` varchar(50) NOT NULL,
  `phone_code` varchar(50) NULL,
  `email_code` varchar(50) NULL,
  PRIMARY KEY (`username`),
  foreign key (username) references User (username)
);

CREATE TABLE Final_Project.`TreatmentType` (
  `treatment_type` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`treatment_type`)
);
insert into Final_Project.TreatmentType values ('Filling'),
('Root Canal'), ('SRP');

CREATE TABLE Final_Project.`TreatmentPrice` (
  `enterprise_id` int NOT NULL,
  `treatment_type` varchar(50) NOT NULL,
  `price` double,
  PRIMARY KEY (`enterprise_id`,`treatment_type`),
  foreign key (enterprise_id) references Enterprise (enterprise_id),
  foreign key (treatment_type) references TreatmentType (treatment_type)
);

CREATE TABLE Final_Project.`InsurancePlan` (
    `plan_id` int NOT NULL,
    `plan_name` varchar(50) NOT NULL,
    `price` double NOT NULL,
    `enterprise_id` int NOT NULL,
	PRIMARY KEY (`plan_id`),
	foreign key (enterprise_id) references Enterprise (enterprise_id)
);

CREATE TABLE Final_Project.`PlanCoverage` (
	`plan_id` int NOT NULL,
    `treatment_type` varchar(50) NOT NULL,
    `coverage` double NOT NULL,
    PRIMARY KEY (`plan_id`, `treatment_type`),
    foreign key (plan_id) references InsurancePlan (plan_id),
	foreign key (treatment_type) references TreatmentType (treatment_type)
);
