package arena.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import arena.entity.Advertiser;
import arena.entity.Arena;
import arena.entity.League;
import arena.entity.LeagueOwner;
import arena.entity.Tournament;

/** Điều phối việc tạo giải, chọn tài trợ và thông báo trong UC. */
public class AnnounceTournamentController {
    private final Arena arena;

    /**
     * Kết nối controller với hệ thống ARENA.
     * @param arena hệ thống dùng trong bản giả lập
     */
    public AnnounceTournamentController(Arena arena) {
        this.arena = arena;
    }

    /**
     * Kiểm tra dữ liệu và hạn mức, sau đó lưu giải trong league và ARENA.
     * @param owner chủ giải đã đăng nhập
     * @param name tên giải
     * @param applicationStart ngày mở đăng ký
     * @param applicationEnd ngày đóng đăng ký
     * @param playStart ngày bắt đầu thi đấu
     * @param playEnd ngày kết thúc thi đấu
     * @param maximumPlayers số người chơi tối đa
     * @return giải vừa tạo
     * @throws IllegalArgumentException nếu dữ liệu không hợp lệ
     * @throws IllegalStateException nếu đã đạt hạn mức giải
     */
    public Tournament createTournament(LeagueOwner owner, String name,
            LocalDate applicationStart, LocalDate applicationEnd,
            LocalDate playStart, LocalDate playEnd, int maximumPlayers) {
        if (name.trim().isEmpty() || maximumPlayers < 2
                || applicationStart.isAfter(applicationEnd)
                || !applicationEnd.isBefore(playStart) || playStart.isAfter(playEnd)) {
            throw new IllegalArgumentException("Tên, số người chơi hoặc thứ tự ngày không hợp lệ.");
        }
        League league = owner.getLeague();
        if (!arena.hasCapacity() || !league.hasCapacity()) {
            throw new IllegalStateException("Đã đạt giới hạn số giải của ARENA hoặc league.");
        }
        Tournament tournament = new Tournament(name, applicationStart,
                applicationEnd, playStart, playEnd, maximumPlayers);
        league.addTournament(tournament);
        arena.addTournament(tournament);
        return tournament;
    }

    /**
     * Gửi lời mời giả lập và trả về các nhà quảng cáo chấp nhận.
     * @param advertisers danh sách nhà quảng cáo được mời
     * @return danh sách chấp nhận tài trợ
     */
    public List<Advertiser> requestSponsorship(List<Advertiser> advertisers) {
        List<Advertiser> accepted = new java.util.ArrayList<>();
        for (Advertiser advertiser : advertisers) {
            System.out.println("Mời " + advertiser.getName() + ": "
                    + (advertiser.acceptsSponsorship() ? "Chấp nhận" : "Từ chối"));
            if (advertiser.acceptsSponsorship()) {
                accepted.add(advertiser);
            }
        }
        return accepted;
    }

    /**
     * Ghi lựa chọn tài trợ và in phí giả lập.
     * @param tournament giải cần tài trợ
     * @param sponsor nhà tài trợ chấp nhận, hoặc null để dùng banner chung
     */
    public void selectSponsor(Tournament tournament, Advertiser sponsor) {
        tournament.setSponsor(sponsor);
        if (sponsor == null) {
            System.out.println("Dùng banner chung ngẫu nhiên; phí giả lập 1 đơn vị/lượt hiển thị.");
        } else {
            System.out.println("Thông báo " + sponsor.getName()
                    + " được chọn. Ghi phí tài trợ giả lập: 100 đơn vị.");
        }
    }

    /**
     * Tạo một URL mẫu, không tạo website thật.
     * @param tournament giải cần công bố
     */
    public void publishHomePage(Tournament tournament) {
        tournament.setHomePage("https://arena.example/tournaments/"
                + java.net.URLEncoder.encode(tournament.getName(),
                        java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Giả lập gửi thông báo vào ngày mở đăng ký cho nhóm được chọn.
     * @param tournament giải cần thông báo
     * @param group tên nhóm người nhận
     */
    public void notifyGroup(Tournament tournament, String group) {
        System.out.println("[Giả lập ngày " + tournament.getApplicationStart()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                + "] Thông báo cho " + group + ": " + tournament.getName()
                + " - " + tournament.getHomePage());
    }
}
