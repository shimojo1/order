CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'ユーザID',
  email varchar(255) NOT NULL COMMENT 'メールアドレス',
  password varchar(255) NOT NULL COMMENT 'パスワード',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '作成日時',
  modified_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日時',
  PRIMARY KEY (id),
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ユーザ';