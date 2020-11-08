package com.qdbgame.newsplatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/15 14:30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrowsePermission implements Serializable {
    Integer permissionId;
    Integer userId;
    Integer newsId;
}
