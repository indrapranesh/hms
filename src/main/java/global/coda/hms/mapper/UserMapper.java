package global.coda.hms.mapper;

import global.coda.hms.model.Account;
import global.coda.hms.model.UserDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("insert into `t_user_details`(`pk_user_id`,`username`,`password`,`firstname`,`lastname`,`is_active`,`fk_role_id`,`city`,`state`,`phone_number`) values(#{userId},#{username},#{password},#{firstname},#{lastname},#{isActive},#{roleId},#{city},#{state},#{phoneNumber})")
    @Options(useGeneratedKeys = true,keyProperty = "userId", keyColumn = "pk_user_id")
    int createUser(UserDetails userDetails);


    @Select("SELECT pk_user_id as user_id,username,password,firstname,lastname,city,state,created_time as created_date,updated_time as updated_date,phone_number,fk_role_id as role_id,is_active from t_user_details where pk_user_id=#{userId} and is_active=1;")
    UserDetails getUser(int userId);

    @Update("update t_user_details set firstname= #{firstname}, lastname = #{lastname} , city = #{city} , state = #{state} , phone_number = #{phoneNumber} where pk_user_id = #{userId}")
    int updateUser(UserDetails userDetails);

    @Update("update t_user_details set is_active=0 where pk_user_id = #{userId}")
    int deleteUser(int userId);

    @Select("select * from t_user_details where username=#{username} and password=#{password}")
    UserDetails login(Account accountDetails);
}



