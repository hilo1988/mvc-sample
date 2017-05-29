package jp.co.whizztech.mvc.builder;

import jp.co.whizztech.mvc.builder.impl.SearchSongQueryBuilder;
import lombok.Data;

import java.util.Collection;

/**
 * SQLクエリビルダー
 * Created by hilo on 2017/05/24.
 */
public interface SqlBuilder {

    QueryInfo build();

    /**
     * SQLクエリを生成するときにできるクエリ
     */
    @Data
    class QueryInfo {
        private final String query;

        private final Object[] params;

        public QueryInfo(StringBuilder sql, Collection<?> params) {
            this.query = sql.toString();
            this.params = params.toArray();
        }
    }

    /**
     * Sqlクエリビルダーファクトリ
     */
    class Factory {
        public static SqlBuilder getSearchSongQueryBuilder(Long unitId) {
            return new SearchSongQueryBuilder(unitId);
        }
    }
}
