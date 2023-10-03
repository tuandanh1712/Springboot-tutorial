package com.tutorial.apidemo.database;

import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.respositories.ProductRespository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=tuanvattu123 \
-e MYSQL_USER=daodanhtuan1712 \
-e MYSQL_PASSWORD=tuanvattu123 \
-e MYSQL_DATABASE=test_db \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u hoangnd -p

* */
@Configuration
public class Database {
  private  static Logger logger= LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRespository productRespository){
        return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product product1= new Product("Xs",20,220.5,"");
                Product product2= new Product("Iphone 11",20,230.5,"");
                logger.info(("insert data:"+productRespository.save(product1)));
                logger.info(("insert data:"+productRespository.save(product2)));
            }
        };
    }
}
