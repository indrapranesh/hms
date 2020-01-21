package global.coda.hms.mapper;


import global.coda.hms.model.Patient;
import global.coda.hms.model.UserDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PatientMapper {

    @Insert("INSERT INTO `t_patient` (`fk_user_id`,`blood_group`,`weight`) VALUES (#{userId},#{bloodGroup},#{weight})")
    @Options(useGeneratedKeys = true, keyProperty = "patientId" , keyColumn = "pk_patient_id")
    int createPatient(Patient patient);

    @Select("select pk_patient_id as patient_id,fk_user_id as user_id,blood_group,weight,is_active,created_date,updated_date from t_patient where pk_patient_id = #{patientId}")

    @Results(id = "selectMap",value = {
            @Result(property = "userDetails",javaType = UserDetails.class,column="user_id", one= @One(select = "global.coda.hms.mapper.UserMapper.getUser"))
    })
    Patient readPatient(int patientId);

    @Update("update t_patient set weight=#{weight},blood_group=#{bloodGroup} where pk_patient_id = #{patientId}")
    int editPatient(Patient patient);

    @Update("update t_patient set is_active=0 where pk_patient_id=#{patientId}")
    int deletePatient(int patientId);
}
