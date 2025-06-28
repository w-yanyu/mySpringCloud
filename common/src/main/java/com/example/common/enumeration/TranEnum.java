package com.example.common.enumeration;

/**
 * @ClassName TranEnum
 * @Author yanyu
 * @Date 2025/2/1 14:14
 * @Version 1.0
 * @Description TODO
 **/
public class TranEnum {

    public enum TimerStatus{
        /**
         * 启动
         */
        START,

        /**
         * 调度中
         */
        SCHEDULING,

        /**
         * 停止中
         */
        STOPPING,

        /**
         * 停止
         */
        STOPPED,

        /**
         * 删除
         */
        DELETE
        ;
    }
}
