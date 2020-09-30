package ir.piana.dev.sqlrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.piana.dev.uploadrest.StorageService;
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/ajax")
public class AjaxController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SqlQueryService sqlService;

    @Autowired
    private ActionProperties actionProperties;

    @Autowired
    private ParameterParser parameterParser;

    @Autowired
    private StorageService storageService;

    private static ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/serve", method = RequestMethod.POST,
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
                        BiFunction<HttpServletRequest, Map<String, Object>, ResponseEntity> field = bean.getField(activityHeader);
                        return field.apply(request, body);
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
                Map containerMap = new LinkedHashMap();
                Object[] params = null;
                if(activity.getSql().getParams() != null && !activity.getSql().getParams().isEmpty()) {
                    String[] split = activity.getSql().getParams().split(",");
                    params = new Object[split.length];
                    for(String s : split) {
                        String[] split1 = s.split("=");
                        if(split1[1].equals("!")) {
                            params[Integer.valueOf(split1[0]) - 1] = AjaxReplaceType.ITS_ID;
                        } else if(split1[1].startsWith("%") || split1[1].endsWith("%")) {
                            String begin = split1[1].startsWith("%") ? "%" : "";
                            String end = split1[1].endsWith("%") ? "%" : "";
                            String key = split1[1];
                            if (!begin.isEmpty())
                                key = split1[1].substring(1);
                            if (!end.isEmpty())
                                key = key.substring(0, key.length() - 1);
                            params[Integer.valueOf(split1[0]) - 1] = begin + body.get(key) + end;
                        } else if(split1[1].startsWith("@")) {
                            String[] substring = split1[1].substring(1).split("\\*");
                            String index = substring[0];
                            String[] split2 = substring[1].split("&");
                            String base64 = parameterParser.parse(split2[0], request, body);
                            Integer rotate = parameterParser.parse(split2[1], request, body);
                            String group = parameterParser.parse(split2[2], request, body);
                            String imageSrc = storageService.store(base64, group, rotate);
                            params[Integer.valueOf(split1[0]) - 1] = imageSrc;
                            containerMap.put(index, imageSrc);
                        } else {
                            params[Integer.valueOf(split1[0]) - 1] = body.get(split1[1]);
                        }
                    }
                } else {
                    params = new Object[0];
                }
                Object result = sqlService.execute(activity.getSql(), params);
                if(result instanceof AjaxReplaceType && result == AjaxReplaceType.NO_RESULT)
                    return notFound.apply(request);
                else {
                    HttpHeaders responseHeaders = new HttpHeaders();
                    if(activity.getSql() != null && activity.getSql().getType().equalsIgnoreCase("insert") && activity.getSql().getResult() != null) {
                        String[] split = activity.getSql().getResult().split(",");

                    }
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
        public BiFunction<HttpServletRequest, Map<String, Object>, ResponseEntity> getField(String fieldName)
                throws NoSuchFieldException, IllegalAccessException {
            Field field = this.getClass().getField(fieldName);
            return (BiFunction<HttpServletRequest, Map<String, Object>, ResponseEntity>) field.get(this);
        }
    }

    public static enum AjaxReplaceType {
        NO_RESULT,
        UPDATED,
        ITS_ID
    }
}
