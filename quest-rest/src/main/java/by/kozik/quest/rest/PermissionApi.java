package by.kozik.quest.rest;

import by.kozik.quest.bean.FormActionBean;
import by.kozik.quest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge on 09.02.2017.
 */
@RestController
@RequestMapping(value = "/rest/admin/authorities", produces = "application/json")
public class PermissionApi {

    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "/{role_name}",method = RequestMethod.GET)
    public ResponseEntity<List<FormActionBean>> getPermissions(@PathVariable String role_name) {
        List<FormActionBean> permissions = new ArrayList<>();
        if ("all".equals(role_name)) {
            permissions = permissionService.returnNameListForJS();
        } else {
            permissions = permissionService.returnNameListForJS(role_name);
        }
        return new ResponseEntity<List<FormActionBean>>(permissions, HttpStatus.CREATED);
    }
}
