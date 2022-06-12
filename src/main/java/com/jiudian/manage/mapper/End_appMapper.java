package com.jiudian.manage.mapper;

import com.jiudian.manage.model.End_app;
import com.jiudian.manage.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface End_appMapper {
    void Insert_End_app(End_app application);

    List<End_app> get_End_app();

    void Edit_end_State(String state,String p_id);

    End_app get_End_checked(String pid);

    List<Project> get_all_checked_End_app();
}
