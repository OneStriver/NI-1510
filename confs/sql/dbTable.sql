CREATE DATABASE IF NOT EXISTS northInterface char set utf8;
use northInterface;
create table if not exists alarmCorrespond(
  `alarm_code` int(11) NOT NULL,
  `alarm_type` varchar(32) DEFAULT NULL,
  `specific_problem` varchar(32) DEFAULT NULL,
  `alarm_des` varchar(32) DEFAULT NULL,
  `probable_cause` varchar(32) DEFAULT NULL,
  `handle_propose` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table if not exists deviceStatusInformation(
  `deviceType` varchar(32) NOT NULL,
  `deviceIndex` varchar(32) DEFAULT NULL,
  `deviceStatus` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;