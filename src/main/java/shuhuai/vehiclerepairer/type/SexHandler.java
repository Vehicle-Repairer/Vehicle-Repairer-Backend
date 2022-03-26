package shuhuai.vehiclerepairer.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Sex.class)
public class SexHandler extends BaseTypeHandler<Sex> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int index, Sex sex, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(index, sex.getType());
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, String field) throws SQLException {
        return Sex.getSexEnum(resultSet.getString(field));
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, int index) throws SQLException {
        return Sex.getSexEnum(resultSet.getString(index));
    }

    @Override
    public Sex getNullableResult(CallableStatement callableStatement, int index) throws SQLException {
        return Sex.getSexEnum(callableStatement.getString(index));
    }
}