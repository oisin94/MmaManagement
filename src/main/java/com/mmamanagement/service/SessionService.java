package com.mmamanagement.service;

import com.mmamanagement.dto.SessionDto;
import com.mmamanagement.entity.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface SessionService {

    List<SessionDto> findAllSessions();

    void createSession(SessionDto sessionDto);

    List<SessionDto> findSessionsBetween(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

    SessionDto findSessionByUrl(String sessionUrl);
}


//List<PostDto> findAllPosts();
//
//void createPost(PostDto postDto);
//
//PostDto findPostById(Long postId);
//
//void updatePost(PostDto postDto);
//
//void deletePost(Long postId);
//
//PostDto findPostByUrl(String postUrl);
//
//List<PostDto> searchPosts(String query);