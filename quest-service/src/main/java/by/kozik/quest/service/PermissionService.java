package by.kozik.quest.service;

//import by.kozik.quest.bean.FormActionBean;

import by.kozik.quest.bean.FormActionBean;

import java.util.List;

/**
 * Created by Serge on 09.02.2017.
 */
public interface PermissionService {

    public List<FormActionBean> returnNameListForJS(String roleName);

    public List<FormActionBean> returnNameListForJS();

    public List<String> returnAllNames();

}