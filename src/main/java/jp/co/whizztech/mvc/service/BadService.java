package jp.co.whizztech.mvc.service;

import jp.co.whizztech.mvc.entity.Song;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 悪いサービス例
 * 曲のセレクトしかしておらず、検索後のデータ加工などは
 * すべてコントローラがやっている。
 * Created by hilo on 2017/05/24.
 */
@Service
@Data
public class BadService {

    private final JdbcTemplate jdbc;

    /**
     * 歌の一覧をユニットIDから取得
     *
     * @param unitId ユニットID
     * @return 歌リスト
     */
    public List<Song> selectSongsByUnitId(Long unitId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM song S ");
        List<Object> params = new ArrayList<>();

        if (unitId != null) {
            sql.append(" WHERE EXISTS (")
                    .append(" SELECT * FROM unit_song USG ")
                    .append(" WHERE S.id = USG.song_id AND USG.unit_id = ? )");
            params.add(unitId);
        }

        RowMapper<Song> mapper = new BeanPropertyRowMapper<>(Song.class);
        return jdbc.query(sql.toString(), params.toArray(), mapper);
    }
}
