package thienvx.sauser.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import thienvx.sauser.repositories.PingRepository;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PingController {

    @Autowired
    private PingRepository pingRepository;

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        Map<String, Object> results = new LinkedHashMap<>();

        Charset defaultCharset = Charset.defaultCharset();
        boolean canConnectToDB = pingRepository.canConnectToDB();

        boolean isAllOK = canConnectToDB
                && defaultCharset.equals(StandardCharsets.UTF_8);

        results.put("allOK", isAllOK);
        results.put("canConnectToDB", canConnectToDB);
        results.put("defaultCharset", defaultCharset.displayName());

        return results;
    }
}
