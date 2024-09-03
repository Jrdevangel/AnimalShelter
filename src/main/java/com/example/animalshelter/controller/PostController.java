package com.example.animalshelter.controller;


import com.example.animalshelter.model.Post;
import com.example.animalshelter.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping(path = "/post/getAll")
    public List<Post> getAllPost() {
        return postService.readPost();
    }

    @PostMapping(path = "/newPost")
    public void createPost(@RequestBody Post post) {
        postService.createPost(post);
    }

    @PutMapping(path = "/post/update/{id}")
    public void updatePost(@RequestBody Post post, @PathVariable Long id) {
        postService.updatePost(post, id);
    }

    @DeleteMapping(path = "/post/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}

