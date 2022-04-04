package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Opening_app;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Op_appMapper {
    void Insert_Op_app(Opening_app application);

    List<Opening_app> get_Op_app();

    void Edit_State(String state,String p_id);
}
