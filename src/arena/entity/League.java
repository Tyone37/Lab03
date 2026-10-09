package arena.entity;

import java.util.ArrayList;
import java.util.List;

/** League lưu danh sách giải đấu và giới hạn số giải được tạo. */
public class League {
    private final int maximumTournaments;
    private final List<Tournament> tournaments = new ArrayList<>();

    /**
     * Tạo league.
     * @param maximumTournaments số giải tối đa
     */
    public League(int maximumTournaments) {
        this.maximumTournaments = maximumTournaments;
    }

    /**
     * Lấy true nếu league còn chỗ cho một giải mới.
     * @return true nếu league còn chỗ cho một giải mới
     */
    public boolean hasCapacity() {
        return tournaments.size() < maximumTournaments;
    }

    /**
     * Lưu giải đã được controller kiểm tra hạn mức.
     * @param tournament giải mới
     */
    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }
}
