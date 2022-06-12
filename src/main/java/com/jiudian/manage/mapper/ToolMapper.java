package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Tool;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolMapper {
    List<Tool> get_all_tool();

    String get_toolAmount_by_id(String tid);

    void delete_tool(String id);

    void update_tool(Tool newTool);

    void InsertTool(Tool newTool);

    void back_tool(String amount,String tool);
}
