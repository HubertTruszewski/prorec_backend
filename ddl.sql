CREATE TABLE public.USERS(
                             USER_ID BIGSERIAL NOT NULL PRIMARY KEY,
                             USERNAME VARCHAR(50) UNIQUE NOT NULL,
                             PASSWORD VARCHAR(75) NOT NULL,
                             EMAIL VARCHAR(70) UNIQUE NOT NULL,
                             ACTIVE BOOLEAN NOT NULL,
                             REGISTER_DATE TIMESTAMPTZ NOT NULL
);

CREATE TABLE public.CHALLENGES(
                                  CHALLENGE_ID BIGSERIAL NOT NULL PRIMARY KEY,
                                  NAME VARCHAR(60) NOT NULL,
                                  DESCRIPTION TEXT NOT NULL,
                                  CODE_SNIPPET TEXT NOT NULL,
                                  EXAMPLE_TEST_CASES TEXT NOT NULL,
                                  LANGUAGE int4 NOT NULL,
                                  TYPE int4 NOT NULL
);

CREATE TABLE public.TEST_CASES (
                                   TEST_CASE_ID BIGSERIAL NOT NULL PRIMARY KEY,
                                   EXPRESSION TEXT NOT NULL,
                                   EXPECTED_VALUE TEXT NOT NULL,
                                   EXPECTED_VALUE_TYPE INT4 NOT NULL,
                                   CHALLENGE_ID BIGINT NOT NULL,
                                   CONSTRAINT test_cases_challenges_challenge_id_fk FOREIGN KEY (CHALLENGE_ID) REFERENCES CHALLENGES(CHALLENGE_ID)
);

CREATE INDEX idx_test_cases_challenge_id ON TEST_CASES(CHALLENGE_ID);

CREATE TABLE public.ASSESSMENTS(
                                   ASSESSMENT_ID BIGSERIAL NOT NULL PRIMARY KEY,
                                   EMAIL VARCHAR(70) NOT NULL,
                                   TOKEN VARCHAR(36) NOT NULL,
                                   CREATE_DATE TIMESTAMPTZ NOT NULL,
                                   EXPIRY_DATE TIMESTAMPTZ NOT NULL,
                                   SOLVING_TIME INT4 NOT NULL,
                                   STATUS INT4 NOT NULL,
                                   DEADLINE TIMESTAMPTZ,
                                   USER_ID BIGINT NOT NULL,
                                   CONSTRAINT assessments_users_user_id_fk FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

CREATE INDEX idx_assessments_user_id ON ASSESSMENTS(USER_ID);

CREATE TABLE public.ASSESSMENT_SELECTED_CHALLENGES(
                                                      ASSESSMENT_ID BIGINT NOT NULL,
                                                      CHALLENGE_ID BIGINT NOT NULL,
                                                      CONSTRAINT assessment_selected_challenges_pk PRIMARY KEY (ASSESSMENT_ID, CHALLENGE_ID),
                                                      CONSTRAINT assessment_selected_challenges_assessments_assessment_id_fk FOREIGN KEY (ASSESSMENT_ID) REFERENCES ASSESSMENTS(ASSESSMENT_ID),
                                                      CONSTRAINT assessment_selected_challenges_challenges_challenge_id_fk FOREIGN KEY (CHALLENGE_ID) REFERENCES CHALLENGES(CHALLENGE_ID)
);

CREATE INDEX idx_assessments_selected_challenges_challenge_id ON ASSESSMENT_SELECTED_CHALLENGES(CHALLENGE_ID);

CREATE TABLE public.ATTEMPTS(
                                ATTEMPT_ID BIGSERIAL NOT NULL,
                                SUBMITTED_CODE TEXT NOT NULL,
                                CODE_ERROR BOOLEAN NOT NULL,
                                CODE_ERROR_DETAILS TEXT,
                                SUBMIT_DATE TIMESTAMPTZ NOT NULL,
                                ASSESSMENT_ID BIGINT NOT NULL,
                                CHALLENGE_ID BIGINT NOT NULL,
                                CONSTRAINT attempts_pk PRIMARY KEY (ATTEMPT_ID),
                                CONSTRAINT attempts_assessments_assessment_id_fk FOREIGN KEY (ASSESSMENT_ID) REFERENCES ASSESSMENTS(ASSESSMENT_ID),
                                CONSTRAINT attempts_selected_challenges_challenges_challenge_id_fk FOREIGN KEY (CHALLENGE_ID) REFERENCES CHALLENGES(CHALLENGE_ID)
);

CREATE INDEX idx_attempts_assessment_id ON ATTEMPTS(ASSESSMENT_ID);
CREATE INDEX idx_attempts_challenge_id ON ATTEMPTS(CHALLENGE_ID);

CREATE TABLE public.TEST_RESULTS(
                                    ATTEMPT_ID BIGINT NOT NULL,
                                    TEST_CASE_ID BIGINT NOT NULL,
                                    PASSED BOOLEAN NOT NULL,
                                    REMARKS TEXT,
                                    CONSTRAINT test_results_id PRIMARY KEY (ATTEMPT_ID, TEST_CASE_ID),
                                    CONSTRAINT test_results_attempts_attempt_id_fk FOREIGN KEY (ATTEMPT_ID) REFERENCES ATTEMPTS(ATTEMPT_ID),
                                    CONSTRAINT test_results_test_cases_test_case_id_fk FOREIGN KEY (TEST_CASE_ID) REFERENCES TEST_CASES(TEST_CASE_ID)
);
CREATE INDEX idx_test_results_test_case_id ON TEST_RESULTS(TEST_CASE_ID);
