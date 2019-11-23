package com.leo.common.utils;

import com.leo.common.constant.StatusConst;
import com.leo.common.error.BusinessException;
import com.leo.common.result.EmBusinessResult;
import com.leo.common.result.StatusEnum;

/**
 * 数据状态工具
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:17
 */
public class StatusUtil {

    /** 逻辑删除语句 */
    public static final String SLICE_DELETE = " set status=" + StatusConst.DELETE + " WHERE id=?";

    /** 不等于逻辑删除条件语句 */
    public static final String NOT_DELETE = "status != " + StatusConst.DELETE;

    /**
     * 获取状态StatusEnum对象
     * @param param 状态字符参数
     */
    public static StatusEnum getStatusEnum(String param) throws BusinessException {
        try {
            return StatusEnum.valueOf(param.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(EmBusinessResult.STATUS_ERROR);

        }
    }
}
