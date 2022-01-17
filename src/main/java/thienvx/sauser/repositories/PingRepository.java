package thienvx.sauser.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean canConnectToDB() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }
}
