package com.onyeka.project1.mapper;


import com.onyeka.project1.model.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * CREDENTIALS WHERE username = #{username}")
    Credential getCredential(String username);
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password) VALUE(#{url}, #{username}, #{key}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);
}
