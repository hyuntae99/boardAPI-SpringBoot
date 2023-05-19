package com.study.boardAPI.Service;

import com.study.boardAPI.entity.Board;
import com.study.boardAPI.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired // 스프링 빈이 알아서 읽어옴 (의존성 주입)
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception {

        // 프로젝트의 경로 (boardAPI 까지) + src.main.resources.static.file
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        // UUID = 식별자
        UUID uuid = UUID.randomUUID();

        // 랜덤 식별자_원래 파일 이름
        String fileName = uuid + "_" + file.getOriginalFilename();

        // 파일을 아래 경로에 넣고 파일 이름은 fileName
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        // Entity에 이름과 경로 저장 -> DB에 저장됨
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board); // entity 저장
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable); // findAll = List<Board> 반환
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get(); // id를 받아서 id에 해당하는 게시글을 찾는다.
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

}
