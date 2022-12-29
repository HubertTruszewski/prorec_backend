package pl.edu.pw.elka.pis05.prorec.challenge.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TEST_CASE")
@Table(name = "TEST_CASES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestCase implements Serializable {

    @Id
    @Column(name = "TEST_CASE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long testCaseId;

    @Column(nullable = false)
    private String expression;

    @Column
    private String expectedValue;

    @Enumerated(EnumType.ORDINAL)
    private ExpectedValueType expectedValueType;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    public TestCase(final String expression, final String expectedValue, final ExpectedValueType expectedValueType,
            final Challenge challenge) {
        this.expression = expression;
        this.expectedValue = expectedValue;
        this.expectedValueType = expectedValueType;
        this.challenge = challenge;
    }
}
