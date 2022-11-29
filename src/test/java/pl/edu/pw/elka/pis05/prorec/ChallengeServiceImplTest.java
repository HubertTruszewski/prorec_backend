package pl.edu.pw.elka.pis05.prorec;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.ChallengeType;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.TestCaseRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.service.ChallengeService;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ChallengeServiceImplTest {

    @MockBean
    private ChallengeRepository challengeRepository;

    @MockBean
    private TestCaseRepository testCaseRepository;

    @Autowired
    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        Mockito.when(challengeRepository.findByChallengeId(1))
                .thenReturn(new Challenge(1,
                        "Test challenge",
                        "desc",
                        "code",
                        ChallengeType.EASY,
                        "example",
                        new ArrayList<>(),
                        LanguageName.PYTHON));

        Mockito.when(challengeRepository.save(new Challenge("abc",
                        "def",
                        "code",
                        ChallengeType.EASY,
                        "example",
                        LanguageName.PYTHON)))
                .thenReturn(new Challenge(3,
                        "abc",
                        "def",
                        "code",
                        ChallengeType.EASY,
                        "example",
                        new ArrayList<>(),
                        LanguageName.PYTHON));
    }

    @Test
    void get_challenge_by_id() {
        final ChallengeDTO challenge = challengeService.findByChallengeId(1);
        assertThat(challenge.getChallengeId()).isEqualTo(1);
        assertThat(challenge.getName()).isEqualTo("Test challenge");
        assertThat(challenge.getDescription()).isEqualTo("desc");
        assertThat(challenge.getCodeSnippet()).isEqualTo("code");
        assertThat(challenge.getType().name()).isEqualTo("EASY");
        assertThat(challenge.getExampleTestCases()).isEqualTo("example");
        assertThat(challenge.getLanguage().name()).isEqualTo("PYTHON");
    }
}
