package com.example.demo.dao;
import com.example.demo.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TestDao {

    int deleteById(@Param("id") String id);

    int addById(@Param("corpusId") String id,@Param("corpusName") String name,@Param("corpusTime") String time,@Param("corpusNum") String number);

    List<ArticleVO> search(@Param("articleName") String name);

    List<ArticleVO> searchAll(String userId);
}
