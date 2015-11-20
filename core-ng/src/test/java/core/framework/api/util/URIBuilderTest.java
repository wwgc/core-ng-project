package core.framework.api.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author neo
 */
public class URIBuilderTest {
    @Test
    public void encodePathSegment() {
        assertEquals("encode utf-8", "%E2%9C%93", URIBuilder.encodePathSegment("✓"));
        assertEquals("a%20b", URIBuilder.encodePathSegment("a b"));
        assertEquals("a+b", URIBuilder.encodePathSegment("a+b"));
        assertEquals("a=b", URIBuilder.encodePathSegment("a=b"));
        assertEquals("a%3Fb", URIBuilder.encodePathSegment("a?b"));
        assertEquals("a%2Fb", URIBuilder.encodePathSegment("a/b"));
        assertEquals("a&b", URIBuilder.encodePathSegment("a&b"));
    }

    @Test
    public void encodeQueryParam() {
        assertEquals("a%20b", URIBuilder.encodeQueryParam("a b"));
        assertEquals("a%2Bb", URIBuilder.encodeQueryParam("a+b"));
        assertEquals("a%3Db", URIBuilder.encodeQueryParam("a=b"));
        assertEquals("a?b", URIBuilder.encodeQueryParam("a?b"));
        assertEquals("a/b", URIBuilder.encodeQueryParam("a/b"));
        assertEquals("a%26b", URIBuilder.encodeQueryParam("a&b"));
    }

    @Test
    public void encodeFragment() {
        assertEquals("a%20b", URIBuilder.encodeFragment("a b"));
        assertEquals("a+b", URIBuilder.encodeFragment("a+b"));
        assertEquals("a=b", URIBuilder.encodeFragment("a=b"));
        assertEquals("a?b", URIBuilder.encodeFragment("a?b"));
        assertEquals("a/b", URIBuilder.encodeFragment("a/b"));
        assertEquals("a&b", URIBuilder.encodeFragment("a&b"));
    }

    @Test
    public void buildFullURL() {
        URIBuilder builder = new URIBuilder()
            .hostAddress("example.com")
            .addPath("path1")
            .addPath("path2");

        assertEquals("//example.com/path1/path2", builder.toURI());

        builder.addQueryParam("k1", "v1")
            .addQueryParam("k2", "v2");

        assertEquals("//example.com/path1/path2?k1=v1&k2=v2", builder.toURI());

        builder.scheme("http")
            .port(8080);
        assertEquals("http://example.com:8080/path1/path2?k1=v1&k2=v2", builder.toURI());
    }

    @Test
    public void buildFromExistingURI() {
        assertEquals("http://localhost/path1/path2?k1=v1+v1&k2=v2", new URIBuilder("http://localhost/path1/path2?k1=v1+v1&k2=v2").toURI());

        assertEquals("//localhost:8080/path1/path2/", new URIBuilder("//localhost:8080/path1").addPath("path2").addSlash().toURI());
    }

    @Test
    public void buildRelativeURL() {
        assertEquals("/?k1=v1%20v2", new URIBuilder().addSlash().addQueryParam("k1", "v1 v2").toURI());

        URIBuilder builder = new URIBuilder()
            .addPath("path1")
            .addPath("path2");

        assertEquals("path1/path2", builder.toURI());

        builder.addQueryParam("k1", "v1");
        builder.addQueryParam("k2", "v2");

        assertEquals("path1/path2?k1=v1&k2=v2", builder.toURI());

        builder.fragment("f1");
        assertEquals("path1/path2?k1=v1&k2=v2#f1", builder.toURI());
    }

    @Test
    public void buildRelativeURLWithTrailingSlash() {
        URIBuilder builder = new URIBuilder()
            .addSlash()
            .addPath("path1")
            .addPath("path2")
            .addSlash();

        assertEquals("/path1/path2/", builder.toURI());
    }
}