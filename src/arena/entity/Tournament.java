package arena.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Thông tin giải đấu, phương án quảng cáo và trang chủ giả lập. */
public class Tournament {
    private final String name;
    private final LocalDate applicationStart;
    private final LocalDate applicationEnd;
    private final LocalDate playStart;
    private final LocalDate playEnd;
    private final int maximumPlayers;
    private Advertiser sponsor;
    private String homePage;

    /**
     * Lưu thông tin giải sau khi controller kiểm tra dữ liệu.
     * @param name tên giải
     * @param applicationStart ngày mở đăng ký
     * @param applicationEnd ngày đóng đăng ký
     * @param playStart ngày bắt đầu thi đấu
     * @param playEnd ngày kết thúc thi đấu
     * @param maximumPlayers số người chơi tối đa
     */
    public Tournament(String name, LocalDate applicationStart,
            LocalDate applicationEnd, LocalDate playStart, LocalDate playEnd,
            int maximumPlayers) {
        this.name = name;
        this.applicationStart = applicationStart;
        this.applicationEnd = applicationEnd;
        this.playStart = playStart;
        this.playEnd = playEnd;
        this.maximumPlayers = maximumPlayers;
    }

    /**
     * Lấy tên giải.
     * @return tên giải
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy ngày mở đăng ký.
     * @return ngày mở đăng ký
     */
    public LocalDate getApplicationStart() {
        return applicationStart;
    }

    /**
     * Gắn nhà tài trợ; null biểu thị dùng banner chung.
     * @param sponsor nhà tài trợ được chọn hoặc null
     */
    public void setSponsor(Advertiser sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Lưu URL trang chủ giả lập.
     * @param homePage URL của giải
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * Lấy URL trang chủ giả lập.
     * @return URL trang chủ giả lập
     */
    public String getHomePage() {
        return homePage;
    }

    /** In thông tin giải ra console. */
    public void printSummary() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("\n--- Thông tin giải ---");
        System.out.println("Tên: " + name);
        System.out.println("Đăng ký: " + applicationStart.format(dateFormat)
                + " đến " + applicationEnd.format(dateFormat));
        System.out.println("Thi đấu: " + playStart.format(dateFormat)
                + " đến " + playEnd.format(dateFormat));
        System.out.println("Số người chơi tối đa: " + maximumPlayers);
        System.out.println("Quảng cáo: "
                + (sponsor == null ? "Banner chung ngẫu nhiên" : sponsor.getName()));
        System.out.println("Trang chủ: " + homePage);
    }
}
