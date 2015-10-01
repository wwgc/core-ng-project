package core.framework.impl.template;

import core.framework.api.util.Lists;

import java.util.List;

/**
 * @author neo
 */
public class TestModel {
    public String stringField;
    public Integer numberField;
    public List<String> items = Lists.newArrayList();
    public List<TestModelChild> children = Lists.newArrayList();
    public String htmlField;

    public Integer addToNumberField() {
        return numberField + 100;
    }

    public String appendToStringField(String postfix) {
        return stringField + postfix;
    }

    public Boolean booleanMethod() {
        return true;
    }
}
