package com.cesgroup.prison.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller统一异常处理
 * @author :
 * @date :
 */
//@ControllerAdvice
public class AllControllerAdvice {
    private static Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<String> errorHandler(Exception ex) {
        ex.printStackTrace();
        logger.error("接口出现严重异常：{}", ex.getMessage());
        return ResponseHelper.validationFailure("服务繁忙!");
    }

    /**
     * 捕捉UnauthorizedException
     * @return
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseModel<String> handleUnauthorized() {
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMITION);
    }
*/
    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
   /* @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseModel<String> handleShiroException(ShiroException e) {
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMITION);
    }*/

    /**
     * 捕捉BusinessException自定义抛出的异常
     * @return
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseModel handleBusinessException(BusinessException e) {
        if(e instanceof BusinessException) {
            logger.info("数据操作失败："+e.getMessage());
            return ResponseHelper.validationFailure("数据操作失败："+e.getMessage());
        }
        return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
    }*/

   /* @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TemplateInputException.class)
    @ResponseBody
    public ResponseModel<String> handleTemplateInputException(TemplateInputException e) {
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMITION);
    }
*/
    /*@ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ParamJsonException.class)
    @ResponseBody
    public ResponseModel<String> handleParamJsonException(Exception e) {
        if(e instanceof ParamJsonException) {
            logger.info("参数错误："+e.getMessage());
            return ResponseHelper.validationFailure("参数错误："+ e.getMessage());
        }
        return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
    }*/


}