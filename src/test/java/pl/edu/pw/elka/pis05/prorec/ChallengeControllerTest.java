package pl.edu.pw.elka.pis05.prorec;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.ChallengeType;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ProrecApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ChallengeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChallengeRepository challengeRepository;


    @Test
    @DirtiesContext
    void get_all_challenges_empty_list() throws Exception {
        mvc.perform(get("/challenge/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[]"));
    }

    @Test
    @DirtiesContext
    void get_challenge() throws Exception {
        final Challenge challenge = new Challenge("Test Challenge",
                "desc",
                "snippet",
                ChallengeType.EASY,
                "examples",
                LanguageName.PYTHON);

        challengeRepository.save(challenge);

        final long challengeId = challenge.getChallengeId();

        MockHttpServletResponse response = mvc.perform(get(
                        "/challenge/" + challengeId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        mvc.perform(get("/challenge/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Test Challenge"))
                .andExpect(jsonPath("$.description").value("desc"))
                .andExpect(jsonPath("$.codeSnippet").value("snippet"))
                .andExpect(jsonPath("$.type").value("EASY"))
                .andExpect(jsonPath("$.exampleTestCases").value("examples"))
                .andExpect(jsonPath("$.language").value("PYTHON"));
    }

    @Test
    @DirtiesContext
    void add_new_challenge() throws Exception {
        final String newChallengeDTOJSON = "{\"name\": \"Avg value\",\"description\": \"The function should return an average value of numbers in an array given as input\",\"codeSnippet\": \"def avg(numbers):\\n    # your code goes here\\n    return\",\"exampleTestCases\": \"avg([1, 2, 3])===2\\navg([2, 3, 4])===3\",\"type\": \"EASY\",\"language\": \"PYTHON\"}";
        mvc.perform(post("/challenge/add").contentType(MediaType.APPLICATION_JSON).content(newChallengeDTOJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.challengeId").value("1"))
                .andExpect(jsonPath("$.name").value("Avg value"))
                .andExpect(jsonPath("$.description").value(
                        "The function should return an average value of numbers in an array given as input"))
                .andExpect(jsonPath("$.codeSnippet").value("def avg(numbers):\n    # your code goes here\n    return"))
                .andExpect(jsonPath("$.exampleTestCases").value("avg([1, 2, 3])===2\navg([2, 3, 4])===3"))
                .andExpect(jsonPath("$.type").value("EASY"))
                .andExpect(jsonPath("$.language").value("PYTHON"));
    }

    @Test
    @DirtiesContext
    void add_new_test_case() throws Exception {
        final Challenge challenge = new Challenge("Test Challenge",
                "desc",
                "snippet",
                ChallengeType.EASY,
                "examples",
                LanguageName.PYTHON);
        challengeRepository.save(challenge);
        final long challengeId = challenge.getChallengeId();
        final String newTestCaseDTO =
                "{\"expression\": \"avg([1, 2, 3])==2\", \"expectedValue\": \"2\", \"challengeId\": " + challengeId
                        + "}";
        mvc.perform(post("/challenge/addTestCase").contentType(MediaType.APPLICATION_JSON).content(newTestCaseDTO))
                .andExpect(status().isCreated());
    }
}
