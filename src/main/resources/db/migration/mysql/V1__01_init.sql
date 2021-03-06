create table visitor(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name text,
    last_name text,
    phone text,
    reason_for_visit text,
    contact_person text,
    license_plate text,
    badge_number int not null,
    checkin_time datetime default current_timestamp,
    checkout_time datetime);

create table employee(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name text,
    last_name text,
    department text,
    phone text,
    badge_number integer not null,
    checkin_time datetime default current_timestamp,
    checkout_time datetime);

create table supplier(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name text,
    last_name text,
    phone text,
    company text,
    contact_person text,
    license_plate text,
    badge_number int not null,
    checkin_time datetime default current_timestamp,
    checkout_time datetime);
