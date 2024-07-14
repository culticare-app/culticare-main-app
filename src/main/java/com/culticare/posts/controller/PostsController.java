package com.culticare.posts.controller;

import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.request.PostEditRequestDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.controller.dto.response.PostListResponseDto;
import com.culticare.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    // 게시글 등록
    @PostMapping("/new")
    public ResponseEntity<PostCreateResponseDto> savePosts(Long loginMemberId, PostCreateRequestDto postCreateRequestDto) {

        Long savedPostId = postsService.savePost(loginMemberId, postCreateRequestDto);
        PostCreateResponseDto postCreateResponseDto = postsService.getPost(savedPostId);

        return ResponseEntity.status(HttpStatus.OK).body(postCreateResponseDto);
    }

    // 카테고리별 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<PostListResponseDto> findByCategory(@RequestParam(value = "category") String category, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.getPostListByCategory(category, pageable));
    }

    // 게시글 개별 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostCreateResponseDto> findById(@PathVariable("postId") Long postId) {

        PostCreateResponseDto postResponseDto = postsService.getPost(postId);

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // 게시글 수정
    @PatchMapping("/edit/{postId}")
    public ResponseEntity<Void> editPost(Long loginMemberId, @PathVariable("postId") Long postId, PostEditRequestDto postEditRequestDto) {

        postsService.editPost(loginMemberId, postId, postEditRequestDto);

        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(Long loginMemberId, @PathVariable("postId") Long postId) {

        postsService.deletePost(loginMemberId, postId);

        return ResponseEntity.ok().build();
    }
}
