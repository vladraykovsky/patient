package com.parients.comparator;

import com.parients.model.Comment;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {


    @Override
    public int compare(Comment o1, Comment o2) {
        return (int) (o2.getComment_id() - o1.getComment_id());
    }
}
