package com.onyeka.project1.mapper;


import com.onyeka.project1.model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

    @Select("SELECT * FILES WHERE filename = #{filename}")
    File getFile(String fileName);
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata) VALUES(#(filename), #(contentType), #(fileSize), #(fileData))")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);
}
