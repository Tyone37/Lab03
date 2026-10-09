package arena.entity;

import java.util.ArrayList;
import java.util.List;

/** Hệ thống ARENA lưu các giải được công bố trong bản giả lập. */
public class Arena {
    private final int maximumTournaments;
    private final List<Tournament> tournaments = new ArrayList<>();

    /**
     * Tạo hệ thống với hạn mức số giải.
     * @param maximumTournaments số giải tối đa của ARENA
     */
    public Arena(int maximumTournaments) {
        this.maximumTournaments = maximumTournaments;
    }

    /**
     * Lấy true nếu ARENA còn chỗ cho một giải mới.
     * @return true nếu ARENA còn chỗ cho một giải mới
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
