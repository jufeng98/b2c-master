package com.javamaster.b2c.cloud.test.learn.java.test;

import cn.com.bluemoon.app.dubbo.service.SysDictDubboService;
import cn.com.bluemoon.common.util.MD5Implementor;
import cn.com.bluemoon.crmbp.service.toSfa.SfaDubboService;
import cn.com.bluemoon.dubbo.kdn.SubscribeService;
import cn.com.bluemoon.file.dubbo.service.ImageService;
import cn.com.bluemoon.mall.activity.dubbo.dto.UserCouponPackageManageDto;
import cn.com.bluemoon.mall.activity.dubbo.service.MallPromotionActivityBaseService;
import cn.com.bluemoon.mall.activity.dubbo.service.UserCouponPackageManageService;
import cn.com.bluemoon.mall.activity.dubbo.vo.CartItemVo;
import cn.com.bluemoon.mall.order.dubbo.service.MallWashOrderService;
import cn.com.bluemoon.mall.pay.dubbo.service.MallPayApiService;
import cn.com.bluemoon.mall.user.dubbo.enums.MatchType;
import cn.com.bluemoon.mall.user.dubbo.service.UserAddressService;
import cn.com.bluemoon.mall.user.dubbo.service.UserService;
import cn.com.bluemoon.mallorder.api.serviceorder.dto.WashOrderUpdateDto;
import cn.com.bluemoon.mallorder.api.serviceorder.dubbo.IMHServiceOrderService;
import cn.com.bluemoon.mallorder.api.userorder.enums.UserOrderStatusEnum;
import cn.com.bluemoon.mallorder.common.enmu.OrderStatusConstants;
import cn.com.bluemoon.mallwash.order.dubbo.service.WashOrderService;
import cn.com.bluemoon.moonmall.item.dubbo.service.MoonMallItemService;
import cn.com.bluemoon.moonmall.order.dubbo.service.MoonMallOrderService;
import cn.com.bluemoon.moonmall.shoppingcart.dubbo.service.MoonMallShoppingCartDubboService;
import cn.com.bluemoon.service.common.service.RegionService;
import cn.com.bluemoon.service.customizingorder.api.CustomizingOrderDubboService;
import cn.com.bluemoon.service.emp.api.EmpDubboService;
import cn.com.bluemoon.service.emp.api.MapService;
import cn.com.bluemoon.service.mallcrm.service.message.MesssagePushService;
import cn.com.bluemoon.service.portal.service.PortalAppService;
import cn.com.bluemoon.service.station.api.DubboCommonService;
import cn.com.bluemoon.service.user.service.SsoService;
import cn.com.bluemoon.training.dubbo.api.CourseBaseApiService;
import cn.com.bluemoon.wash.dubbo.service.WashLevelTypeService;
import cn.com.bluemoon.wash.dubbo.service.WashPriceManageService;
import com.alibaba.fastjson.JSON;
import com.bluemoon.pf.map.enums.ApiTypeEnums;
import com.bluemoon.pf.map.sdk.dto.*;
import com.bluemoon.pf.map.sdk.enums.Coordsys;
import com.bluemoon.pf.map.service.BasicMapService;
import com.bluemoon.proxy.service.sms.EmailService;
import com.bluemoon.proxy.service.sms.SmsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.javamaster.b2c.cloud.test.learn.java.utils.DubboUtils;
import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.*;

/**
 * @author yudong
 * @date 2019/1/21
 */
@Slf4j
public class DubboTest {

    @AfterClass
    public static void exit() {
        System.exit(0);
    }

    @Test
    public void test() throws Exception {
        EmpDubboService service = DubboUtils.getService(EmpDubboService.class);
        Object resObj = service.getEmpInfoByNameCode("80555379");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test1() {
        UserService service = DubboUtils.getService(UserService.class, "1.0.0", getProp("Server.SERVER_1"));
        Object object = service.getUserInfoByUserId("U18110814354169025621");
        log.info(OMUtils.writeValueAsString(object));
        object = service.getUserBaseByMobile("18826483965");
        log.info(OMUtils.writeValueAsString(object));
        object = service.getUserBaseByMobile("18826483966");
        log.info(OMUtils.writeValueAsString(object));
    }

    @Test
    public void test2() throws Exception {
        SsoService service = DubboUtils.getService(SsoService.class, null);
        JSONObject object = new JSONObject();
        object.put("mobile", "18826483964");
        Object resObj = service.getUserInfoByMobile(object);
        log.info(OMUtils.writeValueAsString(resObj, true));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", "80546269");
        jsonObject.put("password", MD5Implementor.MD5Encode("qq123123"));
        String response = service.ssoLogin(jsonObject);
        log.info(response);
    }

    @Test
    public void test3() throws Exception {
        SsoService service = DubboUtils.getService(SsoService.class, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", "21fe6aadeb3561241e7e6aca60b5a757");
        Object resObj = service.checkToken(jsonObject);
        log.info(OMUtils.writeValueAsString(resObj, true));
        jsonObject.put("token", "23fe6aadeb3561241e7e6aca60b5a757");
        resObj = service.checkToken(jsonObject);
        log.info(OMUtils.writeValueAsString(resObj, true));
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("account", "80546269");
        resObj = service.getUserInfo(jsonObj);
        log.info("{}", resObj);
    }

    @Test
    public void test4() throws Exception {
        SsoService service = DubboUtils.getService(SsoService.class, null);
        JSONObject reqJsonObj = new JSONObject();
        reqJsonObj.put("token", "3163e6314b3265c5386b0477af298146");
        String string = service.getUserInfoByToken(reqJsonObj);
        JSONObject resObj = JSONObject.fromObject(string);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test5() {
        Object resObj = DubboUtils.invoke(UserService.class
                , "getUserInfoByUserId", Collections.singletonList("U18110814354169025621")
                , "1.0.0");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test8() {
        CourseBaseApiService service = DubboUtils.getService(CourseBaseApiService.class,
                null, "192.168.240.57:20881");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userCodeList", Arrays.asList(80516445, 80484450));
        jsonObject.put("courseName", "至尊产品培训编辑");
        Date start = new Calendar.Builder().setDate(2019, Calendar.APRIL, 1).build().getTime();
        Date end = DateUtils.addDays(start, 5);
        jsonObject.put("startTime", start.getTime());
        jsonObject.put("endTime", end.getTime());
        Object resObj = service.getStudentSignListByParams(jsonObject);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test9() {
        ImageService service = DubboUtils.getService(ImageService.class, "1.0.0");
        Object resObj = service.getImageVo("group1//M00/02/D7/wKjwDlzko0WASMxbAABOTuQ2LwA962.jpg", "fdfs");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test10() {
        UserService service = DubboUtils.getService(UserService.class, "1.0.0-yudong");
        Object resObj = service.getUserInfoByBlurMobile("3966", 1, 10, MatchType.BACK);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test11() {
        WashPriceManageService service = DubboUtils.getService(WashPriceManageService.class);
        Object resObj = service.getWashPriceManageByWashCode("65476543");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test12() {
        MallWashOrderService service = DubboUtils.getService(MallWashOrderService.class, "1.0.0-yudong");
        Object resObj = service.createMallOrderInfo(null);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test13() {
        WashOrderService service = DubboUtils.getService(WashOrderService.class, "1.0.0-yudong");
        Object resObj = service.getWashOrderPayInfos("U2003031940286000001");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test14() {
        UserAddressService service = DubboUtils.getService(UserAddressService.class, "1.0.0");
        Object resObj = service.getUserAddress("U190701150946235921", "", null);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test15() throws Exception {
        RegionService service = DubboUtils.getService(RegionService.class, null);
        Object resObj = service.selectRegionByCodeAndType("4401", "county");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test16() {
        WashLevelTypeService service = DubboUtils.getService(WashLevelTypeService.class, "1.0.0");
        Object resObj = service.getMallWashLevelTypeByParentId("0");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test17() throws Exception {
        MesssagePushService service = DubboUtils.getService(MesssagePushService.class, null);
        JSONObject json = new JSONObject();
        json.put("desc", "您有新的待派单洗衣订单，请及时处理！");     //推送内容描述
        json.put("staffNum", "80546269"); //要推送的人员编码
        json.put("title", "收衣派单信息提醒"); //推送标题

        JSONObject msg = new JSONObject(); //推送详细对象
        msg.put("source", "washingService"); //消息来源
        msg.put("msgId", "");        //具体的消息ID，可不填
        msg.put("view", "receive_clothes_manager"); //要推送的消息模块，与菜单代码匹配     如果是H5页面则    msg.put("view","h5"；
        msg.put("url", "");  //如果上面的view等于h5，则url项必填，写入的是菜单的url
        json.put("msg", msg);
        Object resObj = service.singleMsgPush(json);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test18() throws Exception {
        RegionService service = DubboUtils.getService(RegionService.class, null, getProp("SERVER_18"));
        Object resObj = service.getPointInfo("广东广州增城区凤凰岛一街凤凰苑");
        log.info(OMUtils.writeValueAsString(resObj, true));
        log.info(OMUtils.writeValueAsString(service.getAddressByGPS(0.0, 23.14365, 113.56129), true));
    }

    @Test
    public void test19() {
        DubboCommonService service = DubboUtils.getService(DubboCommonService.class, null, "127.0.0.1:20880");
        Object resObj = service.findWashCollectPointsArea("44", "4401", "440106", "", null);
        Object resObj1 = service.findWashCollectPointsArea("44", "4401", "440106", "", "五山路中公教育大厦");
        log.info(OMUtils.writeValueAsString(resObj, true));
        log.info(OMUtils.writeValueAsString(resObj1, true));
    }

    @Test
    public void test20() {

        WashPriceManageService service = DubboUtils.getService(WashPriceManageService.class, "1.0.0");
        Object resObj = service.getWashPriceManageByWashCode("010308");
        Object resObj1 = service.getWashPriceManageByWashCode("010215");
        log.info(OMUtils.writeValueAsString(resObj, true));
        log.info(OMUtils.writeValueAsString(resObj1, true));
    }

    @Test
    public void test21() {
        SmsService service = DubboUtils.getService(SmsService.class, "2.0.0");
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("mobileNo", "18826483963");
        jsonObj.put("content", "hello world");
        jsonObj.put("myAccount", "xyzx");
        jsonObj.put("myProjectName", "洗衣中心");
        Object resObj = service.send(jsonObj);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test22() {
        WashOrderService service = DubboUtils.getService(WashOrderService.class, "1.0.0", "127.0.0.1:21888");
        Object resObj = service.updatePayOperationType("U190701150946235921", "TW191126114108669852", 1);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test23() {
        CustomizingOrderDubboService service = DubboUtils.getService(CustomizingOrderDubboService.class, "1.0.0");
        Object resObj = service.findCustomizingOrderDetails("TW191128093529703502");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test24() {
        UserCouponPackageManageService service = DubboUtils.getService(UserCouponPackageManageService.class, "1.0.0");
        UserCouponPackageManageDto dto = new UserCouponPackageManageDto();
        dto.setPhone("18826483963");
        Object resObj = service.getUserCouponPackageManagePage(dto);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test25() {
        DubboCommonService service = DubboUtils.getService(DubboCommonService.class);
        Object resObj = service.findWashCollectPointsArea("44", "4401", "4440106", "", "五山路261号");
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test26() {
        BasicMapService service = DubboUtils.getService(BasicMapService.class, "1.0.0");
        Geoconv geoconv = new Geoconv();
        geoconv.setApiType(ApiTypeEnums.amap.name());
        geoconv.setFrom(Coordsys.GPSC);
        geoconv.setTo(Coordsys.GCJ02);
        Coordinates coordinates = new Coordinates();
        coordinates.setApiType(ApiTypeEnums.amap.name());
        coordinates.setLat(39.990475D);
        coordinates.setLng(116.481499D);
        List<Coordinates> coords = Lists.newArrayList(coordinates);
        geoconv.setCoords(coords);
        ResultBean<List<Coordinates>> resObj = service.geoconv(geoconv, ApiTypeEnums.amap);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test27() {
        BasicMapService service = DubboUtils.getService(BasicMapService.class, "1.0.0");
        Coordinates coordinates = new Coordinates();
        coordinates.setLng(113.346380);
        coordinates.setLat(23.147320);
        Object resObj = service.regeocoder(coordinates, ApiTypeEnums.amap);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test28() throws Exception {
        MapService mapService = DubboUtils.getService(MapService.class);
        Object resObj = mapService.getAddressByPoint(123.525270, 42.045748);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test29() {
        // MessageQueueService service = DubboUtils.getService(MessageQueueService.class, "1.3.6");
        // JSONObject json = new JSONObject();
        // json.put("clothesCode", "20200407hd001");
        // json.put("reportCode", "2004070003");
        // Object resObj = service.sendMessage("clothes_conmunication_notice", json.toString(), true);
        // log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test30() {
        EmailService service = DubboUtils.getService(EmailService.class);
        JSONObject json = new JSONObject();
        json.put("content", "测试邮件发送");
        json.put("to", "liangyudong@bluemoon.com.cn");
        json.put("from", "gzxyzx@bluemoon.com.cn");
        json.put("fromName", "洗衣服务系统");
        json.put("password", "A00000001a");
        json.put("subject", "系统异常提醒");
        json.put("username", "gzxyzx@bluemoon.com.cn");
        Object resObj = service.send(json);
        log.info(OMUtils.writeValueAsString(resObj, true));
    }

    @Test
    public void test31() {
        DubboCommonService service = DubboUtils.getService(DubboCommonService.class, "1.0.0-yudong");
        String jsonStrAddress = service.findWashCollectPointsArea("44", "4418", "441881", "", "英城街道23号");
        log.info(jsonStrAddress);
    }

    @Test
    public void test32() throws Exception {
        SfaDubboService service = DubboUtils.getService(SfaDubboService.class, null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("provinceCode", "");
        jsonObject.put("cityCode", "");
        jsonObject.put("countyCode", "");
        jsonObject.put("merchantCode", "3002249");
        jsonObject.put("merchantName", "");
        jsonObject.put("pageIndex", 0);
        jsonObject.put("pageSize", 20);
        jsonObject.put("method", "getUnRecord");
        @SuppressWarnings("ALL")
        Object res = service.commonDubbo(jsonObject);
        log.info("{}", res);
    }

    @Test
    public void test33() {
        SubscribeService service = DubboUtils.getService(SubscribeService.class, "1.0.0");
        Object res = service.subscribeTraces("246453112546", "SF", "washing");
        log.info(JSON.toJSONString(res));
    }

    @Test
    public void test34() throws Exception {
        PortalAppService service = DubboUtils.getService(PortalAppService.class, null);
        String resObj = service.getRoleListByUser("80546269");
        log.info(OMUtils.writeValueAsString(resObj, true));
        JsonNode roleListResJson = OMUtils.objectMapper.readValue(resObj, JsonNode.class);
        Iterator<JsonNode> iterable = roleListResJson.get("info").iterator();
        iterable.forEachRemaining(jsonNode -> log.info(jsonNode.asText()));
    }


    @Test
    public void test35() {
        DubboCommonService service = DubboUtils.getService(DubboCommonService.class, "1.0.0-yudong");
        Object resObj = service.updateOuterOrderStatus("TW200720142145054721", "OUTER_CANCEL");
        log.info("{}", resObj);
    }

    @Test
    public void test36() {
        WashOrderService service = DubboUtils.getService(WashOrderService.class, "1.0.0");
        Object resObj = service.getPayTypeInfoByOuterCode("TW200721144121203381");
        log.info("{}", resObj);
    }

    @Test
    public void test37() throws Exception {
        MoonMallOrderService service = DubboUtils.getService(MoonMallOrderService.class, "1.0.0");
        Object resObj = service.getOrderInfoByOuterCode("U190701150946235921", "TW200721144121203381");
        log.info("{}", resObj);
    }

    @Test
    public void test38() {
        MoonMallItemService service = DubboUtils.getService(MoonMallItemService.class, "1.0.0");
        Object resObj = service.getInfoByItemIds(Arrays.asList("i200530081626087961", "i200130002702861351"), true);
        log.info("{}", resObj);
    }

    @Test
    public void test39() {
        MoonMallShoppingCartDubboService service = DubboUtils.getService(MoonMallShoppingCartDubboService.class, "1.0.0-yudong");
        Object resObj = service.getCartProductList("U18101210443058835251", "tick");
        log.info("{}", resObj);
    }

    @Test
    public void test40() throws Exception {
        MallPromotionActivityBaseService service = DubboUtils.getService(MallPromotionActivityBaseService.class, "1.0.0");
        List<CartItemVo> cartItemVos = new ArrayList<>();
        CartItemVo cartItemVo = new CartItemVo();
        cartItemVo.setItemId("i200915123609471341");
        cartItemVo.setSku("95346654");
        cartItemVo.setNum(1);
        cartItemVo.setStatus("on");
        cartItemVo.setState("tick");
        Object resObj = service.getDefaultUsableCoupon("U18101210443058835251", cartItemVos, "", "washMall", "ITEM", "");
        log.info("{}", resObj);
    }

    @Test
    public void test41() {
        SysDictDubboService service = DubboUtils.getService(SysDictDubboService.class, "1.0.0");
        Object resObj = service.findDictByType("MallItemPostStatus");
        log.info("{}", resObj);
    }

    @Test
    public void test42() {
        SysDictDubboService service = DubboUtils.retrieveService(SysDictDubboService.class, "1.0.0", null);
        Object resObj = service.findDictByType("MallItemPostStatus");
        log.info("{}", resObj);
    }

    @Test
    public void test43() {
        MallPayApiService service = DubboUtils.retrieveService(MallPayApiService.class, "1.0.0", null);
        Object resObj = service.getPaymentInfo("U2009212011262300183", Collections.singletonList("C2009212048230000024"));
        log.info("{}", resObj);
    }

    @Test
    public void test44() {
        IMHServiceOrderService service = DubboUtils.retrieveService(IMHServiceOrderService.class, "1.0.0", null);
        WashOrderUpdateDto washOrderUpdateDto = new WashOrderUpdateDto();

        // List<ProductStatusResp> productStatusResps = Lists.newArrayList();
        // for (int i = 0; i < 2; i++) {
        //     ProductStatusResp productStatusResp = new ProductStatusResp();
        //     productStatusResp.setProductStatus(ServiceOrderProductStatusEnum.PACKAGED.getCode());
        //     productStatusResp.setId(83994L + i);
        //     productStatusResps.add(productStatusResp);
        // }
        // washOrderUpdateDto.setProductStatusList(productStatusResps);

        // washOrderUpdateDto.setServiceOrderCode();
        // washOrderUpdateDto.setServiceOrderStatus();
        // washOrderUpdateDto.setOrderStatus(OrderStatusConstants.ON_ITS_WAY_TO_WASH);
        washOrderUpdateDto.setOrderStatus(OrderStatusConstants.RETURNING_CLOTHES);
        // washOrderUpdateDto.setUserOrderStatus(UserOrderStatusEnum.SEND_WASHING.getCode());
        washOrderUpdateDto.setUserOrderStatus(UserOrderStatusEnum.BACKING.getCode());

    }

}
