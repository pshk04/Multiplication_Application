package microservices.book.gamification.game.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BadgeType {
    // Badges depending on score
    BRONZE("Bronze \uD83E\uDD49"),
    SILVER("Silver \uD83E\uDD48"),
    GOLD("Gold \uD83E\uDD47"),
    // Other badges won for different conditions
    FIRST_WON("First Time \uD83C\uDF88"),
    LUCKY_NUMBER("Lucky Number \uD83C\uDF40");
    private final String description;

}