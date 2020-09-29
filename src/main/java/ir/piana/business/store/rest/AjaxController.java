package ir.piana.business.store.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.piana.business.store.action.ActionProperties;
import ir.piana.business.store.service.sql.SqlQueryService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class AjaxController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SqlQueryService sqlService;

    @Autowired
    private ActionProperties actionProperties;

    private static ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/ajax", method = RequestMethod.POST,
            consumes = "application/json; charset=utf8",
            produces = "application/json; charset=utf8")
    public @ResponseBody
    ResponseEntity handleAjax(HttpServletRequest request,
                              @RequestBody Map<String, Object> body) {
        String actionHeader = (String) request.getHeader("action");
        String activityHeader = (String) request.getHeader("activity");
        if(actionProperties.getActions().containsKey(actionHeader) && actionProperties.getActions().get(actionHeader).containsKey(activityHeader)) {
            ActionProperties.Activity activity = actionProperties.getActions().get(actionHeader).get(activityHeader);
            if (activity.getFunction() != null && !activity.getFunction().isEmpty()) {
                try {
                    Action bean = (Action) applicationContext.getBean(actionHeader);
                    if (activityHeader != null && !activityHeader.isEmpty()) {
                        Function<HttpServletRequest, ResponseEntity> field = bean.getField(activityHeader);
                        return field.apply(request);
                    }
                } catch (NoSuchFieldException e){
                    e.printStackTrace();
                    return internalServerError.apply(request);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return internalServerError.apply(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    return internalServerError.apply(request);
                }
            } else if(activity.getSql() != null) {
                Object[] params = null;
                if(activity.getSql().getParams() != null && !activity.getSql().getParams().isEmpty()) {
                    String[] split = activity.getSql().getParams().split(",");
                    params = new Object[split.length];
                    for(String s : split) {
                        String[] split1 = s.split("=");
                        params[Integer.valueOf(split1[0]) - 1] = split1[1].equals("!") ?
                                AjaxReplaceType.ITS_ID : body.get(split1[1]);
                    }
                } else {
                    params = new Object[0];
                }
                Object result = sqlService.execute(activity.getSql(), params);
                if(result instanceof AjaxReplaceType && result == AjaxReplaceType.NO_RESULT)
                    return notFound.apply(request);
                else {
                    HttpHeaders responseHeaders = new HttpHeaders();
                    return ResponseEntity.ok(result);
                }
            }
        }

        return notFound.apply(request);
    }

    Function<HttpServletRequest, ResponseEntity> notFound = (r) -> {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Not Fund", responseHeaders, HttpStatus.NOT_FOUND);
    };

    Function<HttpServletRequest, ResponseEntity> notImplement = (r) -> {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Not Implemented", responseHeaders, HttpStatus.NOT_IMPLEMENTED);
    };

    Function<HttpServletRequest, ResponseEntity> internalServerError = (r) -> {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<String>("Internal Server Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    };

    public static String getTemplate(String vueTemplate) {
        vueTemplate = Arrays.stream(vueTemplate.split("\r\n")).map(line -> "\"".concat(line).concat("\" +\r\n "))
                .collect(Collectors.joining( ));
        return vueTemplate.substring(0, vueTemplate.lastIndexOf("+"));
    }

    public static <T> T getDto(InputStream inputStream, Class<T> dtoType)
            throws IOException {
        String s = IOUtils.toString(inputStream);
        T t = jsonMapper.readValue(s, dtoType);
        return t;
    }

    public static abstract class Action {
        public Function<HttpServletRequest, ResponseEntity> getField(String fieldName)
                throws NoSuchFieldException, IllegalAccessException {
            Field field = this.getClass().getField(fieldName);
            return (Function<HttpServletRequest, ResponseEntity>) field.get(this);
        }
    }

    public static enum AjaxReplaceType {
        NO_RESULT,
        ITS_ID
    }
}
