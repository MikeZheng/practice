package com.zrich;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2017/7/24.
 */
public class BeanUtils {
    /**
     * ��ȡ����ָ�����Ƶ����ԣ�֧�ֶ�㼶��
     *
     * @param targetClass
     *            ��
     * @param fieldName
     *            ������
     * @return ���ض�Ӧ�����ԣ����û�ҵ�����null��
     */
    public static Field findField(Class<?> targetClass, String fieldName) {
        if (fieldName.contains(".")) {
            return findNestedField(targetClass, fieldName);
        } else {
            return findDirectField(targetClass, fieldName);
        }
    }

    /**
     * ��ȡ����ע��ָ����ע�����Լ��ϡ�
     *
     * @param targetClass
     *            ��
     * @param annotationClassOnField
     *            ��ע
     * @return ����ע��ָ����ע�����Լ��ϡ�
     */
    public static List<Field> findField(Class<?> targetClass,
                                        Class<? extends Annotation> annotationClassOnField) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : getAllDeclaredField(targetClass)) {
            if (field.isAnnotationPresent(annotationClassOnField)) {
                fields.add(field);
            }
        }
        return fields;
    }

    /**
     * ��ȡ������ָ�����Ե�ֵ��
     *
     * @param target
     *            ����
     * @param field
     *            ����
     * @return ���ض�����ָ�����Ե�ֵ��
     */
    public static Object getField(Object target, Field field) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = field.get(target);
            field.setAccessible(accessible);
            return processHibernateLazyField(result);
        } catch (Exception e) {
            throw new IllegalStateException("��ȡ���������[" + field.getName()
                    + "]ֵʧ��", e);
        }
    }

    /**
     * ��ȡ������ָ�����Ե�ֵ��֧�ֶ�㼶��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @return ���ض�����ָ�����Ե�ֵ��
     */
    public static Object getField(Object target, String fieldName) {
        if (fieldName.contains(".")) {
            return getNestedField(target, fieldName);
        } else {
            return getDirectField(target, fieldName);
        }
    }

    /**
     * ���ö�����ָ�����Ե�ֵ��
     *
     * @param target
     *            ����
     * @param field
     *            ����
     * @param value
     *            ֵ
     */
    public static void setField(Object target, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(target, value);
            field.setAccessible(accessible);
        } catch (Exception e) {
            throw new IllegalStateException("���ö��������[" + field.getName()
                    + "]ֵʧ��", e);
        }
    }

    /**
     * ���ö�����ָ�����Ե�ֵ��֧�ֶ�㼶��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @param value
     *            ֵ
     */
    public static void setField(Object target, String fieldName, Object value) {
        if (fieldName.contains(".")) {
            setNestedField(target, fieldName, value);
        } else {
            setDirectField(target, fieldName, value);
        }
    }

    /**
     * �ݹ��ȡ���Field��ֱ���丸��ΪObject�ࡣ�����Field�����ڸ���֮ǰ��
     *
     * @param targetClass
     *            ��
     * @param excludeFieldNames
     *            �ų�����������
     * @return ����Field�б�
     */
    public static List<Field> getAllDeclaredField(Class<?> targetClass,
                                                  String... excludeFieldNames) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : targetClass.getDeclaredFields()) {
            if (CollectionUtils.contains(excludeFieldNames, field.getName())) {
                continue;
            }
            fields.add(field);
        }
        Class<?> parentClass = targetClass.getSuperclass();
        if (parentClass != Object.class) {
            fields.addAll(getAllDeclaredField(parentClass, excludeFieldNames));
        }
        return fields;
    }

    /**
     * ��������������ͬField��ֵ������Դ������Ϊnull��Field��
     *
     * @param <T>
     *            ��������
     * @param source
     *            Դ����
     * @param target
     *            Ŀ�����
     */
    public static <T> void copyFields(T source, T target) {
        copyFields(source, target, null, null);
    }

    /**
     * ��������������ͬField��ֵ������Դ������Ϊnull��Field��
     *
     * @param <T>
     *            ��������
     * @param source
     *            Դ����
     * @param target
     *            Ŀ�����
     * @param excludeFields
     *            �����Ƶ�Field�����ƣ��������֮���á�,���ָ�
     */
    public static <T> void copyFields(T source, T target, String excludeFields) {
        copyFields(source, target, null, excludeFields);
    }

    /**
     * ��������������ͬField��ֵ������Դ������Ϊnull��Field�������ָ����Ҫ���Ƶ�Field����Ϊnullʱ��FieldҲ���ơ�
     *
     * @param <T>
     *            ��������
     * @param source
     *            Դ����
     * @param target
     *            Ŀ�����
     * @param includeFields
     *            Ҫ���Ƶ�Field�����ƣ��������֮���á�,���ָ�
     * @param excludeFields
     *            �����Ƶ�Field�����ƣ��������֮���á�,���ָ�
     */
    public static <T> void copyFields(T source, T target, String includeFields,
                                      String excludeFields) {
        String[] includeFieldNames = new String[] {};
        if (!StringUtils.isBlank(includeFields)) {
            includeFieldNames = includeFields.split(Chars.COMMA);
        }
        String[] excludeFieldNames = new String[] {};
        if (!StringUtils.isBlank(excludeFields)) {
            excludeFieldNames = excludeFields.split(Chars.COMMA);
        }
        for (Field field : getAllDeclaredField(source.getClass(),
                excludeFieldNames)) {
            if (CollectionUtils.contains(includeFieldNames, field.getName())) {
                copyField(source, target, field.getName(), true);
            } else {
                copyField(source, target, field.getName(), false);
            }
        }
    }

    /**
     * ������������ָ��Field��ֵ��
     *
     * @param <T>
     *            ��������
     * @param source
     *            Դ����
     * @param target
     *            Ŀ�����
     * @param fieldName
     *            Field������
     * @param containedNull
     *            �Ƿ���nullֵ
     */
    @SuppressWarnings("unchecked")
    public static <T> void copyField(T source, T target, String fieldName,
                                     Boolean containedNull) {
        Object sourceFieldValue = getField(source, fieldName);
        Field targetField = findField(target.getClass(), fieldName);
        Boolean needCopy = targetField != null
                && !Modifier.isFinal(targetField.getModifiers())
                && !Modifier.isStatic(targetField.getModifiers());
        if (!containedNull && sourceFieldValue == null) {
            needCopy = false;
        }
        if (needCopy) {
            // ����Collection���͵�����
            if (sourceFieldValue != null
                    && Collection.class.isAssignableFrom(sourceFieldValue
                    .getClass())) {
                if (!((Collection<Object>) sourceFieldValue).isEmpty()
                        || containedNull) {
                    CollectionUtils.copy((Collection<Object>) sourceFieldValue,
                            (Collection<Object>) getField(target, fieldName));
                }
            } else {
                setField(target, fieldName, sourceFieldValue);
            }
        }
    }

    /**
     * ��ȡ����Field�ķ������͡�
     *
     * @param field
     *            ����Field
     * @return ���ط���Field�ķ������͡�
     */
    public static Class<?> getGenericFieldType(Field field) {
        Type type = ((ParameterizedType) field.getGenericType())
                .getActualTypeArguments()[0];
        if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            return (Class<?>) type;
        }
    }

    /**
     * ����Bean�������Ե�Map����
     *
     * @param bean
     *            Bean����
     * @param map
     *            Map����
     * @throws Exception
     */
    public static void copyProperties(Object bean, Map<String, Object> map)
            throws Exception {
        copyProperties(bean, map, null);
    }

    /**
     * ����Bean�������Ե�Map����
     *
     * @param bean
     *            Bean����
     * @param map
     *            Map����
     * @param excludeFields
     *            �����Ƶ�Field�����ƣ��������֮���á�,���ָ�
     * @throws Exception
     */
    public static void copyProperties(Object bean, Map<String, Object> map,
                                      String excludeFields) throws Exception {
        try {
            String[] excludeFieldNames = new String[] {};
            if (!StringUtils.isBlank(excludeFields)) {
                excludeFieldNames = excludeFields.split(Chars.COMMA);
            }
            for (Field field : getAllDeclaredField(bean.getClass(),
                    excludeFieldNames)) {
                if (!CollectionUtils.contains(excludeFieldNames,
                        field.getName())) {
                    map.put(field.getName(), getField(bean, field));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ����Map�������Ե�Bean����
     *
     * @param map
     *            Map����
     * @param bean
     *            Bean����
     * @throws Exception
     */
    public static void copyProperties(Map<String, Object> map, Object bean)
            throws Exception {
        copyProperties(map, bean, null);
    }

    /**
     * ����Map�������Ե�Bean����
     *
     * @param map
     *            Map����
     * @param bean
     *            Bean����
     * @param excludeFields
     *            �����Ƶ�Field�����ƣ��������֮���á�,���ָ�
     * @throws Exception
     *
     */
    public static void copyProperties(Map<String, Object> map, Object bean,
                                      String excludeFields) throws Exception {
        try {
            String[] excludeFieldNames = new String[] {};
            if (!StringUtils.isBlank(excludeFields)) {
                excludeFieldNames = excludeFields.split(Chars.COMMA);
            }
            for (Map.Entry<String, Object> entity : map.entrySet()) {
                Field field = findField(bean.getClass(), entity.getKey());
                if (field != null
                        && !CollectionUtils.contains(excludeFieldNames,
                        entity.getKey())) {
                    setField(bean, field, entity.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ����ָ�����Ƶĵ��㼶���ԡ�
     *
     * @param targetClass
     *            ��
     * @param fieldName
     *            ������
     * @return ���ض�Ӧ�����ԣ����û�ҵ�����null��
     */
    private static Field findDirectField(Class<?> targetClass, String fieldName) {
        for (Field field : getAllDeclaredField(targetClass)) {
            if (fieldName.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }

    /**
     * ��ȡ����ָ�����ƵĶ�㼶���ԡ�
     *
     * @param targetClass
     *            ��
     * @param fieldName
     *            ������
     * @return ���ض�Ӧ�����ԣ����û�ҵ�����null��
     */
    private static Field findNestedField(Class<?> targetClass, String fieldName) {
        String[] nestedFieldNames = fieldName.split("\\.");
        Field field = null;
        for (String nestedFieldName : nestedFieldNames) {
            field = findDirectField(targetClass, nestedFieldName);
            targetClass = field.getType();
        }
        return field;
    }

    /**
     * ��ȡ������ָ�����㼶���Ե�ֵ��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @return ���ض�����ָ�����Ե�ֵ��
     */
    private static Object getDirectField(Object target, String fieldName) {
        return getField(target, findDirectField(target.getClass(), fieldName));
    }

    /**
     * ��ȡ������ָ����㼶���Ե�ֵ��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @return ���ض�����ָ�����Ե�ֵ��
     */
    private static Object getNestedField(Object target, String fieldName) {
        String[] nestedFieldNames = fieldName.split("\\.");
        for (String nestedFieldName : nestedFieldNames) {
            target = getDirectField(target, nestedFieldName);
        }
        return target;
    }

    /**
     * ���ö�����ָ�����㼶���Ե�ֵ��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @param value
     *            ֵ
     */
    private static void setDirectField(Object target, String fieldName,
                                       Object value) {
        setField(target, findDirectField(target.getClass(), fieldName), value);
    }

    /**
     * ���ö�����ָ����㼶���Ե�ֵ��
     *
     * @param target
     *            ����
     * @param fieldName
     *            ������
     * @param value
     *            ֵ
     */
    private static void setNestedField(Object target, String fieldName,
                                       Object value) {
        String[] nestedFieldNames = StringUtils.substringBeforeLast(fieldName,
                ".").split("\\.");
        for (String nestedFieldName : nestedFieldNames) {
            target = getDirectField(target, nestedFieldName);
        }
        setDirectField(target, StringUtils.substringAfterLast(fieldName, "."),
                value);
    }

    /**
     * ����Hibernate���������ԡ�
     *
     * @param fieldValue
     *            ����ֵ
     * @return �����Hibernate������������ִ�д���������ʵ�ʵ����Զ��󣬷���ֱ�ӷ��ء�
     */
    private static Object processHibernateLazyField(Object fieldValue) {
        try {
            Class<?> hibernateProxyClass = Class
                    .forName("org.hibernate.proxy.HibernateProxy");
            if (hibernateProxyClass.isAssignableFrom(fieldValue.getClass())) {
                Method method = fieldValue.getClass().getMethod(
                        "getHibernateLazyInitializer");
                Object lazyInitializer = method.invoke(fieldValue);
                method = lazyInitializer.getClass().getMethod(
                        "getImplementation");
                return method.invoke(lazyInitializer);
            } else {
                return fieldValue;
            }
        } catch (Exception e) {
            return fieldValue;
        }
    }


    class Chars {
        public static final String BACKSLASH = "/";
        public static final String SLASH = "\\";
        public static final String LF = "\n";
        public static final String CR = "\r";
        public static final String COMMA = ",";
        public static final String EQUAL = "=";
        public static final String SEMICOLON = ";";
        public static final String COLON = ":";
        public static final String DOT = ".";
        public static final String BAR = "|";
        public static final String UNDERLINE = "_";
    }
}

