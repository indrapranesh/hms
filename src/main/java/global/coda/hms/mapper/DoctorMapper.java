package global.coda.hms.mapper;


import global.coda.hms.model.Doctor;
import global.coda.hms.model.DoctorPatientMapper;
import global.coda.hms.model.Patient;
import global.coda.hms.model.UserDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;

@Mapper
@Component
public interface DoctorMapper {

    @Insert("INSERT INTO `t_doctor` (`doctor_specialization`,`fk_user_id`) VALUES (#{doctor_specialization},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "doctor_id" , keyColumn = "pk_doctor_id")
    int createDoctor(Doctor doctor);

    @Select("select pk_doctor_id as doctor_id,doctor_specialization,is_active,created_date,updated_date,fk_user_id as user_id from t_doctor where pk_doctor_id=#{doctorId}")

    @Results(id = "selectMap",value = {
            @Result(property = "userDetails",javaType = UserDetails.class,column="user_id", one= @One(select = "global.coda.hms.mapper.UserMapper.getUser"))
    })
    Doctor readDoctor(int doctorId);

    @Update("update t_doctor set doctor_specialization=#{doctor_specialization} where pk_doctor_id = #{doctor_id}")
    int editDoctor(Doctor doctor);

    @Update("update t_doctor set is_active=0 where pk_doctor_id=#{doctorId}")
    int deleteDoctor(int doctorId);

    @Select("<script> select distinct fk_doctor_id from t_record <if test='doctorId!=0'>where fk_doctor_id = #{doctorId}</if></script>")
    @Results({
            @Result(
                    column = "fk_doctor_id", property = "doctor", one = @One(select = "readDoctor")
            ),
            @Result(
                    column = "fk_doctor_id", property = "patient",
                    many = @Many(select = "global.coda.hms.mapper.DoctorMapper.readPatient")
            )
    })
    List<DoctorPatientMapper> listPatients(int doctorId);
    @Select("  select record.fk_patient_id,patient.fk_user_id as user_id,patient.blood_group,patient.weight,patient.is_active,patient.created_date as created_time,patient.updated_date as updated_time from t_record as record left join t_patient as patient on pk_patient_id = fk_patient_id where patient.is_active = 1 and record.fk_doctor_id = 3 group by patient.pk_patient_id;  ")
    @Results(
            @Result(
                    column = "user_id", property = "userDetails", javaType = UserDetails.class,one = @One(select = "global.coda" +
                    ".hms.mapper.UserMapper.getUser")
            )
    )
    List<Patient> readPatient(int doctorId);

}
