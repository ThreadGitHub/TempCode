package org.juc.future.completable_future.shop_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 模拟商品
 * @author thread
 * @date 2023/9/26 21:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors
public class Product {
    private String name;

    private Double prize;
}
