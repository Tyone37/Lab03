package arena.entity;

/** Nhà quảng cáo với phản hồi tài trợ được đặt trước để giả lập. */
public class Advertiser {
    private final String name;
    private final boolean acceptsSponsorship;

    /**
     * Tạo nhà quảng cáo mẫu.
     * @param name tên nhà quảng cáo
     * @param acceptsSponsorship có chấp nhận lời mời tài trợ hay không
     */
    public Advertiser(String name, boolean acceptsSponsorship) {
        this.name = name;
        this.acceptsSponsorship = acceptsSponsorship;
    }

    /**
     * Lấy tên nhà quảng cáo.
     * @return tên nhà quảng cáo
     */
    public String getName() {
        return name;
    }

    /**
     * Lấy phản hồi chấp nhận hoặc từ chối tài trợ.
     * @return phản hồi chấp nhận hoặc từ chối tài trợ
     */
    public boolean acceptsSponsorship() {
        return acceptsSponsorship;
    }
}
