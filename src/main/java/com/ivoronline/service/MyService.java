package com.ivoronline.service;

import com.ivoronline.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // SELECT RECORD
  //=========================================================================================================
  public PersonDTO selectRecord(Integer id, Integer age) {
    return (PersonDTO) jdbcTemplate.queryForObject(
      " SELECT * FROM PERSON WHERE ID = ? AND AGE = ?"   //Return single Record
      , new Object[] { id, age }
      , new BeanPropertyRowMapper(PersonDTO.class)
    );
  }

  //=========================================================================================================
  // SELECT RECORDS
  //=========================================================================================================
  public List<PersonDTO> selectRecords(Integer id, Integer age) {
    return jdbcTemplate.query(
      " SELECT * FROM PERSON WHERE ID > ? AND AGE > ?"   //Return multiple Records
      , new Object[] { id, age }
      , new BeanPropertyRowMapper(PersonDTO.class)
    );
  }

  //=========================================================================================================
  // SELECT WITHOUT PARAMETERS
  //=========================================================================================================
  public List<PersonDTO> selectWithoutParameters() {
    return jdbcTemplate.query(
      " SELECT * FROM PERSON WHERE ID > 1 AND AGE > 10"  //Without SQL Parameters
    //, new Object[] { }                                 //new Object[] { } or skip this Function Parameter
      , new BeanPropertyRowMapper(PersonDTO.class)
    );
  }

}
