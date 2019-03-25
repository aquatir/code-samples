package codesample.springboot.reactive.server;

import com.google.common.io.BaseEncoding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SampleController {

    private Random rnd = new Random();

    @GetMapping("/value")
    public SampleDto getRandomValue() throws InterruptedException {
        Thread.sleep(rnd.nextInt(5) * 1000); // wait for random time up to 10 second to simulate long response time
        var sample = new SampleDto(rnd.nextLong(), generateRndString());
        System.out.println(sample);
        return sample;
    }

    private String generateRndString() {
        final byte[] buffer = new byte[8];
        this.rnd.nextBytes(buffer);
        return BaseEncoding.base64Url().omitPadding().encode(buffer); // or base32()
    }
}
