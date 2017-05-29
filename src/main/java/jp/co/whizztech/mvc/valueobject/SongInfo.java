package jp.co.whizztech.mvc.valueobject;

import lombok.Data;

import java.io.Serializable;

/**
 * 歌の情報データ
 * Created by hilo on 2017/05/24.
 */
@Data
public class SongInfo implements Serializable {

    private final Long songId;

    private final String songName;

    private final String unitName;
}
