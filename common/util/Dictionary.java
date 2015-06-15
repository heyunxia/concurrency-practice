package common.util;

/**
 * @author heyunxia.
 * @Description 字典数据，格式
 *
[
{"id": "1", "groupName": "颜色", "dictKey": "1", "dictValue": "红色"},
{"id": "2", "groupName": "颜色", "dictKey": "2", "dictValue": "橙色"},
{"id": "3", "groupName": "颜色", "dictKey": "3", "dictValue": "黄色"},
{"id": "4", "groupName": "颜色", "dictKey": "4", "dictValue": "绿色"},
{"id": "5", "groupName": "品牌", "dictKey": "1", "dictValue": "Li-Ning"},
{"id": "6", "groupName": "品牌", "dictKey": "2", "dictValue": "Anta"},
{"id": "7", "groupName": "品牌", "dictKey": "3", "dictValue": "361度"}
]
 * @time 2015/6/15 18:15
 */
public class Dictionary {

    /**
     * 主键ID
     */
    public String id;
    /**
     * 字典组
     */
    public String groupName;
    /**
     * 字典key
     */
    public String dictKey;
    /**
     * 字典value
     */
    public String dictValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }


}
