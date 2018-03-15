package com.zrich;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.base.BaseCourtCommon;
import com.base.BaseCourtConditionDto;
import com.base.BaseCourtDto;
import com.base.BaseHouseCommon;
import com.base.BaseHouseConditionDto;
import com.base.BaseHouseDto;
import com.base.BaseHouseUserRelDto;
import com.base.BaseOrgCommon;
import com.base.BaseOrgConditionDto;
import com.base.BaseOrgDto;
import com.base.BaseOrgTreeDto;
import com.base.BaseUserCommon;
import com.base.BaseUserConditionDto;
import com.base.BaseUserDto;
import com.base.DictCodeDto;
import com.base.DictCodeTypeDto;
import com.device.DeviceAttributeConditionDto;
import com.device.DeviceAttributeDomainDto;
import com.device.DeviceAttributeMappingBatchDto;
import com.device.DeviceAttributeMappingDto;
import com.device.DeviceCategoryConditionDto;
import com.device.DeviceCategoryFullDto;
import com.device.DeviceCategorySimpleDto;
import com.device.DistributionRequestDto;
import com.device.DistributionResponseDto;
import com.device.ProviderConditionDto;
import com.device.ProviderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Hello world!
 *
 */
public class ClassStructureAnalyzer
{
    public static void main( String[] args ) throws JsonProcessingException {
//        describeDeviceCategory();
//        describeDeviceAttribute();
//        describeDmProvider();
//        printBase();
        printDevice();

    }

    private static void printBase() {
        printSetMethod(BaseCourtCommon.class);
        printSetMethod(BaseCourtConditionDto.class);
        printSetMethod(BaseCourtDto.class);
        printSetMethod(BaseHouseCommon.class);
        printSetMethod(BaseHouseConditionDto.class);
        printSetMethod(BaseHouseDto.class);
        printSetMethod(BaseHouseUserRelDto.class);
        printSetMethod(BaseOrgCommon.class);
        printSetMethod(BaseOrgConditionDto.class);
        printSetMethod(BaseOrgDto.class);
        printSetMethod(BaseOrgTreeDto.class);
        printSetMethod(BaseUserCommon.class);
        printSetMethod(BaseUserConditionDto.class);
        printSetMethod(BaseUserDto.class);
        printSetMethod(DictCodeDto.class);
        printSetMethod(DictCodeTypeDto.class);
    }

    private static void printDevice() {
        printSetMethod(DeviceAttributeConditionDto.class);
        printSetMethod(DeviceAttributeDomainDto.class);
        printSetMethod(DeviceAttributeDto.class);
        printSetMethod(DeviceAttributeMappingBatchDto.class);
        printSetMethod(DeviceAttributeMappingDto.class);
        printSetMethod(DeviceCategoryConditionDto.class);
        printSetMethod(com.device.DeviceCategoryDto.class);
        printSetMethod(DeviceCategoryFullDto.class);
        printSetMethod(DeviceCategorySimpleDto.class);
        printSetMethod(DistributionRequestDto.class);
        printSetMethod(DistributionResponseDto.class);
        printSetMethod(ProviderConditionDto.class);
        printSetMethod(ProviderDto.class);
    }

    public static void printSetMethod(Class clazz) {
        StringBuilder builder = new StringBuilder();
        String className = clazz.getSimpleName();
        String variableName = className.substring(0, 1).toLowerCase()+className.substring(1);
        String blank = " ";
        builder.append("@Test").append("\r\n");
        builder.append("public void test").append(className).append("(){").append("\r\n");
        builder.append(className).append(blank).append(variableName)
                .append("=").append("new ").append(className).append("();").append("\r\n");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.getName().contains("serialVersionUID")) {
                continue;
            }
            builder.append(variableName).append(".");
            builder.append("set").append(field.getName().substring(0,1).toUpperCase())
                    .append(field.getName().substring(1))
            .append("(");
             if (field.getType().isAssignableFrom(Short.class)){
                builder.append("(short)1");
            } else if (field.getType().isAssignableFrom(Integer.class)){
                 builder.append("2");
             } else if (field.getType().isAssignableFrom(Date.class)) {
                 builder.append("new Date()");
             } else if (field.getType().isAssignableFrom(BigDecimal.class)) {
                builder.append("new BigDecimal(\"1\")");

            } else {
                 builder.append("\"").append(field.getName()).append("\"");
             }
            builder.append(");").append("\r\n");
             builder.append("logger.info(");
            builder.append(variableName).append(".");
            builder.append("get").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1)).append("().toString());").append("\r\n");
        }
        builder.append("logger.info(").append(variableName).append(".toString());").append("\r\n");
        builder.append("logger.info(String.valueOf(").append(variableName).append(".hashCode()));").append("\r\n");
        builder.append("logger.info(String.valueOf(").append(variableName).append(".equals(new Object())));").append("\r\n");
        builder.append("}");
        System.out.println(builder.toString());
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
