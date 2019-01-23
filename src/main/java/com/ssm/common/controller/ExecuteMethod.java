package com.ssm.common.controller;

import com.ssm.common.exception.ServiceException;
import com.ssm.common.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public abstract class ExecuteMethod {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteMethod.class);

    public abstract Object exeMethod();

    public RESTUIModel execute(RESTUIModel RESTUIModel, HttpServletResponse response) {
        try {
            RESTUIModel.setData(exeMethod());
            response.setStatus(HttpStatus.OK.value());
            RESTUIModel.setStatesCode("SSYS000000");
            RESTUIModel.setStatesDesc("操作成功");
        } catch (ValidateException ve) {
            logger.error(ve.getMessageByLocale(Locale.ENGLISH));
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            RESTUIModel.setStatesCode(ve.getCode());
            RESTUIModel.setStatesDesc(ve.getMessageByLocale(Locale.ENGLISH));
        } catch (ServiceException se) {
            logger.error(se.getMessageByLocale(Locale.ENGLISH), se);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            RESTUIModel.setStatesCode(se.getCode());
            RESTUIModel.setStatesDesc(se.getMessageByLocale(Locale.ENGLISH));
        } catch (Exception e) {
            logger.error("系统异常", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            RESTUIModel.setStatesCode("ESYS000001");
            RESTUIModel.setStatesDesc("系统异常");
        }
        return RESTUIModel;
    }

}
