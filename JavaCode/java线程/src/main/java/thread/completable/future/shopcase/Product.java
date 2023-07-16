package thread.completable.future.shopcase;

import lombok.*;

/**
 * 模拟电商商品
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 电商平台
     */
    private String shop;

    /**
     * 价格
     */
    private double price;
}
