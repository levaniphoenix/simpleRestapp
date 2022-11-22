package com.levanphoenix.restapp.DAL;

import com.levanphoenix.restapp.models.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
}
