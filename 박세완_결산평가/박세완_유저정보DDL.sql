DROP TABLE `users`;

CREATE TABLE `users` (
	`user_no`	INT	NOT NULL,
	`user_id`	VARCHAR(20)	NOT NULL,
	`user_pw`	VARCHAR(300)	NOT NULL,
	`user_email`	VARCHAR(50)	NOT NULL,
	`user_state`	VARCHAR(2)	NOT NULL
);

ALTER TABLE `users` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_no`
);

ALTER TABLE `users` MODIFY `user_no` INT NOT NULL AUTO_INCREMENT;

