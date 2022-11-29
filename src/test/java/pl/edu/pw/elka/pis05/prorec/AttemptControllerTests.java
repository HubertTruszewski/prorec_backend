package pl.edu.pw.elka.pis05.prorec;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.ChallengeType;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.TestCaseRepository;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ProrecApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class AttemptControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Test
    void perform_python_task() throws Exception {
        final Challenge challenge = new Challenge("Avg value",
                "avg value of list",
                "snippet",
                ChallengeType.EASY,
                "avg[1,2,3]==2",
                LanguageName.PYTHON);
        challengeRepository.save(challenge);
        testCaseRepository.save(new TestCase("avg([4, 5, 6])", 5, challenge));
        testCaseRepository.save(new TestCase("avg([4, 5, 9])", 6, challenge));

        mvc.perform(post("/attempt/perform").contentType(MediaType.APPLICATION_JSON)
                .content("{\"challengeId\": "+ challenge.getChallengeId() +", \"code\": \"def avg(numbers):\\n    return sum(numbers)/len(numbers)\"}"))
                .andExpect(content().string("Success: 2, failure: 0\n"));

    }
}
