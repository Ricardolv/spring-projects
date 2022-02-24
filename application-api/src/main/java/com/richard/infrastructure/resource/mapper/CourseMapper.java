package com.richard.infrastructure.resource.mapper;

import com.richard.infrastructure.persistence.CourseEntity;
import com.richard.infrastructure.resource.response.CourseResoponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseResoponse sourceToDestination(CourseEntity courseEntity);
    List<CourseResoponse> map(List<CourseEntity> courses);
}
