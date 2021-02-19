package org.javamaster.b2c.mybatis.mapper.h2;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.SQL;
import org.javamaster.b2c.mybatis.model.*;

import java.util.List;

public interface PersonMapper {
    Person selectPerson(int id);

    List<Person> selectAllPerson();

    int insertRandIdAuthor(Person person);

    int insertPerson(Person person);

    @Insert("insert into Person(id, name, age,sex)values (#{id}, #{name}, #{age},${sex})")
    @SelectKey(statement = "select CAST(rand()*1000000 as INTEGER) a from dual", keyProperty = "id", before = true, resultType = int.class)
    int insertPersonAnno(Person person);

    int updatePerson(Person person);

    int deletePerson(Person person);

    Customer selectShopping(String custId);

    Product selectProduct(String prodId);

    Vendor selectVendor(@Param("vendId") String vendId);

    List<Vendor> selectVendors(@Param("vendCountry") String vendCountry, @Param("vendorState") String vendorState);

    List<Vendor> selectVendorsByIds(@Param("ids") List<String> ids);

    @Results(value = {@Result(property = "vendId", column = "vend_id", id = true)})
    @SelectProvider(type = VendorSqlBuilder.class, method = "buildVendorSql")
    List<Vendor> selectVendorsByIdsAnno(@Param("ids") List<String> ids);

    class VendorSqlBuilder {
        public static String buildVendorSql(@Param("ids") final List<String> ids) {
            return new SQL() {
                {
                    SELECT("vend_id,vend_name vendName");
                    FROM("vendors");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < ids.size(); i++) {
                        if (i != ids.size() - 1) {
                            sb.append("'").append(ids.get(i)).append("',");
                        } else {
                            sb.append("'").append(ids.get(i)).append("'");
                        }
                    }
                    WHERE("vend_id in(" + sb.toString() + ")");

                }
            }.toString();
        }
    }

    List<Vendor> selectVendorsLike(@Param("name") String name);
}
