package run.moku.framework.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueryDslConfig {

    @Bean
    fun queryFactory(em: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}