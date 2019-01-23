package com.ssm.common.dao;

import com.ssm.common.annotation.Blur;
import com.ssm.common.annotation.Column;
import com.ssm.common.annotation.Table;
import com.ssm.common.pager.SystemContext;
import com.ssm.common.utils.CommonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
public class BaseSqlProvider {
    private static final long serialVersionUID = 5797147250151699467L;

    private static final Logger logger = LoggerFactory.getLogger(BaseSqlProvider.class);

    private Field[] getFields(Class<?> beanclass){
        Field[] beanFields = beanclass.getDeclaredFields();
        Class<?> beanSuperClass = beanclass.getSuperclass();
        Field[] beanSuperFields = beanSuperClass.getDeclaredFields();
        return ArrayUtils.addAll(beanFields,beanSuperFields);
    }


    private String getTableName(Class<?> beanClass) {
        String tableName = "";
        Table table = beanClass.getAnnotation(Table.class);
        if (table != null) {
            tableName = table.value();
        }
        return tableName;
    }

    /**
     * 添加sql
     */
    public String buildAddSql(Object bean){
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder addSqlBuilder = new StringBuilder();
        List<String> insertParas = new ArrayList<String>();
        List<String> insertParaNames = new ArrayList<String>();
        addSqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        try{
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                if(column==null){
                    continue;
                }
                String columnName = "";
                columnName = column.value();
                field.setAccessible(true);
                Object object = field.get(bean);
                if (object != null) {
                    insertParaNames.add(columnName);
                    insertParas.add("#{" + field.getName() + "}");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < insertParaNames.size(); i++) {
            addSqlBuilder.append(insertParaNames.get(i));
            if (i != insertParaNames.size() - 1)
                addSqlBuilder.append(", ");
        }
        addSqlBuilder.append(")").append(" VALUES (");
        for (int i = 0; i < insertParas.size(); i++) {
            addSqlBuilder.append(insertParas.get(i));
            if (i != insertParas.size() - 1)
                addSqlBuilder.append(", ");
        }
        addSqlBuilder.append(")");
        logger.debug("buildAddSql：【" + addSqlBuilder.toString() + "】");
        return addSqlBuilder.toString();
    }

    /**
     * 修改sql
     */
    public String buildUpdateSql(Object bean)throws Exception{
        Class<?> beanClass = bean.getClass();
        String beanName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder updateSqlBuilder = new StringBuilder();
        updateSqlBuilder.append("UPDATE ").append(beanName).append(" SET ");
        try{
            for(int i=0;i<fields.length;i++){
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                String columnName = "";
                if(column==null){
                    continue;
                }
                columnName = column.value();
                field.setAccessible(true);
                Object beanValue = field.get(bean);
                if (beanValue != null) {
                    if (i != 0) {
                        updateSqlBuilder.append(", ");
                    }
                    updateSqlBuilder.append(columnName).append("=#{").append(field.getName()).append("}");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        updateSqlBuilder.append(" WHERE ").append("id =#{id}");
        return updateSqlBuilder.toString();
    }

    /**
     * 删除SQL
     *
     * @param bean
     * @return
     */
    public String buildDeleteSql(Object bean)throws Exception {
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder deleteSqlBuilder = new StringBuilder();
        deleteSqlBuilder.append("DELETE FROM ").append(tableName).append(" WHERE 1=1");
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                String columnName = "";
                if (column == null) {
                    continue;
                }
                columnName = column.value();
                field.setAccessible(true);
                Object beanValue = field.get(bean);
                if (beanValue != null) {
                    if (i != 0) {
                        deleteSqlBuilder.append(" AND ");
                    }
                    deleteSqlBuilder.append(columnName).append("=#{").append(field.getName()).append("}");
                }
            }
        } catch (Exception e) {
            logger.error("buildDeleteSql exception", e);
            throw e;
        }
        logger.debug("buildDeleteSql：【" + deleteSqlBuilder.toString() + "】");
        return deleteSqlBuilder.toString();
    }
    /**
     * 查询SQL
     *
     * @param bean
     * @return
     */
    public String buildGetSql(Object bean)throws Exception  {
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        //Column[] columns = getColumns(beanClass);
        StringBuilder selectOneSqlBuilder = new StringBuilder();
        List<String> selectParaNames = new ArrayList<String>();
        List<String> selectParas = new ArrayList<String>();
        selectOneSqlBuilder.append("SELECT ");
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                String columnName;
                if (column == null) {
                    continue;
                }
                columnName = column.value();
                field.setAccessible(true);
                Object object = field.get(bean);
                selectOneSqlBuilder.append(columnName);
                if (object != null) {
                    selectParaNames.add(columnName);
                    if(selectParas.size()==0){
                        selectParas.add("#{" + field.getName() + "}");
                    }else {
                        selectParas.add(" , #{" + field.getName() + "}");
                    }

                }
            }
        } catch (Exception e) {
            logger.error("buildSelectOneSql exception", e);
            throw e;
        }
        selectOneSqlBuilder.append(" FROM ").append(tableName);
        if (!CommonUtil.isCollectionEmpty(selectParaNames)) {
            selectOneSqlBuilder.append(" WHERE ");
            for (int i = 0; i < selectParaNames.size(); i++) {
                selectOneSqlBuilder.append(selectParaNames.get(i)).append("=").append(selectParas.get(i));
                if (i != selectParaNames.size() - 1)
                    selectOneSqlBuilder.append(" AND ");
            }
        }
        logger.debug("buildSelectOneSql：【" + selectOneSqlBuilder.toString() + "】");
        return selectOneSqlBuilder.toString();
    }

    /**
     * 列表SQL
     *
     * @param bean
     * @return
     */
    public String buildListSql(Object bean) throws Exception {
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder selectListSqlBuilder = new StringBuilder();
        List<String> selectParaNames = new ArrayList<String>();
        List<String> selectParas = new ArrayList<String>();

        String clazzName = Thread.currentThread().getStackTrace()[1].getMethodName();

        buildSelectSql(selectListSqlBuilder,fields,bean,selectParaNames,selectParas,tableName,clazzName);
//        selectListSqlBuilder.append("SELECT ");
//        try {
//            int j=0;
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                Column column = field.getAnnotation(Column.class);
//                String columnName = "";
//                if (column != null) {
//                    continue;
//                }
//                columnName = column.value();
//                field.setAccessible(true);
//                Object object = field.get(bean);
//
//                if(j==0){
//                    selectListSqlBuilder.append(columnName);
//                }else{
//                    selectListSqlBuilder.append(" , ");
//                    selectListSqlBuilder.append(columnName);
//                }
//                j++;
//                if (object != null) {
//                    selectParaNames.add(columnName);
//                    Blur blur = field.getAnnotation(Blur.class);
//                    if(blur!=null&&Arrays.asList(blur.value()).contains("find")){
//                        selectParas.add(" like concat(concat('%',#{" + field.getName() + "}),'%') ");
//                    }else{
//                        selectParas.add(" = "+" #{" + field.getName() + "} ");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("buildSelectListSql exception", e);
//            throw e;
//        }
//        selectListSqlBuilder.append(" FROM ").append(tableName);
//        if (!CommonUtil.isCollectionEmpty(selectParaNames)) {
//            selectListSqlBuilder.append(" WHERE 1=1");
//            for (int i = 0; i < selectParaNames.size(); i++) {
//                selectListSqlBuilder.append(" AND " + selectParaNames.get(i)).append(" ").append(selectParas.get(i));
//            }
//        }



        logger.debug("buildSelectListSql：【" + selectListSqlBuilder.toString() + "】");
        return selectListSqlBuilder.toString();
    }

    /**
     * 分页SQL
     *
     * @param bean
     * @return
     */
    public String buildFindSql(Object bean) throws Exception {
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder selectPagerSqlBuilder = new StringBuilder();
        List<String> selectParaNames = new ArrayList<String>();
        List<String> selectParas = new ArrayList<String>();
        selectPagerSqlBuilder.append(" SELECT * FROM (");
        selectPagerSqlBuilder.append(" SELECT T.*, rownum rownum_ FROM (");
        String clazzName = Thread.currentThread().getStackTrace()[1].getMethodName();
        buildSelectSql(selectPagerSqlBuilder,fields,bean,selectParaNames,selectParas,tableName,clazzName);

//        selectPagerSqlBuilder.append("SELECT ");
//        try {
//            int j=0;
//            for (int i = 0; i < fields.length; i++) {
//                Field field = fields[i];
//                Column column = field.getAnnotation(Column.class);
//                String columnName = "";
//                if (column != null) {
//                    continue;
//                }
//                columnName = column.value();
//                field.setAccessible(true);
//                Object object = field.get(bean);
//                if(j==0){
//                    selectPagerSqlBuilder.append(columnName);
//                }else{
//                    selectPagerSqlBuilder.append(" , ");
//                    selectPagerSqlBuilder.append(columnName);
//                }
//                if (object != null) {
//                    selectParaNames.add(columnName);
//                    Blur blur = field.getAnnotation(Blur.class);
//                    if(blur!=null&& Arrays.asList(blur.value()).contains("find")){
//                        selectParas.add(" like concat(concat('%',#{" + field.getName() + "}),'%') ");
//                    }else{
//                        selectParas.add(" = "+" #{" + field.getName() + "} ");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("buildSelectPagerSql exception", e);
//            throw e;
//        }
//        selectPagerSqlBuilder.append(" FROM ").append(tableName);
//        if (!CommonUtil.isCollectionEmpty(selectParaNames)) {
//            selectPagerSqlBuilder.append(" WHERE ");
//            for (int i = 0; i < selectParaNames.size(); i++) {
//                selectPagerSqlBuilder.append(selectParaNames.get(i)).append(" LIKE ").append("'%"+selectParas.get(i)+"%'");
//                if (i != selectParaNames.size() - 1)
//                    selectPagerSqlBuilder.append(" AND ");
//            }
//        }



        selectPagerSqlBuilder.append(" ) T ");
        selectPagerSqlBuilder.append(" WHERE rownum <= " + (SystemContext.getCurrentPage() * SystemContext.getPageSize()) + " ) ");
        selectPagerSqlBuilder.append(" WHERE rownum_ > " + ((SystemContext.getCurrentPage() - 1) * SystemContext.getPageSize()));

        logger.debug("buildSelectPagerSql：【" + selectPagerSqlBuilder.toString() + "】");
        return selectPagerSqlBuilder.toString();
    }


    private void buildSelectSql(StringBuilder selectPagerSqlBuilder,Field[] fields,Object bean,
                                List<String> selectParaNames,List<String> selectParas,String tableName, String methodName)throws Exception{

        selectPagerSqlBuilder.append("SELECT ");
        try {
            int j=0;
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                String columnName = "";
                if (column == null) {
                    continue;
                }
                columnName = column.value();
                field.setAccessible(true);
                Object object = field.get(bean);
                if(j==0){
                    selectPagerSqlBuilder.append(columnName);
                }else{
                    selectPagerSqlBuilder.append(" , ");
                    selectPagerSqlBuilder.append(columnName);
                }
                if (object != null) {
                    selectParaNames.add(columnName);
                    Blur blur = field.getAnnotation(Blur.class);
                    if(blur!=null&& Arrays.asList(blur.value()).contains(methodName)){
                        selectParas.add(" like concat(concat('%',#{" + field.getName() + "}),'%') ");
                    }else{
                        selectParas.add(" = "+" #{" + field.getName() + "} ");
                    }
                }
                j++;
            }
        } catch (Exception e) {
            logger.error("buildSelectPagerSql exception", e);
            throw e;
        }
        selectPagerSqlBuilder.append(" FROM ").append(tableName);
        if (!CommonUtil.isCollectionEmpty(selectParaNames)) {
            selectPagerSqlBuilder.append(" WHERE 1=1");
            for (int i = 0; i < selectParaNames.size(); i++) {
                selectPagerSqlBuilder.append(" AND " + selectParaNames.get(i)).append(" ").append(selectParas.get(i));
            }
        }

    }
    /**
     * 总条数
     *
     * @param bean
     * @return
     */
    public String buildCountSql(Object bean)throws Exception {
        Class<?> beanClass = bean.getClass();
        String tableName = getTableName(beanClass);
        Field[] fields = getFields(beanClass);
        StringBuilder countTotalSqlBuilder = new StringBuilder();
        List<String> selectParaNames = new ArrayList<String>();
        List<String> selectParas = new ArrayList<String>();
        countTotalSqlBuilder.append("SELECT count(*) FROM ").append(tableName);
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Column column = field.getAnnotation(Column.class);
                String columnName = "";
                if (column != null) {
                    if (!column.required())
                        continue;
                    columnName = column.value();
                }else {
                    continue;
                }
                field.setAccessible(true);
                Object object = field.get(bean);
                if (object != null) {
                    selectParaNames.add(columnName);
                    selectParas.add("#{" + field.getName() + "}");
                }
            }
            if (!CommonUtil.isCollectionEmpty(selectParaNames)) {
                countTotalSqlBuilder.append(" WHERE ");
                for (int i = 0; i < selectParaNames.size(); i++) {
                    countTotalSqlBuilder.append(selectParaNames.get(i)).append("=").append(selectParas.get(i));
                    if (i != selectParaNames.size() - 1)
                        countTotalSqlBuilder.append(" AND ");
                }
            }
        } catch (Exception e) {
            logger.error("buildCountSql exception", e);
            throw e;
        }

        logger.debug("buildCountTotalSqlBuilder：【" + countTotalSqlBuilder.toString() + "】");
        return countTotalSqlBuilder.toString();
    }


}
