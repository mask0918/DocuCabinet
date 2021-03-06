package com.bst.pidms.dao;

import com.bst.pidms.entity.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Label record);

    Label selectByPrimaryKey(Integer id);

    List<Label> selectByCondition(Label label);

    List<Label> selectAll();

    int updateByPrimaryKey(Label record);

    List<Label> selectTreeByCid(@Param("userId") Integer userId, @Param("id") Integer id);

    List<Label> selectTreeByPid(@Param("userId") Integer userId, @Param("pid") Integer id);
}