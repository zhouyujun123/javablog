package com.example.demo.dao;
import com.example.demo.po.ArticlePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TestDAO {

    int deleteById(@Param("id") String id);

    int addById(@Param("corpusId") String id,@Param("corpusName") String name,@Param("corpusTime") String time,@Param("corpusNum") String number);

    List<ArticlePO> search(@Param("articleName") String name);

    List<ArticlePO> searchAll(String userId);
}
