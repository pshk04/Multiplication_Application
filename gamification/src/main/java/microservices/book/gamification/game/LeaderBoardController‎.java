
package microservices.book.gamification.game;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import microservices.book.gamification.game.domain.LeaderBoardRow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * This class implements a REST API for the Gamification LeaderBoard service.
 */
@Slf4j
@RestController
@RequestMapping("/leaders")
@RequiredArgsConstructor
class LeaderBoardController {

    private final LeaderBoardService leaderBoardService;

    @Value("${server.port}")
    private int port;

    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard() {
        log.info("Retrieving leaderboard on port: {}", port);
        return leaderBoardService.getCurrentLeaderBoard();
    }
}
