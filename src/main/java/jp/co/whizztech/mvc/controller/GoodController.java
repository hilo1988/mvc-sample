package jp.co.whizztech.mvc.controller;

import jp.co.whizztech.mvc.repository.UnitRepository;
import jp.co.whizztech.mvc.service.GoodService;
import jp.co.whizztech.mvc.valueobject.SongInfo;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * コントローラのいい例
 * Created by hilo on 2017/05/24.
 */
@Data
@Controller()
@RequestMapping("/good")
public class GoodController {

    private final GoodService service;

    private final UnitRepository unitRepository;

    /**
     * 初期表示
     * @param model モデル
     * @return html
     */
    @RequestMapping
    public String index(Model model) {
        model.addAttribute("units", unitRepository.findAll());
        return "good";
    }


    /**
     * 検索メソッド
     * @param unitId ユニットID
     * @param model モデル
     * @return html
     */
    @RequestMapping(value = "search")
    public String search(@RequestParam(name = "unitId", required = false) Long unitId, Model model) {

        // 検索とデータ加工をモデルに任せる
        List<SongInfo> infoList = service.selectSongInfoByUnitId(unitId);

        // コントローラは、属性のセットのみ
        model.addAttribute("songs", infoList);
        model.addAttribute("units", unitRepository.findAll());

        return "good";
    }
}
