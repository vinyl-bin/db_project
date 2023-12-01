package db_project.db_project;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbProjectApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}


}
