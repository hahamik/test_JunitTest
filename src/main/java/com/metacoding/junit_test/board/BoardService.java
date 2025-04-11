package com.metacoding.junit_test.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void 게시글쓰기(String title, String content) {
        boardRepository.save(title, content);
    }

    public List<Board> 게시글목록() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board 게시글상세보기(int id) {
        Board board = boardRepository.findById(id);
        if (board == null) throw new RuntimeException("없는 게시물");
        return boardRepository.findById(id);
    }

    @Transactional
    public void 게시글삭제(int id) {
        Board board = boardRepository.findById(id);

        if (board == null) {
            throw new RuntimeException("게시글이 없는데 왜 삭제를 ㅠ");
        }

        boardRepository.deleteById(id);
    }

    @Transactional
    public void 게시글수정하기(int id, String title, String content) {
        Board board = boardRepository.findById(id);

        if (board == null) {
            throw new RuntimeException("게시글이 없는데 왜 수정을 ㅠ");
        }

        boardRepository.update(id, title, content);
    }
}
