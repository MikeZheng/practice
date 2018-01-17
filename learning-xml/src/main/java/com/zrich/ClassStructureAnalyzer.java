package com.zrich;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class ClassStructureAnalyzer
{
    public static void main( String[] args ) throws JsonProcessingException {
        describeDeviceCategory();
        describeDeviceAttribute();
        describeDmProvider();

    }

    private static void describeDmProvider() throws JsonProcessingException {
        List<DmProvider> list = new ArrayList<>(1);
        DmProvider provider = new DmProvider();
        provider.setCategory(1);
        provider.setContact("010-25632");
        provider.setCreateTime(new Date());
        provider.setCreateUser("admin");
        provider.setUpdateTime(new Date());
        provider.setUpdateUser("admin");
        provider.setUuid("123123123");
        provider.setProviderName("海康");
        provider.setProviderCode("123123123");
        provider.setProviderDesc("安防");
        provider.setDeleteFlag((short) 1);
        provider.setCourtUuid("1212312123123");

        list.add(provider);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(provider);
        System.out.println(json);
        System.out.println(mapper.writeValueAsString(list));
    }

    private static void describeDeviceAttribute() throws JsonProcessingException {
        RequestDto<DeviceAttributeDto> requestDto = new RequestDto<>();
        DeviceAttributeDto deviceAttributeDto = new DeviceAttributeDto();
        deviceAttributeDto.setSourceSysId("egc-mdm-component");
        deviceAttributeDto.setTargetSysId("egc-mdm-component");
        deviceAttributeDto.setBusinessId("egc-mdm-component");

        deviceAttributeDto.setAttrCode("one");
        deviceAttributeDto.setAttrDataType("12l31j23");
        deviceAttributeDto.setAttrDesc("jkasfkjsdf");
        deviceAttributeDto.setAttrType("123123-1232");
        deviceAttributeDto.setUnitCode("2.0");
        deviceAttributeDto.setUnitDesc("1001");
        deviceAttributeDto.setUuid("123123123121");

        Header requestHeader = new Header();
        requestHeader.setBusinessId(deviceAttributeDto.getBusinessId());
        requestHeader.setSourceSysId(deviceAttributeDto.getSourceSysId());
        requestHeader.setTargetSysId(deviceAttributeDto.getTargetSysId());
        requestHeader.setCreateTimestamp(System.currentTimeMillis());
        requestHeader.setCharset("utf-8");
        requestHeader.setContentType("json");
        requestHeader.setExtAttributes(deviceAttributeDto.getExtAttributes());

        requestDto.setHeader(requestHeader);
        requestDto.setData(deviceAttributeDto);

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String json = mapper.writeValueAsString(requestDto);
        System.out.println(json);
    }

    private static void describeDeviceCategory() throws JsonProcessingException {
        RequestDto<DeviceCategoryDto> requestDto = new RequestDto<>();
        DeviceCategoryDto deviceCategoryDto = new DeviceCategoryDto();
        deviceCategoryDto.setSourceSysId("egc-mdm-component");
        deviceCategoryDto.setTargetSysId("egc-mdm-component");
        deviceCategoryDto.setBusinessId("egc-mdm-component");

        deviceCategoryDto.setTypeCode("one");
        deviceCategoryDto.setTypeName("12l31j23");
        deviceCategoryDto.setTypeDesc("jkasfkjsdf");
        deviceCategoryDto.setTypeModel("123123-1232");
        deviceCategoryDto.setHardwareVersion("1.0");
        deviceCategoryDto.setSoftwareVersion("2.0");
        deviceCategoryDto.setProviderCode("1001");

        Header requestHeader = new Header();
        requestHeader.setBusinessId(deviceCategoryDto.getBusinessId());
        requestHeader.setSourceSysId(deviceCategoryDto.getSourceSysId());
        requestHeader.setTargetSysId(deviceCategoryDto.getTargetSysId());
        requestHeader.setCreateTimestamp(System.currentTimeMillis());
        requestHeader.setCharset("utf-8");
        requestHeader.setContentType("json");
        requestHeader.setExtAttributes(deviceCategoryDto.getExtAttributes());

        requestDto.setHeader(requestHeader);
        requestDto.setData(deviceCategoryDto);

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        String json = mapper.writeValueAsString(requestDto);
        System.out.println(json);
    }
}
