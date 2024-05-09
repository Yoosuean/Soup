package io.ssafy.soupapi.domain.projectbuilder.dao;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class TableDefinition {
    public String id;
    public String name;
    public String comment;
    @Builder.Default
    public List<String> columnIds = new ArrayList<>();
    @Builder.Default
    public List<String> seqColumnIds = new ArrayList<>();
    @Builder.Default
    public Map<String, ColumnDefinition> columns = new HashMap<>();
}