package com.sie.iot.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.sie.iot.common.bean.PaginationRequestData;
import com.sie.iot.common.bean.RequestData;
import com.sie.iot.common.iotenum.StatusCodeEnum;
import com.sie.iot.common.model.inter.IBaseCommon;
import com.sie.iot.common.services.CommonAbstractService;
import com.sie.iot.demo.bean.DemoDeptBean;
import com.sie.iot.demo.bean.DemoUserBean;
import com.sie.iot.demo.model.entities.DemoUserEntity_HI;
import com.sie.iot.demo.model.entities.readonly.DemoDeptEntity_RO;
import com.sie.iot.demo.model.entities.readonly.DemoUserEntity_RO;
import com.sie.iot.demo.model.inter.IDemoUser;
import com.siefw.hibernate.core.paging.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.sie.iot.common.bean.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/demo-user")
@Api(value = "人员管理", tags = {"人员管理"})
public class DemoUserController extends CommonAbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoUserController.class);
    
    @Autowired
    private IDemoUser demoUserServer;
    
    @Override
    public IBaseCommon<?> getBaseCommonServer() {
        return this.demoUserServer;
    }
    
    /**
     * 通过人员id获取人员
     * @param id
     * @return 
     */
    @ApiOperation(value = "通过人员id获取人员", notes = "通过人员id获取人员", httpMethod = "GET")
    @GetMapping(value = "/get-id")
    public ResponseData findById(Long id) {

        DemoUserEntity_HI demoUserEntity_HI = null;
        try {
            demoUserEntity_HI = demoUserServer.findById(id);
            if (demoUserEntity_HI == null){
                return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "查无此人");
            }
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoUserEntity_HI, "查询成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "操作失败：" + e.getMessage());
        }
    }
    
    @ApiOperation(value = "获取人员列表", notes = "获取人员列表", httpMethod = "POST")
    @PostMapping(value = "/find-pagination")
    public ResponseData findByPage(@RequestBody PaginationRequestData<DemoUserBean> paginationRequestData){
        Integer pageIndex = paginationRequestData.getPageIndex();
        Integer pageRows = paginationRequestData.getPageRows();
        DemoUserBean demoUserBean = paginationRequestData.getParams();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean));
        try{
            Pagination<DemoUserEntity_HI> pagination = demoUserServer.findPagination(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pagination, "分页查询成功"); 
        }catch (Exception e){
            LOGGER.error(" find - pagination error:" + e);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "分页查询出错:" + e.getMessage());
        }
    }
    
    @ApiOperation(value = "保存", notes = "保存", httpMethod = "POST")
    @PostMapping(value = "/save")
    public ResponseData save(@RequestBody RequestData<DemoUserBean> requestData){
        DemoUserBean demoUserBean = requestData.getParams();
        //校验
        if (StrUtil.isNotBlank(demoUserBean.getUserName())) {
            //判断用户名是否重复
            if (demoUserServer.checkUserName(demoUserBean.getUserName())){
                return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "用户名已经存在");
            }
        }else {
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "用户名不能为空");
        }
        DemoUserEntity_HI demoUserEntity_HI = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean), DemoUserEntity_HI.class);
        
        //固定
        demoUserEntity_HI.setOperatorUserId(1L);
        try{
            demoUserServer.save(demoUserEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoUserEntity_HI, "保存成功");
        }catch (Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 保存失败【" + e.getMessage() + "】");
        }
        
    }

    @ApiOperation(value = "删除",notes = "删除 ",httpMethod ="GET" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "人员ID", paramType = "query", dataType = "long",required = true),
    })
    @GetMapping(value = "/delete-id")
    public ResponseData deleteById(@RequestParam Long id){
        try{
            demoUserServer.deleteById(id);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), "删除成功");
        }catch(Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "删除失败，" + e.getMessage());
        }
    }

    @ApiOperation(value = "更新",notes = "更新 ",httpMethod ="POST" )
    @PostMapping(value="/update")
    public ResponseData update(@RequestBody RequestData<DemoUserBean> requestData){
        DemoUserBean demoUserBean = requestData.getParams();
        DemoUserEntity_HI demoUserEntity_HI = demoUserServer.getById(demoUserBean.getUserId());
        if(null != demoUserEntity_HI){
            //判断人员名称是否重复
            BeanUtil.copyProperties(demoUserBean,demoUserEntity_HI);
            //固定
            demoUserEntity_HI.setOperatorUserId(1L);
        }else{
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 您所更新的数据不存在");
        }
        try{
            demoUserServer.update(demoUserEntity_HI);
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), demoUserEntity_HI," 更新成功");
        }catch (Exception e){
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), " 更新失败【" + e.getMessage() + "】");
        }
    }

    @ApiOperation(value = "获取人员所在部门", notes = "获取人员所在部门" , httpMethod = "POST")
    @PostMapping(value="/find-pagination-dept")
    public ResponseData findRoByUser (@RequestBody PaginationRequestData<DemoUserBean> paginationRequestData) {
        Integer pageIndex = paginationRequestData.getPageIndex();
        Integer pageRows = paginationRequestData.getPageRows();
        DemoUserBean demoUserBean = paginationRequestData.getParams();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(demoUserBean));
        try {
            Pagination<DemoUserEntity_RO> pagination = demoUserServer.findRoUser(jsonObject, pageIndex, pageRows, paginationRequestData.getOrderByBean());
            return new ResponseData(StatusCodeEnum.SUCCESS_CODE.getStatusCode(), pagination, " 分页查询成功");
        } catch (Exception e) {
            LOGGER.error(" find - pagination error:" + e);
            return new ResponseData(StatusCodeEnum.ERROR_CODE.getStatusCode(), "分页查询出错:" + e.getMessage());
        }
    }
}
