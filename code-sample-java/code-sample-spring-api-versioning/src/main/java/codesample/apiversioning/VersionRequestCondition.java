package codesample.apiversioning;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class VersionRequestCondition implements RequestCondition<VersionRequestCondition> {

    private String version;

    public String getVersion() {
        return version;
    }

    public VersionRequestCondition setVersionAndReturn(String version) {
        this.version = version;
        return this;
    }

    public VersionRequestCondition(String version) {
        this.version = version;
    }

    @Override
    public VersionRequestCondition combine(VersionRequestCondition other) {
        return null;
    }

    @Override
    public VersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        String version = httpServletRequest.getHeader("version");
        if (!StringUtils.isEmpty(version)) {
            if (version.equals("1.6"))
                return this;
            else if (version.equals("1.7"))
                return this.setVersionAndReturn("1.7");
        }

        return null;
    }

    @Override
    public int compareTo(VersionRequestCondition other, HttpServletRequest request) {
        String otherVer = other.getVersion();
        String currentVer = request.getHeader("version");

        if (otherVer.equals("1.6") && currentVer.equals("1.7"))
            return 1;
        else if (otherVer.equals("1.7") && currentVer.equals("1.6"))
            return -1;
        else
            return 0;
    }
}
