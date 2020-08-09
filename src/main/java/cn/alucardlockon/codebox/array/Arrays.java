package cn.alucardlockon.codebox.array;

public class Arrays {

    /**
     * 判断数组是否为null或空
     * @return 对象是否为空
     */
    public static boolean isEmpty(Object[] obj){
        return obj==null || obj.length==0;
    }


    /**
     * 判断数组是否不为null或空
     * @return 对象是否不为空
     */
    public static boolean isNotEmpty(Object[] obj){
        return !isEmpty(obj);
    }

}
