package com.nimvb.app.repository;


import com.nimvb.app.database.document.Document;
import com.nimvb.app.database.model.Board;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BoardRepository implements SimpleRepository<Board,String>{

    private final Document<Board,String> boardDocument;

    public BoardRepository(Document<Board, String> boardDocument) {
        this.boardDocument = boardDocument;
    }

    @Override
    public Board save(Board board) {
        Assert.notNull(board,"null object is passed");
        if(board.getId() == null){
            return boardDocument.insert(board);
        }
        return boardDocument.update(board.getId(),board);
    }

    @Override
    public Board persist(Board board) {
        Assert.notNull(board,"null object is passed");
        if(board.getId() == null){
            return boardDocument.insert(board,false);
        }
        return boardDocument.update(board.getId(),board,false);
    }

    @Override
    public Optional<Board> findById(String id){
        Assert.notNull(id,"null id is passed");
        return Optional.ofNullable(boardDocument.find(id));
    }

    @Override
    public Optional<Board> fetchById(String id) {
        Assert.notNull(id,"null id is passed");
        return Optional.ofNullable(boardDocument.fetch(id));
    }

    @Override
    public Collection<Board> findAll() {
        return boardDocument.all();
    }

    @Override
    public void deleteById(String id){
        Assert.notNull(id,"null id is passed");
        boardDocument.delete(id);
    }

    @Override
    public Integer count() {
        return boardDocument.count();
    }


}
