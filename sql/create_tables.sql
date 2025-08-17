USE `BankManagement`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(128) DEFAULT NULL,
  `hashed_password` varchar(512) DEFAULT NULL,
  `type` enum('ADMIN','MANAGER','CUSTOMER') NOT NULL,
  `salt` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(512) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zipcode` int DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rec_updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `chk_branch_phone_number` CHECK (regexp_like(`phone_number`,_utf8mb4'^[0-9+-]{5,20}$'))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email_id` varchar(256) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rec_updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `fk_customer_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chk_email` CHECK (regexp_like(`email_id`,_utf8mb4'^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$')),
  CONSTRAINT `chk_phone_number` CHECK (regexp_like(`phone_number`,_utf8mb4'^[0-9+-]{5,20}$'))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `number` bigint NOT NULL,
  `customer_id` int NOT NULL,
  `hashed_pin` varchar(255) NOT NULL,
  `type` enum('SAVINGS','CURRENT','FIXED_DEPOSIT','RECURRING_DEPOSIT','SALARY','JOINT') NOT NULL,
  `branch_id` int NOT NULL,
  `balance` decimal(15,2) DEFAULT '0.00',
  `minimum_balance` decimal(15,2) DEFAULT '0.00',
  `status` enum('PENDING_APPROVAL','ACTIVE','DORMANT','SUSPENDED','CLOSED') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rec_updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`number`),
  KEY `fk_account_customer` (`customer_id`),
  KEY `fk_account_branch` (`branch_id`),
  CONSTRAINT `fk_account_branch` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_account_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_account_id` bigint NOT NULL,
  `target_account_id` bigint DEFAULT NULL,
  `type` enum('DEPOSIT','WITHDRAWAL','TRANSFER') NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `status` enum('PENDING','SUCCESS','FAILED','CANCELLED') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `rec_updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_source` (`source_account_id`),
  KEY `fk_transaction_target` (`target_account_id`),
  CONSTRAINT `fk_transaction_source` FOREIGN KEY (`source_account_id`) REFERENCES `account` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_transaction_target` FOREIGN KEY (`target_account_id`) REFERENCES `account` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
