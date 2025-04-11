package com.metacoding.junit_test;

import com.metacoding.junit_test.board.Board;
import com.metacoding.junit_test.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import({BoardRepository.class})
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void findAll_test() {

        List<Board> boards = boardRepository.findAll();
        for (Board board : boards) {
            System.out.print("id : " + board.getId());
            System.out.print(" ,title : " + board.getTitle());
            System.out.println(" ,content : " + board.getContent());
            System.out.println("-------------");
        }

    }

    @Test
    public void findById_test() {
        int id = 1;

        Board board = boardRepository.findById(id);
        System.out.print("id : " + board.getId());
        System.out.print(" ,title : " + board.getTitle());
        System.out.println(" ,content : " + board.getContent());

    }

    @Test
    public void save_test() {
        String title = "title6";
        String content = "content6";

        boardRepository.save(title, content);

        int id = 6;
        Board board = boardRepository.findById(id);
        System.out.print("id : " + board.getId());
        System.out.print(" ,title : " + board.getTitle());
        System.out.println(" ,content : " + board.getContent());
    }


    @Test
    public void delete_test() {
        int id = 1;
        boardRepository.deleteById(id);
    }

    @Test
    public void update_test() {
        int id = 1;
        String title = "title9999";
        String content = "content9999";

        boardRepository.update(id,title, content);
        Board board = boardRepository.findById(id);
        System.out.print("id : " + board.getId());
        System.out.print(" ,title : " + board.getTitle());
        System.out.println(" ,content : " + board.getContent());

    }
}
