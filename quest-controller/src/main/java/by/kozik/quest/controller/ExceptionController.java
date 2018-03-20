package by.kozik.quest.controller;

import by.kozik.quest.exception.WrongParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Serge_Kozik on 3/2/2017.
 */

@ControllerAdvice
public class ExceptionController {
    public static final String DEFAULT_ERROR_VIEW = "custom-error.page";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = by.kozik.quest.exception.MissingParameterInSessionException.class)
    public ModelAndView nullAttributeHandler(HttpServletRequest request, Exception e, Locale locale) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("error_message",messageSource.getMessage("message.label.error.session-nullparameter",null,locale));
        mav.addObject("exception_object",e);
        return mav;
    }

    @ExceptionHandler(value = by.kozik.quest.exception.SaveBeanException.class)
    public ModelAndView saveBeanHandler(HttpServletRequest request,Exception e, Locale locale) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("error_message",messageSource.getMessage("message.error.database-save",null,locale));
        mav.addObject("exception_object",e);
        return mav;
    }

    @ExceptionHandler(value = by.kozik.quest.exception.BeanCreateException.class)
    public ModelAndView createBeanHandler(HttpServletRequest request,Exception e, Locale locale) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("error_message", messageSource.getMessage("message.label.error.bean-creation", null, locale));
        mav.addObject("exception_object", e);
        return mav;
    }

    @ExceptionHandler(value = WrongParameterException.class)
    public ModelAndView requestParameterHandler(HttpServletRequest request,Exception e, Locale locale) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
        mav.addObject("error_message", messageSource.getMessage("message.label.error.request-parameter", null, locale));
        mav.addObject("exception_object", e);
        return mav;
    }
}
