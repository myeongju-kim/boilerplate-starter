<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.${packageName}.mapper.${className}Mapper">
    <!-- TODO: change Object to specific type -->
    <select id="findById" resultType="${package}.${packageName}.entity.${className}Entity">
        SELECT
          *
        FROM
          ${tableName}
        <#noparse>
        WHERE
          id = #{id}
        </#noparse>
    </select>

    <select id="findAll" resultType="${package}.${packageName}.entity.${className}Entity">
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
        <#noparse>
          ( /* values #{field} */ )
        </#noparse>
    </insert>

    <update id="update" parameterType="${package}.${packageName}.entity.${className}Entity">
        <!-- TODO: 실제 컬럼명/필드 지정 -->
        UPDATE
          ${tableName}
        <#noparse>
        SET
         /* column = #{field} */
        WHERE
          id = #{id}
        </#noparse>
    </update>
    <!-- TODO: change Object to specific type -->
    <delete id="delete" parameterType="${package}.${packageName}.entity.${className}Entity">
        DELETE FROM
          ${tableName}
        <#noparse>
        WHERE id = #{id}
        </#noparse>
    </delete>
</mapper>
