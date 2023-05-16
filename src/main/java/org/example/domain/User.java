package org.example.domain;

import lombok.*;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:41
 */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Integer id;
    private String username;
    private String password;
    private Double balance;
    private Integer identity;
}
