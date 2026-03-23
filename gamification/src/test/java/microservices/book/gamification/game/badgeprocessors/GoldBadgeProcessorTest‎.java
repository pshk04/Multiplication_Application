package microservices.book.gamification.game.badgeprocessors;
import microservices.book.gamification.challenge.ChallengeSolvedEvent;
import microservices.book.gamification.game.domain.BadgeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GoldBadgeProcessorTest {
    private GoldBadgeProcessor badgeProcessor;

    @BeforeEach
    public void setUp() {
        badgeProcessor = new GoldBadgeProcessor();
    }

    @Test
    public void shouldGiveBadgeIfScoreOverThreshold() {
        Optional<BadgeType> badgeType = badgeProcessor
                .processForOptionalBadge(450, List.of(),
                        new ChallengeSolvedEvent(1L, true, 42, 10, 1L, "John")
                );
        assertThat(badgeType).contains(BadgeType.GOLD);
    }

    @Test
    public void shouldNotGiveBadgeIfScoreUnderThreshold() {
        Optional<BadgeType> badgeType = badgeProcessor
                .processForOptionalBadge(350, List.of(),
                        new ChallengeSolvedEvent(1L, true, 42, 10, 1L, "John")
                );
        assertThat(badgeType).isEmpty();
    }
}