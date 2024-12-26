CREATE TABLE IF NOT EXISTS players(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
codename VARCHAR(255) NOT NULL,
telephone VARCHAR(255),
group_name VARCHAR(255) NOT NULL,
CONSTRAINT uq_codename_group_name UNIQUE (codename, group_name)
);