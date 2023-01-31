import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cntomorrow.admin.AdminApplication;
import com.cntomorrow.modules.test.entity.EntityConfigOldMapper;
import com.cntomorrow.modules.test.entity.EntityFieldConfigOldMapper;
import com.cntomorrow.core.tool.jackson.JsonUtil;
import com.cntomorrow.core.tool.support.Kv;
import com.cntomorrow.core.tool.utils.Func;
import com.cntomorrow.modules.entity.entity.EntityFieldConfig;
import com.cntomorrow.modules.entity.mapper.EntityConfigMapper;
import com.cntomorrow.modules.entity.mapper.EntityFieldConfigMapper;
import com.cntomorrow.modules.entity.provider.MybatisEntityDataProvider;
import com.cntomorrow.modules.sys.entity.SysMenuEntity;
import com.cntomorrow.modules.sys.service.SysMenuService;
import com.cntomorrow.modules.test.entity.EntityConfigOld;
import com.cntomorrow.modules.test.entity.EntityFieldConfigOld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class EntityDataConverTest {

    @Resource
    private MybatisEntityDataProvider mybatisEntityDataProvider;

    @Resource
    private EntityConfigMapper entityConfigMapper;

    @Resource
    private EntityConfigOldMapper entityConfigOldMapper;

    @Resource
    private EntityFieldConfigOldMapper entityFieldConfigOldMapper;

    @Resource
    private EntityFieldConfigMapper entityFieldConfigMapper;

    @Resource
    private SysMenuService sysMenuService;



    /**
     * 数据库转换--实体配置
     */
    @Test
    public void configDbConver(){
        //不需要修改
        List<EntityConfigOld> entityConfigs = entityConfigOldMapper.selectList(null);
        //以tag为单位转换配置
        for (EntityConfigOld entityConfig : entityConfigs) {
            String tag = entityConfig.getTag();
            LambdaQueryWrapper<EntityFieldConfigOld> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EntityFieldConfigOld::getTag, tag);
            queryWrapper.orderByAsc(EntityFieldConfigOld::getColumnSort);
            List<EntityFieldConfigOld> fieldList = entityFieldConfigOldMapper.selectList(queryWrapper);
            //分组
            List<String> groupNames  = new ArrayList<>();
            for (EntityFieldConfigOld entityFieldConfigOld : fieldList) {
                String groupName = entityFieldConfigOld.getColumnGroup();
                if(StrUtil.isEmpty(groupName)){
                    groupName = "基本信息";
                }
                if(!groupNames.contains(groupName)){
                    groupNames.add(groupName);
                }
            }

            List<EntityFieldConfig> entityFieldConfigs = new ArrayList<>();
            Integer index = 1;

            //先找主键、tag

            for (EntityFieldConfigOld fieldOld : fieldList) {
                if(StrUtil.equalsIgnoreCase(fieldOld.getColumnName(), "id")
                || StrUtil.equalsIgnoreCase(fieldOld.getColumnName(), "tag")){
                    EntityFieldConfig fieldConfig = new EntityFieldConfig();
                    fieldConfig.setId(fieldOld.getId());
                    fieldConfig.setTag(tag);
                    fieldConfig.setTableName(fieldOld.getTableName());
                    fieldConfig.setColumnName(fieldOld.getColumnName());
                    fieldConfig.setColumnType(fieldOld.getColumnType());
                    fieldConfig.setColumnComment(fieldOld.getComments());
                    fieldConfig.setUiLabel(fieldOld.getColumnLabel());
                    fieldConfig.setUiType(fieldOld.getShowType());
                    //默认值 -1没有默认值 0-自定义字符串 1-当前用户、时间、部门 2-系统常量 3-自定义sql'
                    Integer defaultType = -1;
                    String defaultValue = "";
                    if(StrUtil.isNotEmpty(fieldOld.getDefaultValue())){
                        defaultType = 1;
                        defaultValue = fieldOld.getDefaultValue();
                    }
                    fieldConfig.setUiDefaultType(defaultType);
                    fieldConfig.setUiDefaultValue(defaultValue);

                    String width = fieldOld.getGrid();
                    int uiWidth = 50;
                    if(StrUtil.equalsIgnoreCase(width, "3-4-8")){
                        uiWidth = 25;
                    }else if(StrUtil.equalsIgnoreCase(width, "6-4-8")){
                        uiWidth = 50;
                    }else if(StrUtil.equalsIgnoreCase(width, "12-2-10")
                            || StrUtil.equalsIgnoreCase(width, "12-2-8")
                            || StrUtil.equalsIgnoreCase(width, "12-2-5")){
                        uiWidth = 100;
                    }
                    fieldConfig.setUiWidth(uiWidth);
                    fieldConfig.setUiSort(index);
                    fieldConfig.setAttrName(fieldOld.getAttrName());
                    fieldConfig.setAttrType(fieldOld.getAttrType());

                    fieldConfig.setIsPk(getBoolean(fieldOld.getIsPk()));
                    fieldConfig.setIsTag(StrUtil.equalsIgnoreCase(fieldOld.getColumnName(), "tag") ? true : false);
                    boolean isNull = false;
                    if(StrUtil.equalsIgnoreCase(fieldOld.getIsNull(), "1")){
                        isNull = false;
                    }else{
                        isNull = true;
                    }
                    fieldConfig.setIsNull(isNull);
                    fieldConfig.setIsInsert(getBoolean(fieldOld.getIsInsert()));
                    fieldConfig.setIsUpdate(getBoolean(fieldOld.getIsUpdate()));
                    fieldConfig.setIsList(getBoolean(fieldOld.getIsList()));
                    fieldConfig.setIsQuery(getBoolean(fieldOld.getIsQuery()));
                    fieldConfig.setQueryType(fieldOld.getQueryType());

                    fieldConfig.setIsEdit(Integer.valueOf(fieldOld.getIsEdit()));
                    fieldConfig.setIsAdd(Integer.valueOf(fieldOld.getIsAdd()));
                    fieldConfig.setIsDetail(getBoolean(fieldOld.getIsDetail()));
                    fieldConfig.setIsExport(getBoolean(fieldOld.getIsEdit()));

                    Kv options = Kv.init();
                    if(StrUtil.isNotEmpty(fieldOld.getOptions())){
                        options = JsonUtil.parse(fieldOld.getOptions(), Kv.class);
                    }
                    int dictType = -1;
                    String dictValue = "";
                    //-1没有 0-字典 1-实体 tag.field 2 自定义sql
                    if(!Func.isNull(fieldOld.getDictName())){
                        dictType = 0;
                        dictValue = fieldOld.getDictName();
                    }else{
                        //是否有自定义SQL

                        if (!Func.hasEmpty(options) && Func.isNotEmpty(options.get("dictSql"))) {
                            String dictSql = String.valueOf(options.get("dictSql"));
                            //需要删掉自定义sql
                            options.remove("dictSql");
                            dictType = 2;
                            dictValue = dictSql;
                        }
                    }
                    fieldConfig.setDictType(dictType);
                    fieldConfig.setDictValue(dictValue);
                    fieldConfig.setFieldValid(fieldOld.getFieldValid());
                    if(StrUtil.isNotEmpty(fieldOld.getIsLink())){
                        fieldConfig.setLinkType(Integer.valueOf(fieldOld.getIsLink()));
                        fieldConfig.setLinkUrl(fieldOld.getLinkUrl());
                    }
                    if(!Func.isEmpty(options)){
                        fieldConfig.setOptionsStr(JsonUtil.toJson(options));
                    }
                    entityFieldConfigs.add(fieldConfig);
                    index++;
                }

            }


            for (String groupName : groupNames) {
                //添加分组
                EntityFieldConfig fieldConfig = new EntityFieldConfig();
                fieldConfig.setTag(tag);
                fieldConfig.setUiLabel(groupName);
                fieldConfig.setUiType("group");
                fieldConfig.setUiWidth(Integer.valueOf(100));
                fieldConfig.setUiSort(index);
                entityFieldConfigs.add(fieldConfig);
                index++;
                //添加分组下的字段
                for (EntityFieldConfigOld fieldOld : fieldList) {

                    if(StrUtil.equalsIgnoreCase(fieldOld.getColumnName(), "id")
                            || StrUtil.equalsIgnoreCase(fieldOld.getColumnName(), "tag")){
                        continue;
                    }
                    if(fieldOld.getId().equals("1400261593769578498")){
                        System.out.println();
                    }
                    String columnGroup = fieldOld.getColumnGroup();
                    if(StrUtil.isBlank(columnGroup)){
                        columnGroup = "基本信息";
                    }
                    if(!StrUtil.equalsIgnoreCase(columnGroup, groupName)){
                        continue;
                    }

                    fieldConfig = new EntityFieldConfig();
                    fieldConfig.setId(fieldOld.getId());
                    fieldConfig.setTag(tag);
                    fieldConfig.setTableName(fieldOld.getTableName());
                    fieldConfig.setColumnName(fieldOld.getColumnName());
                    fieldConfig.setColumnType(fieldOld.getColumnType());
                    fieldConfig.setColumnComment(fieldOld.getComments());
                    fieldConfig.setUiLabel(fieldOld.getColumnLabel());
                    fieldConfig.setUiType(fieldOld.getShowType());
                    //默认值 -1没有默认值 0-自定义字符串 1-当前用户、时间、部门 2-系统常量 '
                    int defaultType = -1;
                    String defaultValue = "";
                    if(StrUtil.isNotEmpty(fieldOld.getDefaultValue())){
                        defaultType = 1;
                        defaultValue = fieldOld.getDefaultValue();
                    }
                    fieldConfig.setUiDefaultType(defaultType);
                    fieldConfig.setUiDefaultValue(defaultValue);

                    String width = fieldOld.getGrid();
                    int uiWidth = 50;
                    if(StrUtil.equalsIgnoreCase(width, "3-4-8")){
                        uiWidth = 25;
                    }else if(StrUtil.equalsIgnoreCase(width, "6-4-8")){
                        uiWidth = 50;
                    }else if(StrUtil.equalsIgnoreCase(width, "12-2-10")
                            || StrUtil.equalsIgnoreCase(width, "12-2-8")
                            || StrUtil.equalsIgnoreCase(width, "12-2-5")){
                        uiWidth = 100;
                    }
                    fieldConfig.setUiWidth(uiWidth);
                    fieldConfig.setUiSort(index);
                    fieldConfig.setAttrName(fieldOld.getAttrName());
                    fieldConfig.setAttrType(fieldOld.getAttrType());

                    fieldConfig.setIsPk(false);
                    fieldConfig.setIsTag(false);
                    boolean isNull = false;
                    if(StrUtil.equalsIgnoreCase(fieldOld.getIsNull(), "1")){
                        isNull = false;
                    }else{
                        isNull = true;
                    }
                    fieldConfig.setIsNull(isNull);
                    fieldConfig.setIsInsert(getBoolean(fieldOld.getIsInsert()));
                    fieldConfig.setIsUpdate(getBoolean(fieldOld.getIsUpdate()));
                    fieldConfig.setIsList(getBoolean(fieldOld.getIsList()));
                    fieldConfig.setIsQuery(getBoolean(fieldOld.getIsQuery()));
                    fieldConfig.setQueryType(fieldOld.getQueryType());

                    fieldConfig.setIsEdit(Integer.valueOf(fieldOld.getIsEdit()));
                    fieldConfig.setIsAdd(Integer.valueOf(fieldOld.getIsAdd()));
                    fieldConfig.setIsDetail(getBoolean(fieldOld.getIsDetail()));
                    fieldConfig.setIsExport(getBoolean(fieldOld.getIsEdit()));

                    Kv options = Kv.init();
                    if(StrUtil.isNotEmpty(fieldOld.getOptions())){
                        options = JsonUtil.parse(fieldOld.getOptions(), Kv.class);
                    }
                    int dictType = -1;
                    String dictValue = "";
                    //-1没有 0-字典 1-实体 tag.field 2 自定义sql
                    if(!Func.isNull(fieldOld.getDictName()) && !Func.isEmpty(fieldOld.getDictName())){
                        dictType = 0;
                        dictValue = fieldOld.getDictName();
                    }else{
                        //是否有自定义SQL
                        if (!Func.hasEmpty(options) && Func.isNotEmpty(options.get("dictSql"))) {
                            String dictSql = String.valueOf(options.get("dictSql"));
                            //需要删掉自定义sql
                            options.remove("dictSql");
                            dictType = 2;
                            dictValue = dictSql;
                        }
                    }
                    fieldConfig.setDictType(dictType);
                    fieldConfig.setDictValue(dictValue);
                    fieldConfig.setFieldValid(fieldOld.getFieldValid());
                    if(StrUtil.isNotEmpty(fieldOld.getIsLink())){
                        fieldConfig.setLinkType(Integer.valueOf(fieldOld.getIsLink()));
                        fieldConfig.setLinkUrl(fieldOld.getLinkUrl());
                    }else{
                        fieldConfig.setLinkType(-1);
                    }
                    if(!Func.isEmpty(options)){
                        fieldConfig.setOptionsStr(JsonUtil.toJson(options));
                    }
                    entityFieldConfigs.add(fieldConfig);
                    index++;
                }
            }
            for (EntityFieldConfig entityFieldConfig : entityFieldConfigs) {
                entityFieldConfigMapper.insert(entityFieldConfig);
            }
        }

    }

    /**
     * 数据库转换--菜单、路由配置
     */
    @Test
    public void menuDbConver(){
        //系统菜单、目录、自定义菜单(组件为非产品的)、按钮 不需要转换
        //
        List<SysMenuEntity> menuEntities = sysMenuService.list(null);
        for (SysMenuEntity menu : menuEntities) {
            //类型 0：目录 1：菜单 2：按钮 3:实体
            Integer type = menu.getType();
            if(type == 0 || type == 2 || type == 3){
                continue;
            }
            String path = menu.getPath();
            String component = menu.getComponent();

            if(!StrUtil.startWith(component, "modules/entity/tpl-crud/")){
                continue;
            }

            if(StrUtil.isEmpty(menu.getOptions())){
                continue;
            }
            String newPath = path;
            String params = StrUtil.subAfter(path, "?", true);
            path = StrUtil.subBefore(path, "?", true);
            String tag = StrUtil.subAfter(path, "/", true);
            Kv options = JsonUtil.parse(menu.getOptions(), Kv.class);
//            String tag = options.getStr("tag");
            if(StrUtil.equals(component, "modules/entity/tpl-crud/Preview")){
                newPath = "/entity/"+tag+"/list/tab";
            }
            if(StrUtil.isNotEmpty(params)){
                newPath += "?" + params;
            }
            menu.setPath(newPath);
            menu.setType(3);
            sysMenuService.updateById(menu);
        }

    }


    public boolean getBoolean(String str){
        if(StrUtil.equalsIgnoreCase(str, "1")){
            return true;
        }else{
            return false;
        }
    }

}