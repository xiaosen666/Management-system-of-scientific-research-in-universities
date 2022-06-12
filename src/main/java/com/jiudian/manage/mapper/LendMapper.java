package com.jiudian.manage.mapper;

import com.jiudian.manage.model.Lend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendMapper {
    int Insert_a_record(Lend lend_sth);
    List<Lend> Get_lend_list(String id);
    List<Lend> Get_all_lend_list();
    void Setcheckname(String uname,int lendid);
    void delect_lend(String lid);
}
