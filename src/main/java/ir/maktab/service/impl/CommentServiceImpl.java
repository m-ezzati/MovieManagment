package ir.maktab.service.impl;

import ir.maktab.model.Comment;
import ir.maktab.model.Movie;
import ir.maktab.repository.impl.CommentRepositoryImpl;
import ir.maktab.repository.impl.MovieRepositoryImpl;
import ir.maktab.service.CommentService;
import ir.maktab.service.MovieService;
import ir.maktab.service.base.impl.BaseServiceImpl;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long> implements CommentService {

    public CommentServiceImpl() {
        super(new CommentRepositoryImpl(Comment.class));
    }
}
