package jp.co.whizztech.mvc.builder.impl;

import jp.co.whizztech.mvc.builder.SqlBuilder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 曲検索クエリビルダ
 * Created by hilo on 2017/05/24.
 */
@Data
public class SearchSongQueryBuilder implements SqlBuilder {

    private final Long unitId;

    @Override
    public QueryInfo build() {

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        sql.append("SELECT * FROM song S ");

        if (unitId != null) {
            sql.append(" WHERE EXISTS (")
                    .append(" SELECT * FROM unit_song USG ")
                    .append(" WHERE S.id = USG.song_id AND USG.unit_id = ? )");
            params.add(unitId);
        }

        return new QueryInfo(sql, params);
    }
}
