//package com.mmamanagement.mapper;
//
//import com.mmamanagement.dto.AttendeeDto;
//import com.mmamanagement.entity.Attendee;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//
//
//    public class AttendeeMapper {
//        // convert comment entity to comment dto
//        public static AttendeeDto mapToCommentDto(Attendee attendee){
//            return AttendeeDto.builder()
//                    .id(attendee.getId())
//                    .name(attendee.getName())
//                    .email(attendee.getEmail())
//                    .content(attendee.getContent())
//                    .createdOn(attendee.getCreatedOn())
//                    .updatedOn(attendee.getUpdatedOn())
//                    .build();
//        }
//
//        // convert comment dto to comment entity
//        public static Comment mapToComment(CommentDto commentDto){
//            return Comment.builder()
//                    .id(commentDto.getId())
//                    .name(commentDto.getName())
//                    .email(commentDto.getEmail())
//                    .content(commentDto.getContent())
//                    .createdOn(commentDto.getCreatedOn())
//                    .updatedOn(commentDto.getUpdatedOn())
//                    .build();
//        }
//    }
//
//
//}
