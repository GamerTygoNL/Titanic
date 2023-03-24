package cc.noxiuam.titanic.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListUtil {

    public boolean isLast(List<?> list, Object object) {
        return list.indexOf(object) == list.size() - 1;
    }

}
