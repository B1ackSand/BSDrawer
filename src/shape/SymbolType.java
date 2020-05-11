package shape;

public interface SymbolType {
    /**
     * 圆角矩形
     */
    static final int ROUNDRECT = 1;
    /**
     * 直角矩形
     */
    static final int NORMALRECT = 2;
    /**
     * 菱形
     */
    static final int DIAMOND = 3;
    /**
     * 平行四边形
     */
    static final int PARALLELOGRAM = 4;
    /**
     * 箭头实线
     */
    static final int ARROWLINE = 5;
    /**
     * 连接点
     */
    static final int CONNECTOR = 6;
    /**
     * 单边曲线矩形
     */
    static final int CURVERECT = 7;
}
