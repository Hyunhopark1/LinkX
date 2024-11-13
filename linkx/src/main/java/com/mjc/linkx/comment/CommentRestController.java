package com.mjc.linkx.comment;


import com.mjc.linkx.common.IResponseController;
import com.mjc.linkx.common.dto.ResponseCode;
import com.mjc.linkx.common.dto.ResponseDto;
import com.mjc.linkx.common.exception.IdNotFoundException;
import com.mjc.linkx.common.exception.LoginAccessException;
import com.mjc.linkx.user.IUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentRestController implements IResponseController {
    private final CommentServiceImpl commentService;


    // 댓글 추가
    @PostMapping
    public ResponseEntity<ResponseDto> insert(Model model, @RequestBody CommentDto dto, HttpSession session) {
        try {
            if (dto == null) {
                return makeResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "입력 매개변수 에러", null);
            }
            IUser loginUser = (IUser) session.getAttribute("LoginUser");

            IComment result = this.commentService.insert(loginUser, dto);
            return makeResponseEntity(HttpStatus.OK.value(), HttpStatus.OK, "성공", result);
        } catch (LoginAccessException ex) {
            log.error(ex.toString());
            return makeResponseEntity(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, ex.toString(), null);
        }catch (IdNotFoundException ex) {
            log.error(ex.toString());
            return makeResponseEntity(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage(), null);
        } catch (Exception ex) {
            log.error(ex.toString());
            return makeResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
    
    // 댓글 가져오기
    @GetMapping("/board/{boardType}/{boardId}/comments")
    public ResponseEntity<ResponseDto> commentList(@PathVariable String boardType,@PathVariable Long boardId, HttpSession session) {
        try {
            IUser loginUser = (IUser)session.getAttribute("LoginUser");
//            if (loginUser == null) {
//                throw new LoginAccessException("로그인 필요");
//            }

            if (boardType == null) {
                return makeResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "입력 매개변수 에러", null);
            }if (boardId == null) {
                return makeResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "입력 매개변수 에러", null);
            }
            SearchCommentDto dto = SearchCommentDto.builder()
                    .boardType(boardType)
                    .boardId(boardId)
                    .build();


            List<CommentDto> result = this.commentService.findAllByBoardTypeId(dto, loginUser);

            return makeResponseEntity(HttpStatus.OK.value(), HttpStatus.OK, "성공", result);
//        } catch (LoginAccessException ex) {
//            log.error(ex.toString());
//            return makeResponseEntity(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN, ex.toString(), null);
        }catch (IdNotFoundException ex) {
            log.error(ex.toString());
            return makeResponseEntity(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage(), null);
        } catch (Exception ex) {
            log.error(ex.toString());
            return makeResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
