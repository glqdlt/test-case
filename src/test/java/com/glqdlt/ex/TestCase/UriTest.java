package com.glqdlt.ex.TestCase;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.net.URISyntaxException;
import java.util.regex.Pattern;


/**
 * @author glqdlt
 */
public class UriTest {

    public static class UrlHelper {

        public static UrlHelper valueOf(String u) {
            int p = u.indexOf("://");
            String scheme = (u.substring(0, p));
            u = u.substring(p + 3);
            int p2 = u.indexOf("/");
            String path = u.substring(p2);
            String hostAndPort = u.substring(0, p2);
            int p3 = hostAndPort.indexOf(":");
            String host = hostAndPort.substring(0, p3);
            String port = hostAndPort.substring(p3 + 1);
            UrlHelper urlHelper = new UrlHelper();
            urlHelper.setPort(port);
            urlHelper.setScheme(scheme);
            urlHelper.setPath(path);
            urlHelper.setHost(host);
            return urlHelper;
        }

        private String host;
        private String port;
        private String path;
        private String scheme;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

    }

    public interface Matcher {
        Boolean match(String value);
    }

    public static class MatcherImpl implements Matcher {
        private String expect;

        public String getExpect() {
            return expect;
        }

        public MatcherImpl(String expect) {
            this.expect = expect;
        }

        @Override
        public Boolean match(String value) {
            if (expect.equals("*")) {
                return true;
            }
            return value.equals(expect);
        }
    }

    @Test
    public void name() throws URISyntaxException {
        UrlHelper registe = UrlHelper.valueOf("https://www.naver.com:*/qwe");
        UrlHelper req = UrlHelper.valueOf("https://www.naver.com:8080/qwe");

    }

    public void aaaaaaa(String uri) {

        String pattern = "^([^/?#]+://)?((([^@\\[/?#]*)@)?(\\[[\\p{XDigit}:.]*[%\\p{Alnum}]*]|[^\\[/?#:]*)(:(.[^/]*(?:\\{[^/]+?})?))?)?([^?#]*)(\\?([^#]*))?(#(.*))?";
        Pattern p = Pattern.compile(pattern);
        java.util.regex.Matcher matcher = p.matcher(uri);
        if (matcher.matches()) {
            String scheme = matcher.group(1);
            String userInfo = matcher.group(4);
            String host = matcher.group(5);
            String port = matcher.group(6);
            String path = matcher.group(8);
            String query = matcher.group(10);
            String fragment = matcher.group(12);
            boolean opaque = false;
            if (StringUtils.hasLength(scheme)) {
                String rest = uri.substring(scheme.length());
                if (!rest.startsWith(":/")) {
                    opaque = true;
                }
            }
        } else {
            throw new IllegalArgumentException("[" + uri + "] is not a valid URI");
        }
    }

    @Test
    public void aaaa() {

        aaaaaaa("https://wwww.naver.com:port/path");
        aaaaaaa("wwww.naver.com:port/path");
        aaaaaaa("https://wwww.naver.com:port");
        aaaaaaa("https://127.0.0.1:port");
        aaaaaaa("jdbc:mysql://aaaa.com/");

    }
}
