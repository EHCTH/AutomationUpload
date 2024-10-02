package infrastructure.parse;

import org.jsoup.Connection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class ProblemExtractorTest {

    @Test
    public void testExtractProblemProfile() throws IOException {
        Parse parseMock = Mockito.mock(Parse.class);

        Connection.Response responseMock = Mockito.mock(Connection.Response.class);
        ExtractorManager problemExtractor = new ProblemExtractor();

        String fakeHtml = """
                  <table class="table table-striped table-bordered clickable-table" id="problemset"><thead><tr><th style="width:10%">문제</th><th style="width:50%">문제 제목</th><th style="width:20%">정보</th><th style="width:7%">맞힌 사람</th><th style="width:5%">제출</th><th style="width:8%">정답 비율</th></tr></thead><tbody><tr><td class="list_problem_id">1000</td><td><a href="/problem/1000" class="result-ac">A+B</a></td><td><span class="problem-label problem-label-ac">성공</span><span class="problem-label problem-label-multilang">다국어</span></td><td><a href="/status?from_problem=1&amp;problem_id=1000&amp;result_id=4">313207</a></td><td><a href="/status?from_problem=1&amp;problem_id=1000">1167122</a></td><td>38.783%</td></tr><tr><td class="list_problem_id">1001</td><td><a href="/problem/1001" class="result-ac">A-B</a></td><td><span class="problem-label problem-label-ac">성공</span></td><td><a href="/status?from_problem=1&amp;problem_id=1001&amp;result_id=4">268874</a></td><td><a href="/status?from_problem=1&amp;problem_id=1001">471793</a></td><td>69.334%</td></tr><tr><td class="list_problem_id">1008</td><td><a href="/problem/1008" class="result-ac">A/B</a></td><td><span class="problem-label problem-label-ac">성공</span><span class="problem-label problem-label-spj">스페셜 저지</span></td><td><a href="/status?from_problem=1&amp;problem_id=1008&amp;result_id=4">221370</a></td><td><a href="/status?from_problem=1&amp;problem_id=1008">787032</a></td><td>34.503%</td></tr><tr><td class="list_problem_id">1546</td><td><a href="/problem/1546" class="result-ac">평균</a></td><td><span class="problem-label problem-label-ac">성공</span><span class="problem-label problem-label-spj">스페셜 저지</span></td><td><a href="/status?from_problem=1&amp;problem_id=1546&amp;result_id=4">123962</a></td><td><a href="/status?from_problem=1&amp;problem_id=1546">301884</a></td><td>50.254%</td></tr><tr><td class="list_problem_id">2557</td><td><a href="/problem/2557" class="result-ac">Hello World</a></td><td><span class="problem-label problem-label-ac">성공</span></td><td><a href="/status?from_problem=1&amp;problem_id=2557&amp;result_id=4">346451</a></td><td><a href="/status?from_problem=1&amp;problem_id=2557">1238271</a></td><td>39.407%</td></tr><tr><td class="list_problem_id">2743</td><td><a href="/problem/2743" class="result-ac">단어 길이 재기</a></td><td><span class="problem-label problem-label-ac">성공</span></td><td><a href="/status?from_problem=1&amp;problem_id=2743&amp;result_id=4">64608</a></td><td><a href="/status?from_problem=1&amp;problem_id=2743">90709</a></td><td>80.214%</td></tr><tr><td class="list_problem_id">10093</td><td><a href="/problem/10093" class="result-ac">숫자</a></td><td><span class="problem-label problem-label-ac">성공</span><span class="problem-label problem-label-subtask">서브태스크</span><span class="problem-label problem-label-multilang">다국어</span></td><td><a href="/status?from_problem=1&amp;problem_id=10093&amp;result_id=4">6860</a></td><td><a href="/status?from_problem=1&amp;problem_id=10093">34115</a></td><td>23.394%</td></tr><tr><td class="list_problem_id">10998</td><td><a href="/problem/10998" class="result-ac">A×B</a></td><td><span class="problem-label problem-label-ac">성공</span></td><td><a href="/status?from_problem=1&amp;problem_id=10998&amp;result_id=4">223780</a></td><td><a href="/status?from_problem=1&amp;problem_id=10998">335613</a></td><td>76.822%</td></tr><tr><td class="list_problem_id">30017</td><td><a href="/problem/30017" class="result-ac">치즈버거 만들기</a></td><td><span class="problem-label problem-label-ac">성공</span></td><td><a href="/status?from_problem=1&amp;problem_id=30017&amp;result_id=4">1577</a></td><td><a href="/status?from_problem=1&amp;problem_id=30017">2623</a></td><td>66.624%</td></tr></tbody></table>
                """;

        Mockito.when(parseMock.execute()).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn(fakeHtml);

        List<String> result = problemExtractor.extractProblemProfile(parseMock);
        assertThat(result).containsExactly(
                "/problem/1000",
                "/problem/1001",
                "/problem/1008",
                "/problem/1546",
                "/problem/2557",
                "/problem/2743",
                "/problem/10093",
                "/problem/10998",
                "/problem/30017");
    }
    @Test
    void extractorLink() {
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
        ExtractorManager extractor = new ProblemExtractor();
        List<String> actual = extractor.extractProblemLink(problemInfo);
        assertThat(actual).isEqualTo(expected);
    }
}
