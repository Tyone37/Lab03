package arena.boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

import arena.control.AnnounceTournamentController;
import arena.entity.Advertiser;
import arena.entity.LeagueOwner;
import arena.entity.Tournament;

/** Giao diện console nhận thông tin và lựa chọn của chủ giải. */
public class AnnounceTournamentBoundary {
    /** Định dạng ngày-tháng-năm với năm gồm bốn chữ số. */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    private final Scanner scanner;
    private final AnnounceTournamentController controller;

    /**
     * Tạo giao diện console.
     * @param scanner bộ đọc bàn phím
     * @param controller đối tượng điều phối UC
     */
    public AnnounceTournamentBoundary(Scanner scanner,
            AnnounceTournamentController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    /**
     * Thực hiện một lần công bố giải; báo lỗi nếu dữ liệu không hợp lệ.
     * @param owner chủ giải đã đăng nhập
     * @param advertisers nhà quảng cáo mẫu để mời tài trợ
     */
    public void run(LeagueOwner owner, List<Advertiser> advertisers) {
        System.out.println("ARENA - AnnounceTournament; chủ giải: " + owner.getName());
        try {
            String name = read("Tên giải: ");
            LocalDate applicationStart = LocalDate.parse(read("Ngày mở đăng ký (dd-MM-yyyy): "), DATE_FORMAT);
            LocalDate applicationEnd = LocalDate.parse(read("Ngày đóng đăng ký (dd-MM-yyyy): "), DATE_FORMAT);
            LocalDate playStart = LocalDate.parse(read("Ngày bắt đầu thi đấu (dd-MM-yyyy): "), DATE_FORMAT);
            LocalDate playEnd = LocalDate.parse(read("Ngày kết thúc thi đấu (dd-MM-yyyy): "), DATE_FORMAT);
            int maximumPlayers = Integer.parseInt(read("Số người chơi tối đa: "));
            Tournament tournament = controller.createTournament(owner, name,
                    applicationStart, applicationEnd, playStart, playEnd, maximumPlayers);

            Advertiser sponsor = null;
            if (readYesNo("Mời các nhà quảng cáo mẫu tài trợ?")) {
                List<Advertiser> accepted = controller.requestSponsorship(advertisers);
                for (int i = 0; i < accepted.size(); i++) {
                    System.out.println((i + 1) + ". " + accepted.get(i).getName());
                }
                int choice = Integer.parseInt(read("Chọn nhà tài trợ (0 để dùng banner chung): "));
                if (choice < 0 || choice > accepted.size()) {
                    throw new IllegalArgumentException("Số thứ tự nhà tài trợ không hợp lệ.");
                }
                if (choice > 0) {
                    sponsor = accepted.get(choice - 1);
                }
            }
            controller.selectSponsor(tournament, sponsor);
            controller.publishHomePage(tournament);
            for (String group : new String[] {"Players", "Spectators", "Advertisers"}) {
                if (readYesNo("Thông báo cho " + group + "?")) {
                    controller.notifyGroup(tournament, group);
                }
            }
            tournament.printSummary();
        } catch (IllegalArgumentException | IllegalStateException
                | java.time.format.DateTimeParseException exception) {
            System.out.println("Không hoàn tất UC: " + exception.getMessage());
        }
    }

    /**
     * Đọc một dòng từ console.
     * @param prompt lời nhắc nhập
     * @return chuỗi đã bỏ khoảng trắng hai đầu
     */
    private String read(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Đọc lựa chọn có hoặc không.
     * @param prompt câu hỏi lựa chọn
     * @return true khi người dùng chọn Y
     */
    private boolean readYesNo(String prompt) {
        while (true) {
            String answer = read(prompt + " (Y/N): ");
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Vui lòng nhập Y hoặc N.");
        }
    }
}
