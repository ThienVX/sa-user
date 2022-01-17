package thienvx.sauser;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

// @Configuration
public class DataSourceConfig {

//     @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
