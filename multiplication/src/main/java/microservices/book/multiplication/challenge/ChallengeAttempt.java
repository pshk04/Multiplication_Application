package microservices.book.multiplication.challenge;

import lombok.*;
import microservices.book.multiplication.user.User;

import javax.persistence.*;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

//    public ChallengeAttempt(final User user, final int factorA, final int factorB, final int resultAttempt, final boolean correct){
//        this(null, user, factorA, factorB, resultAttempt, correct);
//    }
}