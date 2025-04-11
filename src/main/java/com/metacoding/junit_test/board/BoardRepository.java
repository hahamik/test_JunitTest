package com.metacoding.junit_test.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 책임 : DB와 소통하는 친구
@Repository // IoC 컬렉션에 뜬다.
public class BoardRepository {

    private EntityManager em;

    // DI -> IoC 순회해서 타입으로 찾아서 전달해준다.
    public BoardRepository(EntityManager em) {
        System.out.println("BoardRepository new 됨");
        this.em = em;
    }


    public void save(String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, created_at) values(?,?,now())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.executeUpdate(); // insert, update, delete
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList();

        return boardList;
    }

    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }


    }

    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public void update(int id, String title, String content) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }
}
