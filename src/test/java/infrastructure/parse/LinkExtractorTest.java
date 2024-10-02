package infrastructure.parse;

import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.jsoup.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

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
        Connection.Response mockResponse = mock(Connection.Response.class);
        when(parse.execute()).thenReturn(mockResponse);  // parse.execute()의 결과를 Mocking
        when(mockResponse.body()).thenReturn(
                "<table class=\"table table-striped table-bordered clickable-table\" id=\"problemset\">" +
                        "<thead><tr>" +
                        "<th style=\"width:10%\">문제</th><th style=\"width:50%\">문제 제목</th>" +
                        "<th style=\"width:20%\">정보</th><th style=\"width:7%\">맞힌 사람</th>" +
                        "<th style=\"width:5%\">제출</th><th style=\"width:8%\">정답 비율</th>" +
                        "</tr></thead>" +
                        "<tbody>" +
                        "<tr><td class=\"list_problem_id\">1000</td><td><a href=\"/problem/1000\" class=\"result-ac\">A+B</a></td>" +
                        "<td><span class=\"problem-label problem-label-ac\">성공</span>" +
                        "<span class=\"problem-label problem-label-multilang\">다국어</span></td>" +
                        "<td><a href=\"/status?from_problem=1&amp;problem_id=1000&amp;result_id=4\">313207</a></td>" +
                        "<td><a href=\"/status?from_problem=1&amp;problem_id=1000\">1167122</a></td>" +
                        "<td>38.783%</td></tr>" +
                        "</tbody></table>"
        );
        try (MockedStatic<BySelector> bySelectorMock = mockStatic(BySelector.class)) {
            bySelectorMock.when(BySelector::getProblemSet).thenReturn("#problemset > tbody > tr > td:nth-child(2) > a");
            List<String> result = linkExtractor.extractProblemProfile();
            assertEquals(1, result.size());
            assertEquals("/problem/1000", result.get(0));
        }
    }
    @Test
    void transformLinkTest() {
        List<String> expected = List.of(
                "https://www.acmicpc.net/problem/1000",
                "https://www.acmicpc.net/problem/1001",
                "https://www.acmicpc.net/problem/1008",
                "https://www.acmicpc.net/problem/1546",
                "https://www.acmicpc.net/problem/2557",
                "https://www.acmicpc.net/problem/2743",
                "https://www.acmicpc.net/problem/10093",
                "https://www.acmicpc.net/problem/10998",
                "https://www.acmicpc.net/problem/30017"
        );
        List<String> problemInfo = List.of(
                "/problem/1000",
                "/problem/1001",
                "/problem/1008",
                "/problem/1546",
                "/problem/2557",
                "/problem/2743",
                "/problem/10093",
                "/problem/10998",
                "/problem/30017");
        List<String> actual = linkExtractor.transformLink(problemInfo);
        assertThat(actual).isEqualTo(expected);
    }
}

