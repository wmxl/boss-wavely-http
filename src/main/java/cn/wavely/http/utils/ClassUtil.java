package cn.wavely.http.utils;

/**
 * @author chenglong
 * @create 2022-07-06 17:20
 * @desc
 **/
public class ClassUtil {

    public static boolean isBaseClass(Object obj) {
        if (obj instanceof Integer) {
            return true;
        } else if (obj instanceof Long) {
            return true;
        } else if (obj instanceof Short) {
            return true;
        } else if (obj instanceof Double) {
            return true;
        } else if (obj instanceof Byte) {
            return true;
        } else if (obj instanceof Character) {
            return true;
        } else if (obj instanceof Boolean) {
            return true;
        } else if (obj instanceof String) {
            return true;
        }
        return false;
    }
}
