package infrastructure.parse;

import domain.cookie.SeleniumCookie;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkExtractorTest {

    @Mock
    private DriverController driver;

    @Mock
    private WaitDriverController waitDriver;

    @Mock
    private Parse parse;

    @Mock
    private SeleniumCookie cookies;

    @InjectMocks
    private LinkExtractor linkExtractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        linkExtractor = new LinkExtractor(driver, waitDriver, parse);  // 의존성 주입
    }

    @Test
    void testExtractProblemProfile() throws IOException {
        // Given
        Connection mockConnection = mock(Connection.class);
        Connection.Response mockResponse = mock(Connection.Response.class);
        when(parse.executeWithCookies(mockConnection, cookies)).thenReturn(mockResponse);  // parse.executeWithCookies() 결과를 Mocking
        when(mockResponse.body()).thenReturn(
                "<table class=\"table table-striped table-bordered clickable-table\" id=\"problemset\">" +
                        "<thead><tr>" +
                        "<th>문제</th><th>문제 제목</th>" +
                        "</tr></thead>" +
                        "<tbody>" +
                        "<tr><td>1000</td><td><a href=\"/problem/1000\">A+B</a></td></tr>" +
                        "<tr><td>1001</td><td><a href=\"/problem/1001\">A-B</a></td></tr>" +
                        "</tbody></table>"
        );

        try (MockedStatic<BySelector> bySelectorMock = mockStatic(BySelector.class)) {
            bySelectorMock.when(BySelector::getProblemSet).thenReturn("#problemset > tbody > tr > td:nth-child(2) > a");

            // When
            List<String> result = linkExtractor.extractProblemProfile(mockConnection, cookies);

            // Then
            assertEquals(2, result.size());
            assertEquals("/problem/1000", result.get(0));
            assertEquals("/problem/1001", result.get(1));
        }
    }

    @Test
    void testTransformLink() {
        // Given
        List<String> problemInfo = List.of("/problem/1000", "/problem/1001");

        // When
        List<String> result = linkExtractor.transformLink(problemInfo);

        // Then
        List<String> expected = List.of(
                "https://www.acmicpc.net/problem/1000",
                "https://www.acmicpc.net/problem/1001"
        );
        assertThat(result).isEqualTo(expected);
    }
}
