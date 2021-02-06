CREATE TABLE `USERS` (
  `ID` bigint(9) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'システムで自動採番されるユーザーID',
  `EMAIL` varchar(128) NOT NULL COMMENT 'Ｅメールアドレス',
  `AVF` date NOT NULL COMMENT '世代管理用の日付',
  `NAME` varchar(128) DEFAULT NULL COMMENT 'ユーザー情報に表示されるユーザー名を保持する。',
  `PASSWORD` char(60) DEFAULT NULL,
  `LOCKED` tinyint(1) unsigned DEFAULT '0' COMMENT 'アカウントがロックされていることを示すフラグ',
  `EXPIRED` tinyint(1) unsigned DEFAULT '0' COMMENT 'アカウントが期限切れになったことを示すフラグ',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`,`AVF`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8

CREATE TABLE `USER_ROLES` (
  `USER_ID` bigint(9) unsigned zerofill NOT NULL COMMENT 'ユーザーマスタのユーザーIDを参照',
  `ROLE` char(2) NOT NULL,
  `DELFLG` char(1) DEFAULT NULL COMMENT 'ユーザーが無効化された場合に設定される削除フラグ',
  PRIMARY KEY (`USER_ID`,`ROLE`),
  CONSTRAINT `USER_ROLES_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `CODEMST` (
  `CODE_ID` bigint(8) unsigned zerofill NOT NULL COMMENT 'コード体系を一意にしきべつするためのID',
  `CODE` varchar(8) NOT NULL,
  `CODE_NM` varchar(256) DEFAULT NULL,
  `DELFLG` char(1) DEFAULT NULL COMMENT 'ユーザーが無効化された場合に設定される削除フラグ',
  PRIMARY KEY (`CODE_ID`,`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `PERSONAL_INFO` (
  `USER_ID` bigint(9) unsigned zerofill NOT NULL,
  `LAST_NAME` varchar(32) DEFAULT NULL,
  `FIRST_NAME` varchar(32) DEFAULT NULL,
  `ZIPCODE` char(7) DEFAULT NULL,
  `PREF` varchar(16) DEFAULT NULL,
  `CITY` varchar(128) DEFAULT NULL,
  `BLDG` varchar(128) DEFAULT NULL,
  `TELNO` varchar(32) DEFAULT NULL,
  `MOBILE_NO` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `DEPTS` (
  `DEPT_CD` char(7) NOT NULL,
  `AVF` date NOT NULL,
  `DEPT_NM` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`DEPT_CD`,`AVF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `POSITIONS` (
  `POS_CD` char(4) NOT NULL,
  `AVF` date NOT NULL,
  `POS_NM` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`POS_CD`,`AVF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8