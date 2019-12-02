
drop table Final_Project.`User`;
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

drop table Final_Project.`Employee`;
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

CREATE TABLE Final_Project.`User_VerficationCodes` (
  `username` varchar(50) NOT NULL,
  `phone_code` varchar(50) NOT NULL,
  `email_code` varchar(50) NOT NULL
);

CREATE TABLE Final_Project.`User_PersonalInfo` (
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `ssn` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `postcode` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  foreign key (`state`) references `States`(`state_name`),
  foreign key (`username`) references `User` (`username`)
);

