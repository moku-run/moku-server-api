```sql
`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '식별 인덱스',

    `created_at`  DATETIME	    NOT NULL  DEFAULT CURRENT_TIMESTAMP	COMMENT '데이터가 생성된 시간',
	`updated_at`  DATETIME	    NOT NULL  DEFAULT CURRENT_TIMESTAMP	COMMENT '데이터가 수정된 시간',
	`created_by`  BIGINT  UNSIGNED  NOT NULL  COMMENT '데이터를 생성한 유저 아이디',
	`updated_by`  BIGINT  UNSIGNED  NOT NULL  COMMENT '데이터를 수정한 유저 아이디',
	`is_deleted`  TINYINT(1)	NOT NULL  DEFAULT 0	COMMENT '소프트 삭제 여부 (`0`: 정상, `1`: 삭제)',
	`deleted_at`  DATETIME	    NULL	  COMMENT '소프트 삭제된 시간'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```