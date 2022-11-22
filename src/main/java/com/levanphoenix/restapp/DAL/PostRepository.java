package com.levanphoenix.restapp.DAL;

import com.levanphoenix.restapp.models.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
