package org.example.domain;

import lombok.*;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:45
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Goods {
    private String name;
    private String category;
    private Double price;
    private Double discount;
    private Double portprice;
    private Integer store;
    private Integer id;
}
