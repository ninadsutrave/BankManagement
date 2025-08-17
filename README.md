## Bank Management System


### Cool Stuff / Things Learnt

1. Console Password Input
2. Best practices for Utility classes (https://www.baeldung.com/java-helper-vs-utility-classes#java-utility-classes)
3. @UtilityClass lombok annotation

4. DB REGEX constraints for like phone number

CREATE TABLE customer (
id INT AUTO_INCREMENT PRIMARY KEY,
phone_number VARCHAR(20),
CONSTRAINT chk_phone CHECK (phone_number REGEXP '^[+]?[0-9]{8,15}$')
);

5. SQL Error States
