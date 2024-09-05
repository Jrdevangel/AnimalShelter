package com.example.animalshelter.services;


import com.example.animalshelter.model.Post;
import com.example.animalshelter.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private IPostRepository iPostRepository;

    public void createPost(Post post) {
        iPostRepository.save(post);
    }

    public List<Post> readPost() {
        return new ArrayList<Post>(iPostRepository.findAll());
    }

    public Optional<Post> readNewsId(Long id) {
        return iPostRepository.findById(id);
    }

    public void updatePost(Post post, Long id) {
        post.setId(id);
        iPostRepository.save(post);
    }

    public void deletePost(Long id) {
        iPostRepository.deleteById(id);
    }
}






