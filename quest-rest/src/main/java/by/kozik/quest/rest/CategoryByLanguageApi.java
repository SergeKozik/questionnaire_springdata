package by.kozik.quest.rest;

import by.kozik.quest.bean.FormActionBean;
import by.kozik.quest.service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Serge on 27.02.2017.
 */
@RestController
@RequestMapping(value = "/rest/quest/categories", produces = "application/json")
public class CategoryByLanguageApi {

    @Autowired
    private QuestService questService;

    @ResponseBody
    @RequestMapping(value = "/{language}",method = RequestMethod.GET)
    public ResponseEntity<List<String>> getPermissions(@PathVariable String language) {
        List<String> result = questService.returnCategoriesByLanguage(language);
        return new ResponseEntity<List<String>>(result, HttpStatus.CREATED);
    }
}
