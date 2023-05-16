package org.example.domain;

import lombok.*;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 9:12
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Review {
    private Integer userid;
    private Integer goodid;
    private String content;
    private String time;
}
