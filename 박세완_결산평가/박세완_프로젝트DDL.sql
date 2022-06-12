DROP TABLE project;
DROP TABLE task;
DROP TABLE comments;
DROP TABLE tag;
DROP TABLE milestone;
DROP TABLE members;

CREATE TABLE `project` (
	`project_no`	INT	NOT NULL,
    `project_admin`	VARCHAR(20)	NOT NULL,
	`project_title`	VARCHAR(20)	NOT NULL,
    `project_content` VARCHAR(100) NOT NULL,
	`project_state`	VARCHAR(2)	NOT NULL
);

CREATE TABLE `task` (
	`task_no`	INT	NOT NULL,
	`task_project_no`	INT	NOT NULL,
    `task_admin`	VARCHAR(20)	NOT NULL,
	`task_title`	VARCHAR(20)	NOT NULL,
	`task_content`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `comments` (
	`comment_no`	INT	NOT NULL,
	`comment_task_no`	INT	NOT NULL,
    `comment_admin`	VARCHAR(20)	NOT NULL,
	`comment_content`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `tag` (
	`tag_no`	INT	NOT NULL,
	`tag_project_no`	INT	NOT NULL,
	`tag_content`	VARCHAR(10)	NOT NULL,
    `tag_admin`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `milestone` (
	`mile_no`	INT	NOT NULL,
	`mile_project_no`	INT	NOT NULL,
    `mile_content`	VARCHAR(50)	NOT NULL,
    `mile_admin` VARCHAR(20) NOT NULL
);

CREATE TABLE `task_tag` (
	`tag_no`	INT	NOT NULL,
	`task_no`	INT	NOT NULL
);

CREATE TABLE `task_milestone` (
	`mile_no`	INT	NOT NULL,
	`task_no`	INT	NOT NULL,
    `mile_start`	DATE NOT NULL,
    `mile_end`	DATE NOT NULL
);

CREATE TABLE `members` (
	`member_id`	VARCHAR(20) NOT NULL,
    `project_no`	INT NOT NULL
);

ALTER TABLE `project` ADD CONSTRAINT `PK_PROJECT` PRIMARY KEY (
	`project_no`
);

ALTER TABLE `task` ADD CONSTRAINT `PK_TASK` PRIMARY KEY (
	`task_no`
);

ALTER TABLE `comments` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`comment_no`,
	`comment_task_no`
);

ALTER TABLE `tag` ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
	`tag_no`
);

ALTER TABLE `milestone` ADD CONSTRAINT `PK_MILESTONE` PRIMARY KEY (
	`mile_no`
);

ALTER TABLE `members` ADD CONSTRAINT `PK_MEMBERS` PRIMARY KEY (
	`member_id`,
    `project_no`
);

ALTER TABLE `task_tag` ADD CONSTRAINT `PK_TASK_TAG` PRIMARY KEY (
	`tag_no`,
    `task_no`
);

ALTER TABLE `task_milestone` ADD CONSTRAINT `PK_TASK_MILESTONE` PRIMARY KEY (
	`mile_no`,
    `task_no`
);

ALTER TABLE `project` MODIFY `project_no` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE `task` MODIFY `task_no` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE `comments` MODIFY `comment_no` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE `tag` MODIFY `tag_no` INT NOT NULL AUTO_INCREMENT;
ALTER TABLE `milestone` MODIFY `mile_no` INT NOT NULL AUTO_INCREMENT;

commit;