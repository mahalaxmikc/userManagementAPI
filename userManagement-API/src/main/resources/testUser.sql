create schema testUser;
drop user 'testUser'@'localhost';
create user 'testUser'@'localhost' identified by 'pass1234'; -- Create the user
grant all on testUser.* to 'testUser'@'localhost'; -- Gives all privileges to that user on new db