package arena.app;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import arena.boundary.AnnounceTournamentBoundary;
import arena.control.AnnounceTournamentController;
import arena.entity.Advertiser;
import arena.entity.Arena;
import arena.entity.League;
import arena.entity.LeagueOwner;

/** Điểm khởi chạy bản giả lập console của UC AnnounceTournament. */
public class ArenaApplication {
    /** Ngăn tạo đối tượng cho lớp chỉ chứa điểm khởi chạy. */
    private ArenaApplication() {
    }

    /**
     * Tạo dữ liệu mẫu và bắt đầu UC.
     * @param args tham số dòng lệnh, không sử dụng
     */
    public static void main(String[] args) {
        Arena arena = new Arena(10);
        League league = new League(3);
        LeagueOwner owner = new LeagueOwner("Chủ giải mẫu", league);
        List<Advertiser> advertisers = Arrays.asList(
                new Advertiser("Alpha", true), new Advertiser("Beta", false));
        AnnounceTournamentController controller = new AnnounceTournamentController(arena);
        try (Scanner scanner = new Scanner(System.in)) {
            AnnounceTournamentBoundary boundary = new AnnounceTournamentBoundary(scanner, controller);
            boundary.run(owner, advertisers);
        }
    }
}
