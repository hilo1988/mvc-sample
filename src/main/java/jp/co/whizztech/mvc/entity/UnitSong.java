package jp.co.whizztech.mvc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * メンバーの歌を定義するエンティティ
 * Created by hilo on 2017/05/24.
 */
@Entity
@Table(name = "unit_song")
@Data
public class UnitSong {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "song_id")
    private Long songId;
}
