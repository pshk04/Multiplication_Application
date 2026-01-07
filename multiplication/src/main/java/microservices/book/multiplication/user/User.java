package microservices.book.multiplication.user;
import lombok.*;

/**
 * Stores information to identify the user.
 */
@Getter
@EqualsAndHashCode
@ToString
public class User {
    private final Long id;
    private final String alias; // first name of the user

    public User(Long id, String alias) {
        this.id = id;
        this.alias = alias;
    }
}
