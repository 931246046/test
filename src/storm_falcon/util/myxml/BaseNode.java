package storm_falcon.util.myxml;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Created by Storm_Falcon on 2015/10/30.
 * Super class of node
 */
abstract class BaseNode {

    protected static XmlProperty property;

    /**
     * �ڵ�����key
     */
    protected String name;

    /**
     * ��Σ����ڸ�ʽ����
     */
    protected int mLevel;

    /**
     * �����б�
     */
    @Nullable
    protected Map<String, String> attrMap = null;

    public void setProperty(XmlProperty property) { BaseNode.property = property; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���ؽڵ�ֵ
     * @return
     */
    public abstract Object getValue();

    /**
     * ���ؽڵ�ֵ����
     * @return
     */
    public abstract int getValueSize();

    public abstract void setValue(Object value);

    @Nullable
    public static BaseNode parseXml(String xml) { return null; }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        this.mLevel = level;
    }

    @Nullable
    public Map<String, String> getAttribute() {
        return attrMap;
    }

    public void setAttribute(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }

    /**
     * �������б�ת��string
     * @return
     */
    @NotNull
    protected String getAttributeString() {
        StringBuilder sb = new StringBuilder();
        if (attrMap != null && attrMap.size() != 0) {
            for (String attrKey : attrMap.keySet()) {
                String attrValue = attrMap.get(attrKey);
                sb.append(" ").append(attrKey).append("=\"").append(attrValue).append("\"");
            }
        }
        return sb.toString();
    }

    /**
     * ���ɿ�ͷ�Ʊ��
     * @param isStart true:��ʼ��ǩ��false:������ǩ
     * @return
     */
    @NotNull
    protected String getTableString(boolean isStart) {
        if (!property.isFormat) {
            return "";
        }
        if (!isStart && getValueSize() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mLevel; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }
}
