package jp.co.whizztech.mvc.service;

import jp.co.whizztech.mvc.builder.SqlBuilder;
import jp.co.whizztech.mvc.entity.Song;
import jp.co.whizztech.mvc.entity.Unit;
import jp.co.whizztech.mvc.entity.UnitSong;
import jp.co.whizztech.mvc.repository.UnitRepository;
import jp.co.whizztech.mvc.repository.UnitSongRepository;
import jp.co.whizztech.mvc.valueobject.SongInfo;
import lombok.Data;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 良いサービス例
 * Created by hilo on 2017/05/24.
 */
@Data
@Service
public class GoodService {

    private final JdbcTemplate jdbc;

    private final UnitSongRepository unitSongRepository;

    private final UnitRepository unitRepository;

    /**
     * ユニットIDをキーに歌情報を取得
     * @param unitId ユニットID
     * @return 歌情報リスト
     */
    public List<SongInfo> selectSongInfoByUnitId(Long unitId) {
        List<Song> songList = selectSongsByUnitId(unitId);
        List<SongInfo> infoList = new ArrayList<>();


        for (Song song :songList) {
            UnitSong unitSong = unitSongRepository.findBySongId(song.getId());
            Unit unit = unitRepository.findById(unitSong.getUnitId());

            SongInfo info = new SongInfo(song.getId(), song.getName(), unit.getName());
            infoList.add(info);
        }

        return infoList;
    }



    /**
     * 歌の一覧をユニットIDから取得
     *
     * @param unitId ユニットID
     * @return 歌リスト
     */
    private List<Song> selectSongsByUnitId(Long unitId) {
        // 検索項目や条件が増えるたびに肥大化するため、SqlBuilderを定義。
        // これで肥大化を防ぐことができる。
        SqlBuilder.QueryInfo info = SqlBuilder.Factory.getSearchSongQueryBuilder(unitId).build();
        RowMapper<Song> mapper = new BeanPropertyRowMapper<>(Song.class);
        return jdbc.query(info.getQuery(), info.getParams(), mapper);
    }

}
