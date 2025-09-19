<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.${packageName}.mapper.${className}Mapper">
    <!-- TODO: change Object to specific type -->
    <select id="findById" parameterType="Object" resultMap="${className}ResultMap">
        SELECT
          *
        FROM
          ${tableName}
        WHERE
          id = #{id}
    </select>

    <select id="findAll" resultMap="${className}ResultMap">
        SELECT
          *
        FROM
          ${tableName}
    </select>

    <insert id="insert" parameterType="${package}.${packageName}.entity.${className}Entity">
        <!-- TODO: 실제 컬럼명/필드 지정 -->
        INSERT INTO
          ${tableName} ( /* columns */ )
        VALUES
          ( /* values #{field} */ )
    </insert>

    <update id="update" parameterType="${package}.${packageName}.entity.${className}Entity">
        <!-- TODO: 실제 컬럼명/필드 지정 -->
        UPDATE
          ${tableName}
        SET
         /* column = #{field} */
        WHERE
          id = #{id}
    </update>
    <!-- TODO: change Object to specific type -->
    <delete id="deleteById" parameterType="${package}.${packageName}.entity.${className}Entity">
        DELETE FROM
          ${tableName}
        WHERE id = #{id}
    </delete>
</mapper>
