package arena.entity;

/** Chủ giải đã đăng nhập, được phép công bố giải trong league của mình. */
public class LeagueOwner {
    private final String name;
    private final League league;

    /**
     * Tạo chủ giải.
     * @param name tên chủ giải
     * @param league league do chủ giải quản lý
     */
    public LeagueOwner(String name, League league) {
        this.name = name;
        this.league = league;
    }

    /**
     * Lấy tên chủ giải.
     * @return tên chủ giải
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy league do chủ giải quản lý.
     * @return league do chủ giải quản lý
     */
    public League getLeague() {
        return league;
    }
}
