package jp.co.whizztech.mvc.controller;

import jp.co.whizztech.mvc.entity.Song;
import jp.co.whizztech.mvc.entity.Unit;
import jp.co.whizztech.mvc.entity.UnitSong;
import jp.co.whizztech.mvc.repository.UnitRepository;
import jp.co.whizztech.mvc.repository.UnitSongRepository;
import jp.co.whizztech.mvc.service.BadService;
import jp.co.whizztech.mvc.valueobject.SongInfo;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 悪いサンプルコントローラ
 * ユニットを指定して曲の一覧を検索するコントローラ
 * Created by hilo on 2017/05/24.
 */
@RequestMapping("/bad")
@Controller
@Data
public class BadController {

    private final BadService badService;

    private final UnitSongRepository unitSongRepository;

    private final UnitRepository unitRepository;

    /**
     * 初期表示
     * @param model モデル
     * @return html
     */
    @RequestMapping
    public String index(Model model) {
        model.addAttribute("units", unitRepository.findAll());
        return "bad";
    }


    /**
     * 検索メソッド
     * @param unitId ユニットID
     * @param model モデル
     * @return html
     */
    @RequestMapping(value = "search")
    public String search(@RequestParam(name = "unitId", required = false) Long unitId, Model model) {


        List<Song> songList = badService.selectSongsByUnitId(unitId);
        List<SongInfo> infoList = new ArrayList<>();


        // XXX ここでデータ加工をしてしまっている。
        for (Song song :songList) {
            UnitSong unitSong = unitSongRepository.findBySongId(song.getId());
            Unit unit = unitRepository.findById(unitSong.getUnitId());

            SongInfo info = new SongInfo(song.getId(), song.getName(), unit.getName());
            infoList.add(info);
        }

        model.addAttribute("songs", infoList);
        model.addAttribute("units", unitRepository.findAll());
        return "bad";
    }

}
