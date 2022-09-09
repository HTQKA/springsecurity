package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT DISTINCT perms from sys_menu where id in (\n" +
            "            SELECT menu_id  from sys_role_menu where role_id in (\n" +
            "                SELECT role_id from sys_user_role  where user_id=#{userId}\n" +
            "            )\n" +
            "        ) and status='0'")
    List<String> selectPermsByUserId(@Param("userId") Long userId);

    List<String> selectPermsByUserId1(Long userId);
}
