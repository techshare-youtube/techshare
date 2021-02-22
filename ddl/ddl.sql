CREATE TABLE `USERS` (
  `ID` bigint(9) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '�V�X�e���Ŏ����̔Ԃ���郆�[�U�[ID',
  `EMAIL` varchar(128) NOT NULL COMMENT '�d���[���A�h���X',
  `AVF` date NOT NULL COMMENT '����Ǘ��p�̓��t',
  `NAME` varchar(128) DEFAULT NULL COMMENT '���[�U�[���ɕ\������郆�[�U�[����ێ�����B',
  `PASSWORD` char(60) DEFAULT NULL,
  `LOCKED` tinyint(1) unsigned DEFAULT '0' COMMENT '�A�J�E���g�����b�N����Ă��邱�Ƃ������t���O',
  `EXPIRED` tinyint(1) unsigned DEFAULT '0' COMMENT '�A�J�E���g�������؂�ɂȂ������Ƃ������t���O',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`,`AVF`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8

CREATE TABLE `USER_ROLES` (
  `USER_ID` bigint(9) unsigned zerofill NOT NULL COMMENT '���[�U�[�}�X�^�̃��[�U�[ID���Q��',
  `ROLE` char(2) NOT NULL,
  `DELFLG` char(1) DEFAULT NULL COMMENT '���[�U�[�����������ꂽ�ꍇ�ɐݒ肳���폜�t���O',
  PRIMARY KEY (`USER_ID`,`ROLE`),
  CONSTRAINT `USER_ROLES_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `CODEMST` (
  `CODE_ID` bigint(8) unsigned zerofill NOT NULL COMMENT '�R�[�h�̌n����ӂɂ����ׂ��邽�߂�ID',
  `CODE` varchar(8) NOT NULL,
  `CODE_NM` varchar(256) DEFAULT NULL,
  `DELFLG` char(1) DEFAULT NULL COMMENT '���[�U�[�����������ꂽ�ꍇ�ɐݒ肳���폜�t���O',
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