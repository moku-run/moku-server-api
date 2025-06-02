CREATE TABLE `mst_users` (
    `id`	      BIGINT  UNSIGNED  NOT NULL  AUTO_INCREMENT  PRIMARY KEY  COMMENT '식별 인덱스',
	`login_id`	  VARCHAR(50)	    NOT NULL  UNIQUE KEY                   COMMENT '사용자 로그인 아이디 (UK)',
	`nickname`	  VARCHAR(50)	    NOT NULL  UNIQUE KEY                   COMMENT '사용자 닉네임 (UK)',
    `password`	  VARCHAR(100)	    NOT NULL	                           COMMENT '사용자 로그인 비밀번호',
	`role`        VARCHAR(10)	    NOT NULL	                           COMMENT '사용자 권한',
    `is_deleted`  BOOLEAN   	    NOT NULL  DEFAULT 0	                   COMMENT '논리 삭제 여부 (\'0\': 정상, \'1\': 삭제)',
    `deleted_at`  DATETIME	        NULL	                               COMMENT '논리 삭제 시간',
    `created_by`  BIGINT	        NOT NULL  DEFAULT 0                    COMMENT '데이터를 생성한 유저 아이디',
    `updated_by`  BIGINT	        NOT NULL  DEFAULT 0                    COMMENT '데이터를 수정한 유저 아이디',
    `created_at`  DATETIME	        NOT NULL  DEFAULT CURRENT_TIMESTAMP	   COMMENT '데이터가 생성된 시간',
    `updated_at`  DATETIME	        NOT NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '데이터가 수정된 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;