package ir.piana.dev.sqlrest;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

//@Profile({ "production"})
@ConfigurationProperties(prefix = "app.service")
public class ActionProperties {

    Map<String, Map<String, Activity>> actions;
//    List<Activity> actions;

    public Map<String, Map<String, Activity>> getActions() {
        return actions;
    }

    public void setActions(Map<String, Map<String, Activity>> actions) {
        this.actions = actions;
    }


    //    public static class Actions {
//        private List<Action> actions;
//
//        public Actions() {
//        }
//
//        public List<Action> getActions() {
//            return actions;
//        }
//
//        public void setActions(List<Action> actions) {
//            this.actions = actions;
//        }
//    }

    public static class Activity {
        private String function;
        private SQL sql;

        public Activity() {
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }

        public SQL getSql() {
            return sql;
        }

        public void setSql(SQL sql) {
            this.sql = sql;
        }
    }

    public static class SQL {
        private String type;
        private String query;
        private String resultType;
        private String sequenceName;
        private String params;
        private String result;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public String getSequenceName() {
            return sequenceName;
        }

        public void setSequenceName(String sequenceName) {
            this.sequenceName = sequenceName;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
