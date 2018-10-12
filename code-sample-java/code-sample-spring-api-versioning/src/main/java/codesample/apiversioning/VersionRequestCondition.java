package codesample.apiversioning;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {

    private String version;

    public VersionRequestCondition(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }


    @Override
    public VersionRequestCondition combine(VersionRequestCondition other) {
        return null;
    }

    @Override /* This method will try to check THIS condition suffice for incoming http request */
    public VersionRequestCondition getMatchingCondition(HttpServletRequest other) {
        String otherVersion = other.getHeader("version");
        if (!StringUtils.isEmpty(otherVersion) && otherVersion.equals(version)) {
            return this;
        } else
            return null;
    }

    @Override
    public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
        String otherVer = other.getVersion();
        String currentVer = request.getHeader("version");

        if (currentVer.equals("1.7") && otherVer.equals("1.6"))
            return 1;
        else if (currentVer.equals("1.6") && otherVer.equals("1.7"))
            return -1;
        else
            return 0;
    }
}
