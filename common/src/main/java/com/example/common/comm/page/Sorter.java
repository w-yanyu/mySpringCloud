package com.example.common.comm.page;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Sorter
 * @Author yanyu
 * @Date 2024/5/6 21:30
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class Sorter {

    /**
     * 排序分割符 name-desc
     */
    private static final String SEPARATOR = "-";

    /**
     * 排序字段 "sort": "score-desc,grade-asc"
     */
    protected String sort;

    protected List<SortKey> SortKeys;

    public List<SortKey> getSortKeys() {
        SortKeys = new ArrayList<>();
        if (StringUtils.isEmpty(sort)) {
            SortKeys.add(new SortKey("update_time", false));
        } else {
            List<String> list = Arrays.asList(sort.split(","));
            list.forEach(a -> {
                String[] split = a.split(SEPARATOR, 2);
                if (split.length == 2) {
                    SortKeys.add(new SortKey(split[0], split[1].toUpperCase().equals(SqlKeyword.ASC.getSqlSegment())));
                } else if (split.length == 1) {
                    SortKeys.add(new SortKey(split[0], true));
                }
            });
        }
        return SortKeys;
    }

    @Data
    @AllArgsConstructor
    public static class SortKey {
        private String key;
        private boolean asc;
    }
}
