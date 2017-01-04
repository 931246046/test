package storm_falcon.util.myxml;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by Storm_Falcon on 2015/10/30.
 * myxml element.
 */
public class Node extends BaseNode {

    /**
     * �ڵ�ֵ
     */
    @NotNull
    private List<BaseNode> value = new LinkedList<>();

    public Node(String name) {
        super.name = name;
    }

    public void add(BaseNode node) {
        value.add(node);
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getTableString(true));

        //�ڵ�����key
        sb.append("<").append(name);

        //�����б�
        sb.append(getAttributeString());
        sb.append(">");

        if (property.isFormat && value.size() != 0) {
            sb.append("\n");
        }

        for (BaseNode node : value) {
            sb.append(node.toString());
        }

        sb.append(getTableString(false));
        sb.append("</").append(name).append(">");

        //��β����
        if (property.isFormat) {
            sb.append("\n");
        }

        return sb.toString();
    }

    @NotNull
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Object value) {
        //noinspection unchecked
        this.value = (List<BaseNode>) value;
    }

    @Override
    public int getValueSize() {
        return value.size();
    }
}
