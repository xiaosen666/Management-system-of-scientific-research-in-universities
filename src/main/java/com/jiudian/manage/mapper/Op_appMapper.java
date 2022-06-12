package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Opening_app;
import com.jiudian.manage.model.Project;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Op_appMapper {
    void Insert_Op_app(Opening_app application);

    List<Opening_app> get_Op_app();

    Opening_app Get_checked(String p_id);

    void Edit_State(String state,String p_id);

    void Edit_money_State(String state, String p_id, String gm_admin, Date checkDate);

    List<Project> Get_all_checked();
}
