ALTER TABLE `user` ADD `email` varchar(20) AFTER `username`;
ALTER TABLE user ADD CONSTRAINT UC_user_email UNIQUE (email);