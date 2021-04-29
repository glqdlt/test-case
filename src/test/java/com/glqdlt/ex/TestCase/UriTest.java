package com.glqdlt.ex.TestCase;

import org.junit.Test;

import java.net.URISyntaxException;


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
}
