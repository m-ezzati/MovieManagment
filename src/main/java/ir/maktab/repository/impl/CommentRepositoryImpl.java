package ir.maktab.repository.impl;

import ir.maktab.model.Comment;
import ir.maktab.repository.CommentRepository;
import ir.maktab.repository.base.impl.BaseRepositoryImpl;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, Long> implements CommentRepository {

    public CommentRepositoryImpl(Class<Comment> entityClass) {
        super(entityClass);
    }
}
